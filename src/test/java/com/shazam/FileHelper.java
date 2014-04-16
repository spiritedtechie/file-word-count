package com.shazam;

import java.io.File;

public class FileHelper {

    private static final String TEST_RESOURCES_DIRECTORY = "src/test/resources/";
    
    public static File aTestFile(final String filePath) {
        return new File(TEST_RESOURCES_DIRECTORY + filePath);
    }
    
}
