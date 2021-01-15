package org.princeton.sedgewick.wayne.part2.week1;

import edu.princeton.cs.algs4.In;

import java.util.List;

public class DirectedDepthFirstSearch {

    private final Digraph digraph;
    private final boolean[] marked;

    public DirectedDepthFirstSearch(Digraph digraph, int s) {
        this.digraph = digraph;
        marked = new boolean[digraph.getV()];
        dfs(s);
    }

    public DirectedDepthFirstSearch(Digraph digraph, Iterable<Integer> sources) {
        this.digraph = digraph;
        marked = new boolean[digraph.getV()];
        for (Integer s : sources)
            if (!marked[s])
                dfs(s);
    }

    private void dfs(int s) {
        System.out.printf("Visiting vertex: %s \n", s);
        marked[s] = true;
        for (Integer w : digraph.adj(s))
            if (!marked[w])
                dfs(w);
    }

    public boolean marked(int s) {
        return marked[s];
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));
        DirectedDepthFirstSearch dfs1 = new DirectedDepthFirstSearch(digraph, 0);
        //Visiting vertex: 0
        //Visiting vertex: 6
        //Visiting vertex: 4
        //Visiting vertex: 3
        //Visiting vertex: 2
        //Visiting vertex: 1
        //Visiting vertex: 5
        System.out.println(dfs1.marked(3)); // true
        System.out.println(dfs1.marked(11)); // false

        DirectedDepthFirstSearch dfs2 = new DirectedDepthFirstSearch(digraph, List.of(8, 9));
        //Visiting vertex: 8
        //Visiting vertex: 9
        //Visiting vertex: 11
        //Visiting vertex: 12
        //Visiting vertex: 10
    }

}
