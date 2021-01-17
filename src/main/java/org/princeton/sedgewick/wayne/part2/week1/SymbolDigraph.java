package org.princeton.sedgewick.wayne.part2.week1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

public class SymbolDigraph {

    private Map<String, Integer> map; // String -> index
    private String[] keys; // index -> String
    private Digraph digraph; // the graph

    public SymbolDigraph(String stream, String sp) {
        map = new HashMap<>();
        In in = new In(stream);

        // ------------- First pass ---------------
        // Build dictionary with airport codes and array indexes
        while (in.hasNextLine()) { // reads line "JFK MCO"
            String[] codeArr = in.readLine().split(sp); // makes array [JFK, MCO]
            for (String s : codeArr)
                map.putIfAbsent(s, map.size()); // {JFK -> 0, MCO -> 1}
        }

        keys = new String[map.size()]; // Inverted index
        for (String name : map.keySet())
            keys[map.get(name)] = name; // map.get("JFK") == 0 => keys[0] = "JFK"


        // ------------- Second pass  --------------
        // Reads the same date for the second time and builds graph according to
        // dictionary code indexes

        digraph = new Digraph(map.size());
        in = new In(stream); // Second pass
        while (in.hasNextLine()){ // builds the graph
            String[] codeArr = in.readLine().split(sp); /// makes array [JFK, MCO]
            int v = map.get(codeArr[0]); // map.get("JFK") == 0
            for (int i = 1; i < codeArr.length; i++)
                digraph.addEdge(v, map.get(codeArr[i])); // map.get("MCO") == 1 => graph.addEdge(0, 1)
        }
    }

    public boolean contains(String name) {
        return map.containsKey(name);
    }

    public int getVertexByName(String name) {
        return map.get(name);
    }

    public String getVertexName(int v) {
        return keys[v];
    }

    public Digraph getDigraph() {
        return digraph;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String delim = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, delim);

        Digraph graph = sg.getDigraph();
        for (String key : sg.keys) {
            int vertex = sg.getVertexByName(key);
            System.out.println(key);
            for (int w : graph.adj(vertex))
                StdOut.println("===> " + sg.getVertexName(w));
        }
        //JFK
        //===> ORD
        //===> ATL
        //===> MCO
        //MCO
        //ORD
        //===> ATL
        //===> PHX
        //===> DFW
        //===> HOU
        //===> DEN
        //DEN
        //===> LAS
        //===> PHX
        //HOU
        //===> MCO
        //DFW
        //===> HOU
        //===> PHX
        //PHX
        //===> LAX
        //ATL
        //===> MCO
        //===> HOU
        //LAX
        //LAS
        //===> PHX
        //===> LAX
    }
}