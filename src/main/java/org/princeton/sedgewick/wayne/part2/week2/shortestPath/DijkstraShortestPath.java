package org.princeton.sedgewick.wayne.part2.week2.shortestPath;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

public class DijkstraShortestPath {

    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraShortestPath(EdgeWeightedDigraph graph, int s) {
        int vertexes = graph.V();
        distTo = new double[vertexes];
        edgeTo = new DirectedEdge[vertexes];
        pq = new IndexMinPQ<>(vertexes);

        for (int i = 0; i < vertexes; i++)
            distTo[i] = Double.POSITIVE_INFINITY;

        distTo[s] = 0;

        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge edge : graph.adj(v))
                relax(edge);
        }
    }

    private void relax(DirectedEdge edge) {
        int from = edge.getFrom();
        int to = edge.getTo();
        double newWeight = distTo[from] + edge.getWeight();
        if (distTo[to] > newWeight) {
            distTo[to] = newWeight;
            edgeTo[to] = edge;
            if (pq.contains(to))
                pq.decreaseKey(to, newWeight);
            else
                pq.insert(to, newWeight);
        }
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(new In(args[0]));
        DijkstraShortestPath path = new DijkstraShortestPath(digraph, 0);
        System.out.println(path.distTo[3]); // 0.99
        System.out.println(path.distTo[6]); // 1.51
    }
}
