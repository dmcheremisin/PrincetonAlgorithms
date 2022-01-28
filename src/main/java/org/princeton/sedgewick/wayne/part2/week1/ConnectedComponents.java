package org.princeton.sedgewick.wayne.part2.week1;

import edu.princeton.cs.algs4.In;

public class ConnectedComponents {

    private final Graph g;
    private final boolean[] marked;
    private final int[] id;
    private int count;

    public ConnectedComponents(Graph g) {
        this.g = g;
        marked = new boolean[g.getV()];
        id = new int[g.getV()];
        for(int s = 0; s < g.getV(); s++) {
            if(!marked[s]) {
                dfs(s);
                count++;
            }
        }
    }

    private void dfs(int v) {
        marked[v] = true;
        id[v] = count;
        System.out.printf("Vertex: %s ; component: %s \n", v, count);
        for (Integer adj : g.adj(v))
            if (!marked[adj])
                dfs(adj);
    }

    public boolean connected(int i, int j) {
        return id[i] == id[j];
    }

    public int getComponentId(int i) {
        return id[i];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(args[0]));
        ConnectedComponents cc = new ConnectedComponents(graph);
        //Vertex: 0 ; component: 0
        //Vertex: 6 ; component: 0
        //Vertex: 4 ; component: 0
        //Vertex: 5 ; component: 0
        //Vertex: 3 ; component: 0
        //Vertex: 2 ; component: 0
        //Vertex: 1 ; component: 0
        //Vertex: 7 ; component: 1
        //Vertex: 8 ; component: 1
        //Vertex: 9 ; component: 2
        //Vertex: 11 ; component: 2
        //Vertex: 12 ; component: 2
        //Vertex: 10 ; component: 2
        System.out.println(cc.count()); // 3
        System.out.println(cc.connected(0, 6)); // true
        System.out.println(cc.connected(5, 10)); // false
        System.out.println(cc.getComponentId(8)); // 1
    }
}
