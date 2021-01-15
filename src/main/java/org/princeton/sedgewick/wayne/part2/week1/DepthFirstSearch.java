package org.princeton.sedgewick.wayne.part2.week1;

import edu.princeton.cs.algs4.In;

public class DepthFirstSearch {

    private final Graph g;
    private final boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph g, int s) {
        this.g = g;
        marked = new boolean[g.getV()];
        dfs(s);
    }

    private void dfs(int s) {
        marked[s] = true;
        System.out.printf("Visiting vertex: %s \n", s);
        count++;
        for (Integer adj : g.adj(s))
            if (!marked[adj])
                dfs(adj);
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

    public void isConnected() {
        if (count != g.getV())
            System.out.print("NOT ");
        System.out.println("connected");
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(args[0]));
        DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);
        //Visiting vertex: 0
        //Visiting vertex: 6
        //Visiting vertex: 4
        //Visiting vertex: 5
        //Visiting vertex: 3
        //Visiting vertex: 2
        //Visiting vertex: 1
        System.out.println(dfs.count()); // 7
        System.out.println(dfs.marked(3)); // true
        System.out.println(dfs.marked(11)); // false
        dfs.isConnected();
        // NOT connected
    }
}
