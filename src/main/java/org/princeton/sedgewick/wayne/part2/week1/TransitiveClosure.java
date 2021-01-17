package org.princeton.sedgewick.wayne.part2.week1;

public class TransitiveClosure {

    private DirectedDepthFirstSearch[] all;

    public TransitiveClosure(Digraph G) {
        all = new DirectedDepthFirstSearch[G.getV()];
        for (int v = 0; v < G.getV(); v++)
            all[v] = new DirectedDepthFirstSearch(G, v);
    }

    public boolean reachable(int v, int w) {
        return all[v].marked(w);
    }

    public static void main(String[] args) {
        SymbolDigraph sg = new SymbolDigraph(args[0], args[1]);
        TransitiveClosure closure = new TransitiveClosure(sg.getDigraph());
        int v = sg.getVertexByName("JFK");
        int w = sg.getVertexByName("ATL");
        int k = sg.getVertexByName("DEN");
        System.out.println(closure.reachable(v, w)); // true
        System.out.println(closure.reachable(v, k)); // true
        System.out.println(closure.reachable(w, k)); // false
    }
}
