package org.princeton.sedgewick.wayne.part2.week2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class KruskalMST {

    private Queue<Edge> mst = new Queue<>();

    public KruskalMST(EdgeWeightedGraph graph) {
        MinPQ<Edge> minPq = new MinPQ<>();
        for (Edge edge : graph.edges())
            minPq.insert(edge);

        UF uf = new UF(graph.V());
        while (!minPq.isEmpty() && mst.size() < graph.V() - 1) {
            Edge edge = minPq.delMin();
            int v = edge.either();
            int w = edge.other(v);
            if (!uf.connected(v, w)) {
                mst.enqueue(edge);
                uf.union(v, w);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
        KruskalMST kruskalMST = new KruskalMST(graph);
        for (Edge edge : kruskalMST.mst)
            System.out.println(edge);

        //Edge{v=0, w=7, weight=0.16}
        //Edge{v=2, w=3, weight=0.17}
        //Edge{v=1, w=7, weight=0.19}
        //Edge{v=0, w=2, weight=0.26}
        //Edge{v=5, w=7, weight=0.28}
        //Edge{v=4, w=5, weight=0.35}
        //Edge{v=6, w=2, weight=0.4}
    }
}
