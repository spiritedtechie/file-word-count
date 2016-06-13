package com.spiritedtechie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileLines implements Lines {

    private final File f;

    public FileLines(File f) {
        validateFile(f);
        this.f = f;
    }

    private void validateFile(File f) {
        if (f == null) throw new IllegalArgumentException("No file supplied");
        if (!f.exists()) throw new IllegalArgumentException("File does not exist");
    }

    public List<String> get() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(f));
        try {
            return buildLines(br);
        } finally {
            br.close();
        }
    }

    private List<String> buildLines(BufferedReader br) throws Exception {
        List<String> lines = new ArrayList();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }
}