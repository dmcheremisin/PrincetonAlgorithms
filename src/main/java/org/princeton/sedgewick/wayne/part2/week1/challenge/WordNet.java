package org.princeton.sedgewick.wayne.part2.week1.challenge;

import edu.princeton.cs.algs4.In;
import org.princeton.sedgewick.wayne.part1.week2.containers.bag.Bag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordNet {

    private Set<String> nouns = new HashSet<>();
    private List<String> synsets = new ArrayList<>();
    private List<Bag<Integer>> hypernyms = new ArrayList<>();

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null)
            throw new IllegalArgumentException("Constructor arguments must not be null");
        In in = new In(synsets);
        while (in.hasNextLine()) {
            String[] synArr = in.readLine().split(",");
            this.synsets.add(synArr[1]);
            String[] synNouns = synArr[1].split(" ");
            for (String synNoun : synNouns)
                nouns.add(synNoun);

            this.hypernyms.add(new Bag<>());
        }
        in = new In(hypernyms);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] hyp = line.split(",");
            int id = -1;
            for (int i = 0; i < hyp.length; i++) {
                int parsedInt = Integer.parseInt(hyp[i]);
                if (i == 0) {
                    id = parsedInt;
                    continue;
                }
                this.hypernyms.get(id).add(parsedInt);
            }
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nouns;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new IllegalArgumentException("word must not be null");
        return nouns.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new IllegalArgumentException("nounB & nounB must not be null");
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new IllegalArgumentException("nounB & nounB must not be null");
        return "";
    }

    // do unit testing of this class
    public static void main(String[] args) {
        String basePath = "C:\\Workspace\\PrincetonAlgorithms\\src\\main\\resources\\part2\\week1\\challenge\\";
        WordNet wordNet = new WordNet(basePath + "synsets.txt", basePath + "hypernyms.txt");
        System.out.println(wordNet.isNoun("AIDS")); // true
        System.out.println(wordNet.isNoun("ALGOL")); // true
        System.out.println(wordNet.isNoun("BASH")); // false
    }
}