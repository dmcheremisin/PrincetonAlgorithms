package org.princeton.sedgewick.wayne.part2.week2.shortestPath;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph {

    private final int V;
    private final Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int v) {
        V = v;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<>();
    }

    public EdgeWeightedDigraph(In in) {
        this(in.readInt());

        while (in.hasNextLine())
            addEdge(new DirectedEdge(in.readInt(), in.readInt(), in.readDouble()));
    }

    public void addEdge(DirectedEdge edge) {
        int from = edge.getFrom();
        adj[from].add(edge);
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }
}
