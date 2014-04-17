package com.shazam.functional;

import java.io.File;
import java.util.ArrayList;

public class ProcessRunner {

    public Process runWordCount(File testFile) throws Exception {
        return runJarApplication(testFile);
    }

    public Process runLineCount(File testFile) throws Exception {
        return runJarApplication(testFile, "-l");
    }

    public Process runJarApplication(File testFile, String... optionalExecArgs) throws Exception {
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
