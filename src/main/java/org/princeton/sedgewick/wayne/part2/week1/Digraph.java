package org.princeton.sedgewick.wayne.part2.week1;

import edu.princeton.cs.algs4.In;
import org.princeton.sedgewick.wayne.part1.week2.containers.bag.Bag;

public class Digraph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public Digraph(In in) {
        this(in.readInt()); // read vertices
        int E = in.readInt(); // read edges
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph reverseGraph = new Digraph(V);
        for (int v = 0; v < V; v++)
            for (Integer w : adj[v])
                reverseGraph.addEdge(w, v);

        return reverseGraph;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph graph = new Digraph(in);
        graph.adj(0).forEach(v -> System.out.printf("%1$2s", v)); //  6 2 1 5
        System.out.println();
        graph.adj(5).forEach(v -> System.out.printf("%1$2s", v)); //  3 4
    }
}
