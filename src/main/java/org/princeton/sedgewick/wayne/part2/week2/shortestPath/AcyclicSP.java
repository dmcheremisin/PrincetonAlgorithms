package org.princeton.sedgewick.wayne.part2.week2.shortestPath;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Topological;

public class AcyclicSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph graph, int s) {
        edgeTo = new DirectedEdge[graph.V()];
        distTo = new double[graph.V()];

        for (int i = 0; i < graph.V(); i++)
            distTo[i] = Double.POSITIVE_INFINITY;
        distTo[s] = 0;

        Topological topological = new Topological(graph);
        for (Integer v : topological.order())
            for (DirectedEdge edge : graph.adj(v))
                relax(edge);
    }

    private void relax(DirectedEdge edge) {
        int from = edge.from();
        int to = edge.to();
        double weight = distTo[from] + edge.weight();
        if(distTo[to] > weight){
            distTo[to] = weight;
            edgeTo[to] = edge;
        }
    }

    public Iterable<DirectedEdge> getPathTo(int v) {
        Bag<DirectedEdge> pathToV = new Bag<>();
        int currentVertex = v;
        DirectedEdge edgeInPath;

        while ((edgeInPath = edgeTo[currentVertex]) != null) {
            pathToV.add(edgeInPath);
            currentVertex = edgeInPath.from();
        }

        return pathToV;
    }

    public static void main(String[] args) {
        //tinyEWDAG.txt
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(new In(args[0]));
        AcyclicSP path = new AcyclicSP(digraph, 5);

        System.out.println(path.distTo[3]); // 0.61
        System.out.println(path.distTo[6]); // 1.13

        for (DirectedEdge edge : path.getPathTo(6))
            System.out.println(edge);
        //5->1  0.32
        //1->3  0.29
        //3->6  0.52
    }

}
