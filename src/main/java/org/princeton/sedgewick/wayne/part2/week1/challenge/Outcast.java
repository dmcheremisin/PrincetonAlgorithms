package org.princeton.sedgewick.wayne.part2.week1.challenge;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Outcast {

    private final WordNet wordNet;

    public Outcast(WordNet wordnet) { // constructor takes a WordNet object
        this.wordNet = wordnet;
    }

    public String outcast(String[] nouns) { // given an array of WordNet nouns, return an outcast
        if (nouns == null || nouns.length < 2)
            throw new IllegalArgumentException("Nouns size must be greater or equal to 2");

        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < nouns.length; i++) {
            int distance = 0;
            String x = nouns[i];
            for (int j = 0; j < nouns.length; j++) {
                if (j == i)
                    continue;
                String y = nouns[j];
                distance += wordNet.distance(x, y);
            }
            distances.add(i, distance);
        }
        int max = Collections.max(distances);
        int index = distances.indexOf(max);

        return nouns[index];
    }

    public static void main(String[] args) { // see test client below
        String basePath = "C:\\Workspace\\PrincetonAlgorithms\\src\\main\\resources\\part2\\week1\\challenge\\";
        WordNet wordNet = new WordNet(basePath + "synsets.txt", basePath + "hypernyms.txt");
        Outcast outcast = new Outcast(wordNet);
        printOutcast(outcast, "horse zebra cat bear table"); // table
        printOutcast(outcast, "water soda bed orange_juice milk apple_juice tea coffee"); // bed
        printOutcast(outcast, "apple pear peach banana lime lemon blueberry strawberry mango watermelon potato"); // potato
    }

    private static void printOutcast(Outcast outcast, String str) {
        String[] nouns = str.split(" ");
        StdOut.println(str + ": " + outcast.outcast(nouns));
    }
}