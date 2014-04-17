package com.shazam;

import java.io.File;

public class WordCounter implements Counter {

    private final Lines lines;

    public WordCounter(Lines lines) {
        this.lines = lines;
    }

    public int count() throws Exception {
        int count = 0;
        for (String line : lines.get()) {
            count = count + countLineWords(line);
        }
        return count;
    }

    private int countLineWords(String line) {
        final String lineTrimmed = line.trim();
        if (lineTrimmed.isEmpty()) return 0;
        else return lineTrimmed.split("\\s+").length;
    }

    public static void main(String[] args) throws Exception {

        if (args.length == 1) {
            File f = new File(args[0]);
            WordCounter wc = new WordCounter(new FileLines(f));
            System.out.println(wc.count());
        } else if (args.length == 2) {
            if (!"-l".equals(args[0])) {
                System.out.println("Invalid arguments supplied");
            } else {
                File f = new File(args[1]);
                LineCounter lc = new LineCounter(new FileLines(f));
                System.out.println(lc.count());
            }
        } else {
            System.out.println("Invalid number of arguments supplied");
        }
    }
}