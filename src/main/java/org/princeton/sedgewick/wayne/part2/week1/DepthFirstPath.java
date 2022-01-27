package org.princeton.sedgewick.wayne.part2.week1;

import edu.princeton.cs.algs4.In;

import java.util.Stack;

public class DepthFirstPath {

    private final Graph G;
    private final boolean[] marked;
    private final int[] edgeTo;
    private final int s;

    public DepthFirstPath(Graph g, int s) {
        this.G = g;
        this.s = s;
        marked = new boolean[g.getV()];
        edgeTo = new int[g.getV()];
        dfs(s);
    }

    private void dfs(int s) {
        marked[s] = true;
        for (Integer adj : G.adj(s)) {
            if (!marked[adj]) {
                edgeTo[adj] = s;
                dfs(adj);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;

        Stack<Integer> stack = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x])
            stack.push(x);

        stack.push(s);
        return stack;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(args[0]));
        DepthFirstPath dfp = new DepthFirstPath(graph, 0);

        System.out.println(dfp.hasPathTo(3)); // true
        System.out.println(dfp.pathTo(3)); // [3, 5, 4, 6, 0]

        System.out.println(dfp.hasPathTo(11)); // false
        System.out.println(dfp.pathTo(11)); // null
    }
}
