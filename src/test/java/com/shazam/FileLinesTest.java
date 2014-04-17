package com.shazam;

import static com.shazam.FileHelper.aTestFile;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FileLinesTest {

    private static final String FILE_LINES_TEST_DIRECTORY = "fileLinesTest/";

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void instantiatedWithNullFile() throws Exception {
        // given
        File f = noFile();
        // expect
        expectArgumentExceptionWithMessage("No file supplied");
        // when
        getFileLines(f);
    }

    @Test
    public void fileDoesNotExist() throws Exception {
        // given
        File f = nonExistentFile();
        // expect
        expectArgumentExceptionWithMessage("File does not exist");
        // when
        getFileLines(f);
    }

    @Test
    public void file_oneLine_empty() throws Exception {
        // given
        File f = aFileLinesTestFile("oneLine_empty.txt");
        // when
        List<String> fileLines = getFileLines(f);
        // then
        assertThat(fileLines, empty());
    }

    @Test
    public void file_oneLine_nonEmpty() throws Exception {
        // given
        File f = aFileLinesTestFile("oneLine_nonEmpty.txt");
        // when
        List<String> fileLines = getFileLines(f);
        // then
        assertThat(fileLines, hasItems("hello\tthere          Fred"));
    }

    @Test
    public void file_multipleLines_allEmpty() throws Exception {
        // given
        File f = aFileLinesTestFile("multipleLines_allEmpty.txt");
        // when
        List<String> fileLines = getFileLines(f);
        // then
        assertThat(fileLines, hasItems(""));
    }

    @Test
    public void file_multipleLines_nonEmpty() throws Exception {
        // given
        File f = aFileLinesTestFile("multipleLines_nonEmpty.txt");
        // when
        List<String> fileLines = getFileLines(f);
        // then
        assertThat(fileLines, hasSize(4));
        assertThat(fileLines, hasItems("hello", "\tfred", "\t   how", "    are you"));
    }

    @Test
    public void file_multipleLines_mixureOfEmptyAndNonEmpty() throws Exception {
        // given
        File f = aFileLinesTestFile("multipleLines_mixureOfEmptyAndNonEmpty.txt");
        // when
        List<String> fileLines = getFileLines(f);
        // then
        assertThat(fileLines, hasSize(5));
        assertThat(fileLines, hasItems("hello", "\t", "\tfred", "", "hi"));
    }

    private List<String> getFileLines(File f) throws Exception {
        FileLines fl = new FileLines(f);
        return fl.get();
    }

    private void expectArgumentExceptionWithMessage(String string) {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(string);
    }

    private File aFileLinesTestFile(String string) {
        return aTestFile(FILE_LINES_TEST_DIRECTORY + string);
    }

    private File noFile() {
        return null;
    }

    private File nonExistentFile() {
        return aTestFile("doesnotexist.txt");
    }
}
