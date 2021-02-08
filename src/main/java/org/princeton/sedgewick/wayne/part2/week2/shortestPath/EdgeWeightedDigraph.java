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

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int i = 0; i < V; i++)
            for (DirectedEdge directedEdge : this.adj[i])
                bag.add(directedEdge);

        return bag;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(new In(args[0]));
        for (DirectedEdge edge : digraph.edges())
            System.out.println(edge);
        //DirectedEdge{from=7, to=5, weight=0.28}
        //DirectedEdge{from=7, to=3, weight=0.39}
        //DirectedEdge{from=6, to=2, weight=0.4}
        //DirectedEdge{from=6, to=0, weight=0.58}
        //DirectedEdge{from=6, to=4, weight=0.93}
        //DirectedEdge{from=5, to=4, weight=0.35}
        //DirectedEdge{from=5, to=7, weight=0.28}
        //DirectedEdge{from=5, to=1, weight=0.32}
        //DirectedEdge{from=4, to=5, weight=0.35}
        //DirectedEdge{from=4, to=7, weight=0.37}
        //DirectedEdge{from=3, to=6, weight=0.52}
        //DirectedEdge{from=2, to=7, weight=0.34}
        //DirectedEdge{from=1, to=3, weight=0.29}
        //DirectedEdge{from=0, to=4, weight=0.38}
        //DirectedEdge{from=0, to=2, weight=0.26}
    }
}
