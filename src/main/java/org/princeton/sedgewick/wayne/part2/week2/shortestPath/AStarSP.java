package org.princeton.sedgewick.wayne.part2.week2.shortestPath;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Collections;

// A* (A star) algorithm implementation using models from Princeton course by Robert Sedgewick and Kevin Wayne
public class AStarSP {

    private final EdgeWeightedDigraph G;
    private DirectedEdge[] edgeTo;
    private double[] gScore; // Cost from start along best known path(distTo in prev algorithms)
    private double[] fScore; // Estimated total cost from start to goal through y(new)

    //private boolean[] closed; // visited vertexes(marked in prev algorithms) - we don't need it
    //closed array is absent in pseudocode in wikipedia https://en.wikipedia.org/wiki/A*_search_algorithm
    //we can reach vertex by different ways, alternative way may be better, we will need to update gScore in that case

    private IndexMinPQ<Double> openPq;

    public AStarSP(EdgeWeightedDigraph G) {
        this.G = G;
    }

    public Iterable<DirectedEdge> path(int start, int goal) {
        int V = G.V();
        gScore = new double[V];
        fScore = new double[V];
        //closed = new boolean[V];
        edgeTo = new DirectedEdge[V];
        openPq = new IndexMinPQ<>(V); // priority queue of graph vertexes with priority based on fScore(weight to goal)

        for (int i = 0; i < V; i++) {
            gScore[i] = Double.POSITIVE_INFINITY;
            fScore[i] = Double.POSITIVE_INFINITY;
        }

        gScore[start] = 0.0;
        openPq.insert(start, 0.0);

        while (!openPq.isEmpty()) {
            int v = openPq.delMin();
            if (v == goal)
                return getPathTo(v); // goal vertex is found, we can return path

            //closed[v] = true;
            for (DirectedEdge e : G.adj(v)) {
                //if (closed[e.getTo()])
                    //continue;
                relax(e, goal);
            }
        }
        return Collections.emptyList();
    }


    private void relax(DirectedEdge e, int goal) {
        int v = e.getFrom(), w = e.getTo();

        if (gScore[w] > gScore[v] + e.getWeight()) {
            gScore[w] = gScore[v] + e.getWeight();
            edgeTo[w] = e;

            fScore[w] = gScore[w] + heuristicCost(w, goal);
            if (openPq.contains(w))
                openPq.decreaseKey(w, fScore[w]);
            else
                openPq.insert(w, fScore[w]);
        }
    }

    // base implementation, should be properly designed to predict roughly cost to get to the target
    private int heuristicCost(int w, int goal) {
        return 1;
    }

    public Iterable<DirectedEdge> getPathTo(int v) {
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.getFrom()])
            path.push(e);
        return path;
    }

    public static void main(String[] args) {
        //tinyEWD.txt
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(new In(args[0]));
        AStarSP aStarSP = new AStarSP(digraph);
        Iterable<DirectedEdge> path = aStarSP.path(0, 6);

        for (DirectedEdge edge : path)
            System.out.println(edge);
        //DirectedEdge{from=0, to=2, weight=0.26}
        //DirectedEdge{from=2, to=7, weight=0.34}
        //DirectedEdge{from=7, to=3, weight=0.39}
        //DirectedEdge{from=3, to=6, weight=0.52}
    }

}
