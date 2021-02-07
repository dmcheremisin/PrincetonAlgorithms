package org.princeton.sedgewick.wayne.part2.week2.mst;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph {

    private final int V;
    private final Bag<Edge>[] adj;

    public EdgeWeightedGraph(int v) {
        V = v;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int i = 0; i < v; i++)
            adj[i] = new Bag<>();
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());

        while (in.hasNextLine())
            addEdge(new Edge(in.readInt(), in.readInt(), in.readDouble()));
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

    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj(v)) {
                if (e.other(v) > v)
                    list.add(e);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
        for (Edge edge : graph.edges())
            System.out.println(edge);

        //Edge{v=5, w=7, weight=0.28}
        //Edge{v=4, w=5, weight=0.35}
        //Edge{v=4, w=7, weight=0.37}
        //Edge{v=6, w=4, weight=0.93}
        //Edge{v=3, w=6, weight=0.52}
        //Edge{v=2, w=3, weight=0.17}
        //Edge{v=2, w=7, weight=0.34}
        //Edge{v=6, w=2, weight=0.4}
        //Edge{v=1, w=7, weight=0.19}
        //Edge{v=1, w=3, weight=0.29}
        //Edge{v=1, w=5, weight=0.32}
        //Edge{v=1, w=2, weight=0.36}
        //Edge{v=0, w=7, weight=0.16}
        //Edge{v=0, w=2, weight=0.26}
        //Edge{v=0, w=4, weight=0.38}
        //Edge{v=6, w=0, weight=0.58}
    }
}
