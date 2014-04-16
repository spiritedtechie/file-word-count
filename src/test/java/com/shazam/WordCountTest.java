package com.shazam;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class WordCountTest {

    private static final String TEST_RESOURCES_DIRECTORY = "src/test/resources/";

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void emptyFile_emptyLine() throws Exception {
        // given
        File f = aTestFile("empty.txt");

        // when
        int count = countWordsInFile(f);

        // then
        assertWordCount(0, count);
    }

    @Test
    public void oneLine_oneWord() throws Exception {
        // given
        File f = aTestFile("oneline_oneword.txt");

        // when
        int count = countWordsInFile(f);

        // then
        assertWordCount(1, count);
    }

    @Test
    public void oneLine_oneWord_whitespaceBeforeLine() throws Exception {
        // given
        File f = aTestFile("oneline_oneword_whitespaceBefore.txt");

        // when
        int count = countWordsInFile(f);

        // then
        assertWordCount(1, count);
    }

    @Test
    public void oneLine_oneWord_whitespaceAfterLine() throws Exception {
        // given
        File f = aTestFile("oneline_oneword_whitespaceAfter.txt");

        // when
        int count = countWordsInFile(f);

        // then
        assertWordCount(1, count);
    }

    @Test
    public void oneLine_multipleWords_oneWhitespaceSeparating() throws Exception {
        // given
        File f = aTestFile("oneline_multiplewords_oneWhitespaceSeparating.txt");

        // when
        int count = countWordsInFile(f);

        // then
        assertWordCount(3, count);
    }

    @Test
    public void oneLine_multipleWords_multipleWhitespaceSeparating() throws Exception {
        // given
        File f = aTestFile("oneline_multiplewords_multipleWhitespaceSeparating.txt");

        // when
        int count = countWordsInFile(f);

        // then
        assertWordCount(3, count);
    }

    @Test
    public void multipleLines_multipleWords() throws Exception {
        // given
        File f = aTestFile("multiplelines_multiplewords.txt");

        // when
        int count = countWordsInFile(f);

        // then
        assertWordCount(7, count);
    }

    @Test
    public void nullFile() throws Exception {
        // given
        File f = noFile();

        // expect
        expectArgumentExceptionWithMessage("No file supplied");

        // when
        countWordsInFile(f);
    }

    @Test
    public void fileDoesNotExist() throws Exception {
        // given
        File f = nonExistentFile();

        // expect
        expectArgumentExceptionWithMessage("File does not exist");

        // when
        countWordsInFile(f);
    }

    private void expectArgumentExceptionWithMessage(final String string) {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(string);
    }

    private void assertWordCount(final int expected, final int actual) {
        assertEquals(expected, actual);
    }

    private File aTestFile(final String filePath) {
        return new File(TEST_RESOURCES_DIRECTORY + filePath);
    }

    private File noFile() {
        return null;
    }

    private File nonExistentFile() {
        return aTestFile("doesnotexist.txt");
    }

    private int countWordsInFile(final File f) throws Exception {
        WordCount wc = new WordCount(f);
        return wc.count();
    }

}
