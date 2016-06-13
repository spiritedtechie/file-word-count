package com.spiritedtechie;

import static com.spiritedtechie.FileHelper.aTestFile;
import static java.lang.Integer.parseInt;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class CountApplicationIT {

    private CountApplicationRunner pr;

    @Before
    public void setUp() {
        pr = new CountApplicationRunner();
    }

    @Test
    public void wordCount_fourWords() throws Exception {
        // Given
        File testFile = aTestFile("sampleFile_fourWords.txt");
        // When
        Process p = pr.runWordCount(testFile);
        // Then
        assertCompleted(p);
        List<String> output = extractContents(p.getInputStream());
        assertThat(output, hasSize(1));
        assertWordCount(4, parseInt(output.get(0)));
    }

    @Test
    public void wordCount_nineWords() throws Exception {
        // Given
        File testFile = aTestFile("sampleFile_nineWords.txt");
        // When
        Process p = pr.runWordCount(testFile);
        // Then
        assertCompleted(p);
        List<String> output = extractContents(p.getInputStream());
        assertThat(output, hasSize(1));
        assertWordCount(9, parseInt(output.get(0)));
    }

    @Test
    public void lineCount_threeLines() throws Exception {
        // Given
        File testFile = aTestFile("sampleFile_threeLines.txt");
        // When
        Process p = pr.runLineCount(testFile);
        // Then
        assertCompleted(p);
        List<String> output = extractContents(p.getInputStream());
        assertThat(output, hasSize(1));
        assertWordCount(3, parseInt(output.get(0)));
    }

    @Test
    public void lineCount_fourLines() throws Exception {
        // Given
        File testFile = aTestFile("sampleFile_fourLines.txt");
        // When
        Process p = pr.runLineCount(testFile);
        // Then
        assertCompleted(p);
        List<String> output = extractContents(p.getInputStream());
        assertThat(output, hasSize(1));
        assertLineCount(4, parseInt(output.get(0)));
    }

    @Test
    public void invalidArgument() throws Exception {
        // Given
        File testFile = aTestFile("sampleFile_fourLines.txt");
        // When
        Process p = pr.runJarApplication(testFile, "-invalidArgument");
        // Then
        assertCompleted(p);
        List<String> output = extractContents(p.getInputStream());
        assertThat(output, hasSize(1));
        assertEquals("Invalid count type argument supplied", output.get(0));
    }

    @Test
    public void invalidNumberOfArguments() throws Exception {
        // Given
        File testFile = aTestFile("sampleFile_fourLines.txt");
        // When
        Process p = pr.runJarApplication(testFile, "-l", "-a");
        // Then
        assertCompleted(p);
        List<String> output = extractContents(p.getInputStream());
        assertThat(output, hasSize(1));
        assertEquals("Invalid number of arguments supplied", output.get(0));
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

    private List<String> extractContents(InputStream is) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        List<String> contents = new ArrayList<String>();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                contents.add(line);
            }
            return contents;
        } finally {
            br.close();
        }
    }

}
