package org.princeton.sedgewick.wayne.part2.week2.shortestPath;

import edu.princeton.cs.algs4.Bag;
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

    public Iterable<DirectedEdge> getPathTo(int v) {
        Bag<DirectedEdge> pathToV = new Bag<>();
        int currentVertex = v;
        DirectedEdge edgeInPath;

        while ((edgeInPath = edgeTo[currentVertex]) != null) {
            pathToV.add(edgeInPath);
            currentVertex = edgeInPath.getFrom();
        }

        return pathToV;
    }

    public static void main(String[] args) {
        //tinyEWD.txt
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(new In(args[0]));
        DijkstraShortestPath path = new DijkstraShortestPath(digraph, 0);

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
