package com.shazam;

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
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        List<String> lines = new ArrayList<String>();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            br.close();
        }
        return lines;
    }
}