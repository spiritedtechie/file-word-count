package com.shazam;

import static com.shazam.FileHelper.aTestFile;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FilesLinesTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void nullFile() throws Exception {
        // given
        File f = noFile();
        // expect
        expectArgumentExceptionWithMessage("No file supplied");
        // when
        createNewFileLines(f);
    }

    @Test
    public void fileDoesNotExist() throws Exception {
        // given
        File f = nonExistentFile();
        // expect
        expectArgumentExceptionWithMessage("File does not exist");
        // when
        createNewFileLines(f);
    }

    private FileLines createNewFileLines(final File f) {
        return new FileLines(f);
    }

    private void expectArgumentExceptionWithMessage(final String string) {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(string);
    }

    private File noFile() {
        return null;
    }

    private File nonExistentFile() {
        return aTestFile("doesnotexist.txt");
    }
}
