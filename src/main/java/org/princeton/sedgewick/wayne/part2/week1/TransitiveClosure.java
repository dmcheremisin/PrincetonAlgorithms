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
        int jfk = sg.getVertexByName("JFK");
        int atl = sg.getVertexByName("ATL");
        int den = sg.getVertexByName("DEN");
        int lax = sg.getVertexByName("LAX");
        System.out.println(closure.reachable(jfk, atl)); // true
        System.out.println(closure.reachable(jfk, den)); // true
        System.out.println(closure.reachable(jfk, lax)); // true
        System.out.println(closure.reachable(lax, jfk)); // false
        System.out.println(closure.reachable(atl, den)); // false
    }
}
