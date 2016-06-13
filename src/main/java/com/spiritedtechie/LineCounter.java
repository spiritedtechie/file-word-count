package com.spiritedtechie;

public class LineCounter implements Counter {

    private static final String TYPE_CODE = "-l";
    private final Lines lines;

    public LineCounter(Lines lines) {
        this.lines = lines;
    }

    public int count() throws Exception {
        return lines.get().size();
    }

    public String getTypeCode() {
        return TYPE_CODE;
    }

}
