package agh.cs.lab8.utils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DNAUtils {

    public static Integer mostCommon(List<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }
        Map.Entry<Integer, Integer> max = null;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }
        assert max != null;
        return list.indexOf(max.getKey());
    }


    public static List<Integer> drawDNA() {
        List<Integer> dna = new ArrayList<>();
        for(int i = 0; i<Config.dnaLength; i++) {
            dna.add(ThreadLocalRandom.current().nextInt(0,8));
        }
        completeDNA(dna);
        return dna;
    }

    public static List<Integer> completeDNA(List<Integer> dna) {
        Collections.sort(dna);
        for(int i = 0; i<8; i++) {
            if(!dna.contains(i)) {
                dna.set(mostCommon(dna), i);
            }
        }
        Collections.sort(dna);
        return dna;
    }

    public static List<Integer> recombineDNA(List<Integer> dna1, List<Integer> dna2) {
        Collections.sort(dna1);
        Collections.sort(dna2);
        int splitpoint1 = ThreadLocalRandom.current().nextInt(0,Config.dnaLength-1);
        int splitpoint2 = ThreadLocalRandom.current().nextInt(splitpoint1,Config.dnaLength+1);
        List<Integer> dna = new ArrayList<>();
        for(int i = 0; i<splitpoint1; i++) {
            dna.add(dna1.get(i));
        }
        for(int i = splitpoint1; i<splitpoint2; i++) {
            dna.add(dna2.get(i));
        }
        for(int i = splitpoint2; i<Config.dnaLength; i++) {
            dna.add(dna1.get(i));
        }
        completeDNA(dna);
        return dna;
    }
}
