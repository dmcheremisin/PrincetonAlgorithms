package org.princeton.sedgewick.wayne.part2.week2.shortestPath;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

public class DijkstraSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        int V = G.V();
        distTo = new double[V];
        edgeTo = new DirectedEdge[V];
        pq = new IndexMinPQ<>(V);

        for (int i = 0; i < V; i++)
            distTo[i] = Double.POSITIVE_INFINITY;

        distTo[s] = 0;
        pq.insert(s, 0.0);

        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge edge : G.adj(v))
                relax(edge);
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.getFrom(), w = e.getTo();

        if (distTo[w] > distTo[v] + e.getWeight()) {
            distTo[w] = distTo[v] + e.getWeight();
            edgeTo[w] = e;

            if (pq.contains(w))
                pq.decreaseKey(w, distTo[w]);
            else
                pq.insert(w, distTo[w]);
        }
    }
    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> getPathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.getFrom()])
            path.push(e);
        return path;
    }

    public static void main(String[] args) {
        //tinyEWD.txt
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(new In(args[0]));
        DijkstraSP path = new DijkstraSP(digraph, 0);

        System.out.println(path.distTo[3]); // 0.9900000000000001
        System.out.println(path.distTo[6]); // 1.5100000000000002

        for (DirectedEdge edge : path.getPathTo(6))
            System.out.println(edge);
        //DirectedEdge{from=0, to=2, weight=0.26}
        //DirectedEdge{from=2, to=7, weight=0.34}
        //DirectedEdge{from=7, to=3, weight=0.39}
        //DirectedEdge{from=3, to=6, weight=0.52}
    }
}
