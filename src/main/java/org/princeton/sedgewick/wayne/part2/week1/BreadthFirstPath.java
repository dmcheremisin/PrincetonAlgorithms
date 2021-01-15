package org.princeton.sedgewick.wayne.part2.week1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.util.Stack;

public class BreadthFirstPath {

    private final Graph g;
    private final boolean[] marked;
    private final int[] edgeTo;
    private final int s;

    public BreadthFirstPath(Graph g, int s) {
        this.g = g;
        this.s = s;
        marked = new boolean[g.getV()];
        edgeTo = new int[g.getV()];
        bfp(s);
    }

    private void bfp(int s) {
        marked[s] = true;

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);

        while (!queue.isEmpty()) {
            Integer v = queue.dequeue();
            for (Integer adj : g.adj(v)) {
                if (!marked[adj]) {
                    marked[adj] = true;
                    edgeTo[adj] = v;
                    queue.enqueue(adj);
                }
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
        BreadthFirstPath bfp = new BreadthFirstPath(graph, 0);

        System.out.println(bfp.hasPathTo(3)); // true
        System.out.println(bfp.pathTo(3)); // [3, 5, 0]

        System.out.println(bfp.hasPathTo(11)); // false
        System.out.println(bfp.pathTo(11)); // null
    }
}
