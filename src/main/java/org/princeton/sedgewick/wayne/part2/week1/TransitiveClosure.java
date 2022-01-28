package org.princeton.sedgewick.wayne.part2.week1;

public class TransitiveClosure {

    private DirectedDepthFirstSearch[] all;

    public TransitiveClosure(Digraph digraph) {
        all = new DirectedDepthFirstSearch[digraph.V()];
        for (int v = 0; v < digraph.V(); v++)
            all[v] = new DirectedDepthFirstSearch(digraph, v);
    }

    public boolean reachable(int v, int w) {
        return all[v].marked(w);
    }

    public static void main(String[] args) {
        SymbolDigraph sd = new SymbolDigraph(args[0], args[1]);
        int jfk = sd.getVertexByName("JFK");
        int atl = sd.getVertexByName("ATL");
        int den = sd.getVertexByName("DEN");
        int lax = sd.getVertexByName("LAX");

        TransitiveClosure closure = new TransitiveClosure(sd.getG());
        System.out.println(closure.reachable(jfk, atl)); // true
        System.out.println(closure.reachable(jfk, den)); // true
        System.out.println(closure.reachable(jfk, lax)); // true
        System.out.println(closure.reachable(lax, jfk)); // false
        System.out.println(closure.reachable(atl, den)); // false
    }
}
