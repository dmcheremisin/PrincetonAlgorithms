package org.princeton.sedgewick.wayne.part2.week1.challenge;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class SAP {

    private final Digraph digraph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.digraph = new Digraph(G);
    }

    private void checkArguments(int v, int w) {
        if (v < 0 || w < 0)
            throw new IllegalArgumentException("v and w must be greater than 0");
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        checkArguments(v, w);
        if (v == w)
            return 0;

        BreadthFirstDirectedPaths pathV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths pathW = new BreadthFirstDirectedPaths(digraph, w);

        int ancestorW = getAncestor(pathV, w);
        int distanceW = -1;
        if (ancestorW != -1)
            distanceW = pathV.distTo(ancestorW) + pathW.distTo(ancestorW);

        int ancestorV = getAncestor(pathW, v);
        int distanceV = -1;
        if (ancestorV != -1)
            distanceV = pathV.distTo(ancestorV) + pathW.distTo(ancestorV);

        if (distanceV != -1 && distanceW != -1) {
            return Math.min(distanceV, distanceW);
        } else if (distanceV != -1) {
            return distanceV;
        } else if (distanceW != -1) {
            return distanceW;
        }

        return -1;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        checkArguments(v, w);
        if (v == w)
            return v;

        BreadthFirstDirectedPaths pathV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths pathW = new BreadthFirstDirectedPaths(digraph, w);

        int ancestorW = getAncestor(pathV, w);
        int distanceW = -1;
        if (ancestorW != -1)
            distanceW = pathV.distTo(ancestorW) + pathW.distTo(ancestorW);

        int ancestorV = getAncestor(pathW, v);
        int distanceV = -1;
        if (ancestorV != -1)
            distanceV = pathV.distTo(ancestorV) + pathW.distTo(ancestorV);

        if (distanceV != -1 && distanceW != -1) {
            return distanceV < distanceW ? ancestorV : ancestorW;
        } else if (distanceV != -1) {
            return ancestorV;
        } else if (distanceW != -1) {
            return ancestorW;
        }

        return -1;
    }

    private int getAncestor(BreadthFirstDirectedPaths path, int search) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(search);
        boolean[] marked = new boolean[digraph.V()];
        marked[search] = true;
        while (!queue.isEmpty()) {
            Integer vertex = queue.dequeue();
            for (Integer adj : digraph.adj(vertex)) {
                if (path.hasPathTo(adj))
                    return adj;
                if (!marked[adj]) {
                    marked[adj] = true;
                    queue.enqueue(adj);
                }
            }
        }

        return -1;
    }

    private boolean isEmpty(Iterable<Integer> iterable) {
        return iterable == null || !iterable.iterator().hasNext();
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (isEmpty(v) || isEmpty(w))
            throw new IllegalArgumentException("zero vertices");

        BreadthFirstDirectedPaths pathV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths pathW = new BreadthFirstDirectedPaths(digraph, w);

        int ancestorV = getAncestor(w, pathV);
        if (ancestorV == -1)
            return -1;
        int ancestorW = getAncestor(v, pathW);
        int distV = pathV.distTo(ancestorV) + pathW.distTo(ancestorV);
        int distW = pathV.distTo(ancestorW) + pathW.distTo(ancestorW);

        return Math.min(distV, distW);
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (isEmpty(v) || isEmpty(w))
            throw new IllegalArgumentException("zero vertices");

        BreadthFirstDirectedPaths pathV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths pathW = new BreadthFirstDirectedPaths(digraph, w);

        int ancestorV = getAncestor(w, pathV);
        if (ancestorV == -1)
            return -1;
        int ancestorW = getAncestor(v, pathW);
        int distV = pathV.distTo(ancestorV) + pathW.distTo(ancestorV);
        int distW = pathV.distTo(ancestorW) + pathW.distTo(ancestorW);

        return distV < distW ? ancestorV : ancestorW;
    }

    private int getAncestor(Iterable<Integer> vertexes, BreadthFirstDirectedPaths paths) {
        Queue<Integer> queue = new Queue<>();
        boolean[] marked = new boolean[digraph.V()];

        for (Integer vertex : vertexes) {
            queue.enqueue(vertex);
            marked[vertex] = true;
        }
        while (!queue.isEmpty()) {
            Integer vertex = queue.dequeue();
            for (Integer adj : digraph.adj(vertex)) {
                if (paths.hasPathTo(adj))
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
        System.out.println("Ancestor: " + sap.ancestor(10, 11)); // 0
        System.out.println("Length: " + sap.length(10, 11)); // 5
    }
}
