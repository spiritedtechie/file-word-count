package com.shazam;

import java.io.File;
import java.util.List;

public class WordCount {

    private final Lines lines;

    public WordCount(Lines lines) {
        this.lines = lines;
    }

    public int count() throws Exception {
        List<String> linesList = lines.get();

        int count = 0;
        for (String line : linesList) {
            count = count + countLineWords(line);
        }
        return count;
    }

    private int countLineWords(String line) {
        String lineTrimmed = line.trim();
        if (!lineTrimmed.isEmpty()) return lineTrimmed.split("\\s+").length;
        else return 0;
    }

    public static void main(String[] args) throws Exception {
        String filePath = args[0];
        Lines lines = new FileLines(new File(filePath));
        WordCount wordCount = new WordCount(lines);
        System.out.println(wordCount.count());
    }

}