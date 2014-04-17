package com.shazam.functional;

import static com.shazam.FileHelper.aTestFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class WordCountIT {

    private ProcessRunner pr;

    @Before
    public void setUp() {
        pr = new ProcessRunner();
    }

    @Test
    public void wordCount() throws Exception {
        // Given
        File testFile = aTestFile("word_count_integration_test.txt");
        // When
        Process p = pr.runWordCount(testFile);
        // Then
        assertCompleted(p);
        assertWordCount(9, extractCount(p.getInputStream()));
    }

    @Test
    public void lineCount() throws Exception {
        // Given
        File testFile = aTestFile("word_count_integration_test.txt");
        // When
        Process p = pr.runLineCount(testFile);
        // Then
        assertCompleted(p);
        assertLineCount(4, extractCount(p.getInputStream()));
    }

    private void assertWordCount(int expectedCount, int actualCount) throws Exception {
        assertEquals(expectedCount, actualCount);
    }

    private void assertLineCount(int expectedCount, int actualCount) throws Exception {
        assertEquals(expectedCount, actualCount);
    }

    private void assertCompleted(Process p) throws Exception {
        boolean isComplete = p.waitFor(3, TimeUnit.SECONDS);
        assertTrue("Application did not complete", isComplete);
    }

    private int extractCount(InputStream is) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            String output = br.readLine();
            return Integer.parseInt(output);
        } finally {
            br.close();
        }
    }

}
