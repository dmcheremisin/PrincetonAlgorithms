package org.princeton.sedgewick.wayne.part2.week1.challenge;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.util.List;

public class SAP {

    private final Digraph digraph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.digraph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        BreadthFirstDirectedPaths pathV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths pathW = new BreadthFirstDirectedPaths(digraph, w);

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(w);
        boolean[] marked = new boolean[digraph.V()];
        while (!queue.isEmpty()) {
            Integer vertex = queue.dequeue();
            for (Integer adj : digraph.adj(vertex)) {
                if (pathV.hasPathTo(adj))
                    return pathV.distTo(adj) + pathW.distTo(adj);
                if (!marked[adj]) {
                    marked[adj] = true;
                    queue.enqueue(adj);
                }
            }
        }

        return -1;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        BreadthFirstDirectedPaths pathV = new BreadthFirstDirectedPaths(digraph, v);

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(w);
        boolean[] marked = new boolean[digraph.V()];
        marked[w] = true;
        while (!queue.isEmpty()) {
            Integer vertex = queue.dequeue();
            for (Integer adj : digraph.adj(vertex)) {
                if (pathV.hasPathTo(adj))
                    return adj;
                if (!marked[adj]) {
                    marked[adj] = true;
                    queue.enqueue(adj);
                }
            }
        }

        return -1;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths paths = new BreadthFirstDirectedPaths(digraph, v);

        boolean hasPath = false;
        for (Integer wVertex : w) {
            if (paths.hasPathTo(wVertex)) {
                hasPath = true;
                break;
            }
        }
        if (!hasPath)
            return -1;

        int minPathLength = 0;
        for (Integer wVertex : w) {
            int pathLength = paths.distTo(wVertex);
            if (pathLength < minPathLength)
                minPathLength = pathLength;
        }

        return minPathLength;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths pathV = new BreadthFirstDirectedPaths(digraph, v);

        Queue<Integer> queue = new Queue<>();
        for (Integer wVertex : w)
            queue.enqueue(wVertex);

        boolean[] marked = new boolean[digraph.V()];
        while (!queue.isEmpty()) {
            Integer vertex = queue.dequeue();
            for (Integer adj : digraph.adj(vertex)) {
                if (pathV.hasPathTo(adj))
                    return adj;
                if (!marked[adj]) {
                    marked[adj] = true;
                    queue.enqueue(adj);
                }
            }
        }
        return -1;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));
        SAP sap = new SAP(digraph);
        System.out.println(sap.ancestor(3, 11));
        System.out.println(sap.length(3, 11));
        System.out.println(sap.ancestor(List.of(13, 23, 24), List.of(6, 16,17)));
    }
}
