package org.princeton.sedgewick.wayne.part2.week3.minCutMaxFlow;

import org.princeton.sedgewick.wayne.part1.week2.containers.bag.Bag;

public class FlowNetwork {

    private final int V;
    private Bag<FlowEdge>[] adj;

    public FlowNetwork(int v) {
        V = v;
        adj = (Bag<FlowEdge>[]) new Bag[v];
        for (int i = 0; i < v; i++)
            adj[i] = new Bag<>();
    }

    public int getV() {
        return V;
    }

    public void addEdge(FlowEdge e) {
        int from = e.from();
        int to = e.to();

        adj[from].add(e);
        adj[to].add(e);
    }

    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }
}
