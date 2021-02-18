package org.princeton.sedgewick.wayne.part2.week3.minCutMaxFlow;

public class FlowEdge {

    private final int v, w;
    private final double capacity;
    private double flow;

    public FlowEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getFlow() {
        return flow;
    }

    public int other(int vertex) {
        if (vertex == v) return w;
        if (vertex == w) return v;
        throw new IllegalArgumentException("Illegal endpoint");
    }

    public double residualCapacityTo(int vertex) {
        if (vertex == v)
            return flow;

        if (vertex == w)
            return capacity - flow;

        throw new IllegalArgumentException("Illegal endpoint");
    }

    public void addResidualFlowTo(int vertex, double delta) {
        if (vertex == v)
            flow -= delta;

        if (vertex == w)
            flow += delta;

        throw new IllegalArgumentException("Illegal endpoint");
    }
}
