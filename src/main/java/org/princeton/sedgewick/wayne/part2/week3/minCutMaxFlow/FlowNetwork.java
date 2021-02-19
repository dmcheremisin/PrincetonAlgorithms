package org.princeton.sedgewick.wayne.part2.week3.minCutMaxFlow;

import edu.princeton.cs.algs4.In;
import org.princeton.sedgewick.wayne.part1.week2.containers.bag.Bag;

public class FlowNetwork {

    private final int V;
    private Bag<FlowEdge>[] adj;

    public FlowNetwork(int v) {
        V = v;
        adj = (Bag<FlowEdge>[]) new Bag[v];
        for (int i = 0; i < v; i++)
            adj[i] = new Bag<>();
    }

    public FlowNetwork(In in) {
        this(in.readInt());

        while (in.hasNextLine())
            addEdge(new FlowEdge(in.readInt(), in.readInt(), in.readDouble()));
    }

    public int getV() {
        return V;
    }

    public void addEdge(FlowEdge e) {
        int from = e.from();
        int to = e.to();

        adj[from].add(e);
        adj[to].add(e);
    }

    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    public static void main(String[] args) {
        // tinyFN.txt
        FlowNetwork flowNetwork = new FlowNetwork(new In(args[0]));
        for (FlowEdge flowEdge : flowNetwork.adj(2))
            System.out.println(flowEdge);
        //FlowEdge{v=2, w=4, capacity=1.0, flow=0.0}
        //FlowEdge{v=2, w=3, capacity=1.0, flow=0.0}
        //FlowEdge{v=0, w=2, capacity=3.0, flow=0.0}
    }
}
