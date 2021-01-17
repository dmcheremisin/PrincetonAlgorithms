package org.princeton.sedgewick.wayne.part2.week1;

public class KosarajuSCC {

    private boolean[] marked; // reached vertices
    private int[] id; // component identifiers
    private int count; // number of strong components

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.getV()];
        id = new int[G.getV()];

        DigraphDepthFirstOrder order = new DigraphDepthFirstOrder(G.reverse());
        for (int s : order.reversePost())
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }
}