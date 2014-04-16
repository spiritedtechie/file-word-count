package com.shazam.functional;

import static com.shazam.FileHelper.aTestFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class WordCountIT {

    @Test
    public void wordCount() throws Exception {
        // Given
        File testFile = aTestFile("word_count_integration_test.txt");
        // When
        Process p = runApplication(testFile);
        // Then
        assertCompleted(p);
        assertWordCount(9, extractCount(p.getInputStream()));
    }

    private void assertWordCount(final int expectedCount, final int actualCount) throws IOException {
        assertEquals(expectedCount, actualCount);
    }

    private Process runApplication(final File testFile) throws IOException {
        File jar = new File("target/word-count-0.0.1-SNAPSHOT.jar");
        String[] execArgs = new String[] { "java", "-jar", jar.getCanonicalPath(), testFile.getCanonicalPath() };
        return Runtime.getRuntime().exec(execArgs);
    }

    private void assertCompleted(final Process p) throws InterruptedException {
        boolean isComplete = p.waitFor(3, TimeUnit.SECONDS);
        assertTrue("Application did not complete", isComplete);
    }

    private int extractCount(final InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            String output = br.readLine();
            return Integer.parseInt(output);
        } finally {
            br.close();
        }
    }

}
