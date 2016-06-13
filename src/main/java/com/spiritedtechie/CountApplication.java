package com.spiritedtechie;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CountApplication {

    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            runCount(args[0], "-w");
        } else if (args.length == 2) {
            runCount(args[1], args[0]);
        } else {
            System.out.println("Invalid number of arguments supplied");
        }
    }

    private static void runCount(String filePath, String countTypeCode) throws Exception {
        List<Counter> counters = buildCounters(filePath);
        Counter counter = findFirstMatchingCounter(counters, countTypeCode);

        if (counter != null) {
            System.out.println(counter.count());
        } else {
            System.out.println("Invalid count type argument supplied");
        }
    }

    private static Counter findFirstMatchingCounter(List<Counter> counters, String countTypeCode) {
        for (Counter counter : counters) {
            if (countTypeCode.equals(counter.getTypeCode())) return counter;
        }
        return null;
    }

    private static List<Counter> buildCounters(String filePath) {
        File f = new File(filePath);
        List<Counter> counters = new ArrayList();
        counters.add(new LineCounter(new FileLines(f)));
        counters.add(new WordCounter(new FileLines(f)));
        return counters;
    }
}
