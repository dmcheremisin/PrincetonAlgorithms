package org.princeton.sedgewick.wayne.part2.week1.challenge;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SAPTest {

    private static final String basePath =
            "C:\\Workspace\\PrincetonAlgorithms\\src\\main\\resources\\part2\\week1\\challenge\\";

    @Test
    void digraph1() {
        Digraph digraph = new Digraph(new In(basePath + "digraph1.txt"));
        SAP sap = new SAP(digraph);
        assertEquals(4, sap.length(12, 0));
        assertEquals(0, sap.ancestor(12, 0));

        //assertEquals(3, sap.length(3, 8));
    }

    @Test
    void digraph2() {
        Digraph digraph = new Digraph(new In(basePath + "digraph2.txt"));
        SAP sap = new SAP(digraph);
        assertEquals(3, sap.length(2, 5));
        assertEquals(5, sap.ancestor(2, 5));

//        assertEquals(3, sap.length(2, 5));
//        assertEquals(5, sap.ancestor(2, 5));
    }

}