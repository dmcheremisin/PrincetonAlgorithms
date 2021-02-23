package org.princeton.sedgewick.wayne.part2.week3.minCutMaxFlow;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class FordFulkerson {

    private boolean[] marked;
    private FlowEdge edgeTo[];
    private double value;

    public FordFulkerson(FlowNetwork flowNetwork, int s, int t) {
        value = 0;

        while (hasAugmentingPath(flowNetwork, s, t)) {
            double bottle = Double.POSITIVE_INFINITY;

            for (int v = t; v != s; v = edgeTo[v].other(v))
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));

            for (int v = t; v != s; v = edgeTo[v].other(v))
                edgeTo[v].addResidualFlowTo(v, bottle);

            value += bottle;
        }
    }

    private boolean hasAugmentingPath(FlowNetwork flowNetwork, int s, int t) {
        edgeTo = new FlowEdge[flowNetwork.getV()];
        marked = new boolean[flowNetwork.getV()];

        Queue<Integer> q = new Queue<>();
        q.enqueue(s);
        marked[s] = true;

        while (!q.isEmpty()) {
            Integer v = q.dequeue();

            for (FlowEdge edge : flowNetwork.adj(v)) {
                int w = edge.other(v);

                if (edge.residualCapacityTo(w) > 0 && !marked[w]) {
                    edgeTo[w] = edge;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }

        return marked[t];
    }

    public double getValue() {
        return value;
    }

    public boolean inCut(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        FlowNetwork flowNetwork = new FlowNetwork(new In(args[0]));
        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, 0, 5);

        System.out.println(fordFulkerson.getValue()); //4.0
    }
}
