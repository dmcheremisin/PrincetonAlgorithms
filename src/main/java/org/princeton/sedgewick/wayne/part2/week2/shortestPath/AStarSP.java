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
    private boolean[] closed; // visited vertexes(marked in prev algorithms)
    private IndexMinPQ<Double> openPq;

    public AStarSP(EdgeWeightedDigraph G) {
        this.G = G;
        gScore = new double[G.V()];
        fScore = new double[G.V()];
        closed = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
    }

    public Iterable<DirectedEdge> path(int start, int goal) {
        int V = G.V();
        // sort vertexes based on fScore which means take first most promising vertex
        openPq = new IndexMinPQ<>(V);

        for (int i = 0; i < V; i++) {
            gScore[i] = Double.POSITIVE_INFINITY;
            fScore[i] = Double.POSITIVE_INFINITY;
        }

        gScore[start] = 0.0;
        openPq.insert(start, 0.0);

        while (!openPq.isEmpty()) {
            int v = openPq.delMin();
            //System.out.println("v = " + v);
            if (v == goal)
                return getPathTo(v); // return path

            closed[v] = true;

            for (DirectedEdge e : G.adj(v)) {
                //System.out.println("====> " + e);
                if (closed[e.getTo()])
                    continue;

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
            //System.out.println(Arrays.asList(gScore));
            //System.out.println(Arrays.asList(edgeTo));

            fScore[w] = gScore[w] + heuristicCostEstimate(w, goal);
            if (openPq.contains(w))
                openPq.decreaseKey(w, fScore[w]);
            else
                openPq.insert(w, fScore[w]);
        }
    }

    // base implementation, should be properly designed to predict roughly cost to get to the target
    private int heuristicCostEstimate(Integer w, Integer goal) {
        return 1;
    }

    public double distTo(int v) {
        return gScore[v];
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
