package com.shazam.functional;

import static com.shazam.FileHelper.aTestFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class WordCountIT {

    @Test
    public void wordCount() throws Exception {
        // Given
        File testFile = aTestFile("word_count_integration_test.txt");
        // When
        Process p = runWordCount(testFile);
        // Then
        assertCompleted(p);
        assertWordCount(9, extractCount(p.getInputStream()));
    }

    @Test
    public void lineCount() throws Exception {
        // Given
        File testFile = aTestFile("word_count_integration_test.txt");
        // When
        Process p = runLineCount(testFile);
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

    private Process runWordCount(File testFile) throws Exception {
        return runJarApplication(testFile);
    }

    private Process runLineCount(File testFile) throws Exception {
        return runJarApplication(testFile, "-l");
    }

    private Process runJarApplication(File testFile, String... optionalExecArgs) throws Exception {
        File jar = new File("target/word-count-0.0.1-SNAPSHOT.jar");
        ArrayList<String> execArgsList =
                buildExecArgs(jar.getCanonicalPath(), testFile.getCanonicalPath(), optionalExecArgs);
        return Runtime.getRuntime().exec(execArgsList.toArray(new String[0]));
    }

    private ArrayList<String> buildExecArgs(String jarFilePath, String testFilePath, String... optionalExecArgs)
            throws Exception {
        ArrayList<String> execArgsList = new ArrayList<String>();
        execArgsList.add("java");
        execArgsList.add("-jar");
        execArgsList.add(jarFilePath);
        for (String optionalArg : optionalExecArgs) {
            execArgsList.add(optionalArg);
        }
        execArgsList.add(testFilePath);
        return execArgsList;
    }
}
