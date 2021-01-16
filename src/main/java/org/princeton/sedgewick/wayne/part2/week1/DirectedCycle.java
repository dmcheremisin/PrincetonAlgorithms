package org.princeton.sedgewick.wayne.part2.week1;

import java.util.Stack;

public class DirectedCycle {

    private final Digraph digraph;
    private final boolean[] marked;
    private final boolean[] onStack;
    private final int[] edgeTo;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph digraph) {
        this.digraph = digraph;
        marked = new boolean[digraph.getV()];
        onStack = new boolean[digraph.getV()];
        edgeTo = new int[digraph.getV()];
        for (int v = 0; v < digraph.getV(); v++)
            if (!marked[v])
                dfs(v);
    }

    private void dfs(int v) {
        marked[v] = true;
        onStack[v] = true;
        for (Integer w : digraph.adj(v)) {
            if (hasCycle())
                return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
