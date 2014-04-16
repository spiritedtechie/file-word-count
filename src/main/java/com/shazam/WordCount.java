package com.shazam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class WordCount {

    private final File f;

    public WordCount(final File f) {
        validateFile(f);
        this.f = f;
    }

    private void validateFile(final File f) {
        if (f == null) throw new IllegalArgumentException("No file supplied");
        if (!f.exists()) throw new IllegalArgumentException("File does not exist");
    }

    public int count() throws Exception {
        final FileReader fr = new FileReader(f);
        final BufferedReader br = new BufferedReader(fr);
        int count = 0;
        try {
            String line;
            while ((line = br.readLine()) != null) {
                count = count + countLineWords(line);
            }
        } finally {
            br.close();
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
        WordCount wc = new WordCount(new File(filePath));
        System.out.println(wc.count());
    }

}