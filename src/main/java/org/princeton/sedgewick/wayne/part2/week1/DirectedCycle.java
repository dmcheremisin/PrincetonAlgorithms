package org.princeton.sedgewick.wayne.part2.week1;

import edu.princeton.cs.algs4.In;
import org.princeton.sedgewick.wayne.part1.week2.containers.bag.Bag;

public class DirectedCycle {

    private final Digraph G;
    private final boolean[] marked;
    private final boolean[] onStack; // helps to detect cycle
    private final int[] edgeTo;
    private Bag<Integer> cycle;

    public DirectedCycle(Digraph G) {
        this.G = G;
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v])
                dfs(v);
    }

    private void dfs(int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : G.adj(v)) {
            if (hasCycle())
                return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(w);
            } else if (onStack[w]) {
                cycle = new Bag<>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.add(x);
                cycle.add(w);
                cycle.add(v);
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

    public static void main(String[] args) {
        Digraph graph = new Digraph(new In(args[0]));
        DirectedCycle directedCycle = new DirectedCycle(graph);
        if (directedCycle.hasCycle())
            System.out.println(directedCycle.cycle()); // [3, 5, 4, 3]

    }
}
