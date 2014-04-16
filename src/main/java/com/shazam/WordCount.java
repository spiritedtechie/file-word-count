package com.shazam;

import java.io.File;
import java.util.List;

public class WordCount {

    private final IFileLines fileLines;

    public WordCount(final IFileLines fr) {
        this.fileLines = fr;
    }

    /*
     * (non-Javadoc)
     * @see com.shazam.IFileLines#count()
     */
    public int count() throws Exception {
        List<String> lines = fileLines.read();

        int count = 0;
        for (String line : lines) {
            count = count + countLineWords(line);
        }
        return count;
    }

    private int countLineWords(final String line) {
        String lineTrimmed = line.trim();
        if (!lineTrimmed.isEmpty()) return lineTrimmed.split("\\s+").length;
        else return 0;
    }

    public static void main(final String[] args) throws Exception {
        String filePath = args[0];
        IFileLines fileLines = new FileLines(new File(filePath));
        WordCount wordCount = new WordCount(fileLines);
        System.out.println(wordCount.count());
    }

}