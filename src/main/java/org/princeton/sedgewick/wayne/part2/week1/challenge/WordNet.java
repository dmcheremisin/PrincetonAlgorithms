package org.princeton.sedgewick.wayne.part2.week1.challenge;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordNet {

    private final Digraph digraph;
    private final Map<String, List<Integer>> nounsMap = new HashMap<>();
    private final List<String> synsets = new ArrayList<>();

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null)
            throw new IllegalArgumentException("Constructor arguments must not be null");

        In in = new In(synsets);
        while (in.hasNextLine()) {
            String[] synArr = in.readLine().split(",");
            int synsetId = Integer.parseInt(synArr[0]);
            this.synsets.add(synsetId, synArr[1]);
            String[] synNouns = synArr[1].split(" ");
            for (String synNoun : synNouns) {
                nounsMap.putIfAbsent(synNoun, new ArrayList<>());
                nounsMap.get(synNoun).add(synsetId);
            }
        }

        this.digraph = new Digraph(this.synsets.size());
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
                this.digraph.addEdge(id, parsedInt);
            }
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounsMap.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new IllegalArgumentException("word must not be null");
        return nounsMap.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new IllegalArgumentException("nounA & nounB must not be null");

        SAP sap = new SAP(this.digraph);
        return sap.length(nounsMap.get(nounA), nounsMap.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new IllegalArgumentException("nounA & nounB must not be null");

        SAP sap = new SAP(this.digraph);
        int ancestorId = sap.ancestor(nounsMap.get(nounA), nounsMap.get(nounB));
        return synsets.get(ancestorId);
    }

    // do unit testing of this class
    public static void main(String[] args) {
        String basePath = "C:\\Workspace\\PrincetonAlgorithms\\src\\main\\resources\\part2\\week1\\challenge\\";
        WordNet wordNet = new WordNet(basePath + "synsets.txt", basePath + "hypernyms.txt");
        System.out.println(wordNet.isNoun("AIDS")); // true
        System.out.println(wordNet.isNoun("ALGOL")); // true
        System.out.println(wordNet.isNoun("BASH")); // false
        System.out.println(wordNet.nounsMap.get("worm")); // [81679, 81680, 81681, 81682]
        System.out.println(wordNet.nounsMap.get("bird")); // [24306, 24307, 25293, 33764, 70067]

        printPath(wordNet, "municipality", "region"); // [55651, 55652] && [21477, 65579, 65580, 65581, 65582]
        //Path from 'municipality' to 'region'
        //[55651, 55652]
        //[21477, 65579, 65580, 65581, 65582]
        // ========================
        //Has path to id: 65579
        //55651: municipality
        //19379: administrative_district administrative_division territorial_division
        //35787: district territory territorial_dominion dominion
        //65579: region
        // ========================
        printPath(wordNet, "individual", "physical_entity");
        //Path from 'individual' to 'physical_entity'
        //[47987, 60216]
        //[60600]
        // ========================
        //Has path to id: 60600
        //60216: person individual someone somebody mortal soul
        //28054: causal_agent cause causal_agency
        //60600: physical_entity
        // ========================
        printPath(wordNet, "edible_fruit", "physical_entity");
        //Path from 'edible_fruit' to 'physical_entity'
        //[37179]
        //[60600]
        // ========================
        //Has path to id: 60600
        //37179: edible_fruit
        //63109: produce green_goods green_groceries garden_truck
        //41005: food solid_food
        //71516: solid
        //53519: matter
        //60600: physical_entity
        // ========================
    }

    private static void printPath(WordNet wordNet, String from, String to) {
        System.out.println(String.format("Path from '%s' to '%s'", from, to));
        List<Integer> fromIds = wordNet.nounsMap.get(from);
        List<Integer> toIds = wordNet.nounsMap.get(to);
        System.out.println(fromIds);
        System.out.println(toIds);
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(wordNet.digraph, fromIds);
        for (Integer toId : toIds) {
            if (bfs.hasPathTo(toId)) {
                System.out.println(" ======================== ");
                System.out.println("Has path to id: " + toId);
                for (Integer vertex : bfs.pathTo(toId))
                    System.out.println(vertex + ": " + wordNet.synsets.get(vertex));
                System.out.println(" ======================== ");
            }
        }

    }
}