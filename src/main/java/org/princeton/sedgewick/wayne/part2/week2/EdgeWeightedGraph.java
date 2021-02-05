package org.princeton.sedgewick.wayne.part2.week2;

import edu.princeton.cs.algs4.Bag;

public class EdgeWeightedGraph {

    private final int V;
    private final Bag<Edge>[] adj;

    public EdgeWeightedGraph(int v) {
        V = v;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int i = 0; i < v; i++)
            adj[i] = new Bag<>();
    }

    public int V() {
        return V;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }
}
