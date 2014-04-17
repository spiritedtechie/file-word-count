package com.shazam;

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

    public String getTypeCode() {
        return "-w";
    }

}