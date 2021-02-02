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
    }

    @Test
    void digraph2() {
        Digraph digraph = new Digraph(new In(basePath + "digraph2.txt"));
        SAP sap = new SAP(digraph);
        assertEquals(2, sap.length(1, 3));
    }

    @Test
    void digraph3() {
        Digraph digraph = new Digraph(new In(basePath + "digraph3.txt"));
        SAP sap = new SAP(digraph);
        assertEquals(8, sap.ancestor(14, 8));
        assertEquals(4, sap.length(14, 8));
    }

    @Test
    void digraph5() {
        Digraph digraph = new Digraph(new In(basePath + "digraph5.txt"));
        SAP sap = new SAP(digraph);
        assertEquals(8, sap.length(14, 21));
    }

    @Test
    void digraph6() {
        Digraph digraph = new Digraph(new In(basePath + "digraph6.txt"));
        SAP sap = new SAP(digraph);
        assertEquals(4, sap.ancestor(3, 4));
        assertEquals(1, sap.length(3, 4));
    }

}