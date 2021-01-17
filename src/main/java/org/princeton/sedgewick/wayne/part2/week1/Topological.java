package org.princeton.sedgewick.wayne.part2.week1;

public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph digraph) {
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        if (directedCycle.hasCycle())
            return;

        DigraphDepthFirstOrder depthFirstOrder = new DigraphDepthFirstOrder(digraph);
        order = depthFirstOrder.reversePost();
    }

    public boolean hasOrder() {
        return order != null;
    }

    public Iterable<Integer> getOrder() {
        return order;
    }

    public static void main(String[] args) {
        SymbolDigraph sg = new SymbolDigraph(args[0], args[1]);

        Topological topological = new Topological(sg.getDigraph());
        if (topological.hasOrder())
            for (Integer v : topological.getOrder())
                System.out.println(sg.getVertexName(v));
        //JFK
        //ORD
        //DEN
        //LAS
        //DFW
        //PHX
        //LAX
        //ATL
        //HOU
        //MCO
    }
}
