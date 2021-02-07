package org.princeton.sedgewick.wayne.part2.week2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class PrimLazyMST {

    private final MinPQ<Edge> minPQ = new MinPQ<>();
    private final Queue<Edge> mst = new Queue<>();

    private final EdgeWeightedGraph graph;
    private final boolean[] marked;

    public PrimLazyMST(EdgeWeightedGraph graph) {
        this.graph = graph;
        marked = new boolean[graph.V()];

        visit(0);

        while (!minPQ.isEmpty() && mst.size() < graph.V() - 1) {
            Edge edge = minPQ.delMin();
            int v = edge.either();
            int w = edge.other(v);

            if (marked[v] && marked[w]) continue;

            mst.enqueue(edge);

            if (!marked[v]) visit(v);
            if (!marked[w]) visit(w);
        }
    }

    private void visit(int v) {
        marked[v] = true;

        for (Edge edge : graph.adj(v)) {
            int w = edge.other(v);
            if (!marked[w])
                minPQ.insert(edge);
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
        PrimLazyMST primLazyMST = new PrimLazyMST(graph);
        for (Edge edge : primLazyMST.mst)
            System.out.println(edge);

        //Edge{v=0, w=7, weight=0.16}
        //Edge{v=1, w=7, weight=0.19}
        //Edge{v=0, w=2, weight=0.26}
        //Edge{v=2, w=3, weight=0.17}
        //Edge{v=5, w=7, weight=0.28}
        //Edge{v=4, w=5, weight=0.35}
        //Edge{v=6, w=2, weight=0.4}
    }
}
