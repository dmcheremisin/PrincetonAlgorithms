package org.princeton.sedgewick.wayne.part2.week1.challenge;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.Iterator;

public class SAP {

    private final Digraph digraph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.digraph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        BreadthFirstDirectedPaths paths = new BreadthFirstDirectedPaths(digraph, v);
        if (!paths.hasPathTo(w))
            return -1;

        return paths.distTo(w);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        BreadthFirstDirectedPaths paths = new BreadthFirstDirectedPaths(digraph, v);
        if (!paths.hasPathTo(w))
            return -1;
        int dist = paths.distTo(w);

        Iterable<Integer> integers = paths.pathTo(w);
        int commonDist = dist / 2;
        int count = 0;
        int ancestor = -1;
        Iterator<Integer> iterator = integers.iterator();
        while (count++ <= commonDist && iterator.hasNext())
            ancestor = iterator.next();

        return ancestor;
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
        return 0;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));
        SAP sap = new SAP(digraph);
    }
}
