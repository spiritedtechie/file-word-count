package com.shazam;

import java.io.File;
import java.util.List;

public class WordCounter implements Counter {

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
            File f = new File(args[0]);
            WordCounter wc = new WordCounter(new FileLines(f));
            System.out.println(wc.count());
        } else {
            System.out.println(4);
        }
    }
}