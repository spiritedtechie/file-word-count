package com.shazam;

public class LineCounter implements Counter {

    private final Lines lines;

    public LineCounter(Lines lines) {
        this.lines = lines;
    }

    public int count() throws Exception {
        return lines.get().size();
    }

}
