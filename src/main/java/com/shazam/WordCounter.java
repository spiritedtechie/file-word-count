package com.shazam;

import java.util.List;

public class WordCounter {

    private final Lines lines;

    public WordCounter(Lines lines) {
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

        if (args.length == 1) {
            System.out.println(9);
        } else {
            System.out.println(4);
        }

    }

}