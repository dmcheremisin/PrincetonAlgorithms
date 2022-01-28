package org.princeton.sedgewick.wayne.part2.week2.mst;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

public class PrimEagerMST {

    private final EdgeWeightedGraph G;
    private final Edge[] edgeTo;
    private final Double[] distTo;
    private final boolean[] marked;
    private final IndexMinPQ<Double> pq;

    public PrimEagerMST(EdgeWeightedGraph G) {
        this.G = G;
        edgeTo = new Edge[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<>(G.V());

        distTo = new Double[G.V()];
        for (int i = 0; i < G.V(); i++)
            distTo[i] = Double.POSITIVE_INFINITY;
        
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty())
            visit(pq.delMin());
    }

    private void visit(int v) {
        marked[v] = true;

        for (Edge edge : G.adj(v)) {
            int w = edge.other(v);
            if (marked[w]) continue;

            if (edge.weight() < distTo[w]) {
                edgeTo[w] = edge;
                distTo[w] = edge.weight();
                if (pq.contains(w))
                    pq.changeKey(w, edge.weight());
                else
                    pq.insert(w, edge.weight());
            }
        }
    }

    public Iterable<Edge> mst() {
        Bag<Edge> mst = new Bag<>();
        for (int i = 1; i < edgeTo.length; i++)
            mst.add(edgeTo[i]);

        return mst;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
        PrimEagerMST primEagerMST = new PrimEagerMST(graph);
        for (Edge edge : primEagerMST.mst())
            System.out.println(edge);

        //Edge{v=0, w=7, weight=0.16}
        //Edge{v=6, w=2, weight=0.4}
        //Edge{v=5, w=7, weight=0.28}
        //Edge{v=4, w=5, weight=0.35}
        //Edge{v=2, w=3, weight=0.17}
        //Edge{v=0, w=2, weight=0.26}
        //Edge{v=1, w=7, weight=0.19}
    }
}
