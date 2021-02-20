package org.princeton.sedgewick.wayne.part2.week3.minCutMaxFlow.challenge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseballEliminationTest {

    private static final String basePath =
            "C:\\Workspace\\PrincetonAlgorithms\\src\\main\\resources\\part2\\week3\\challenge\\";

    @Test
    void team4aElemination() {
        BaseballElimination elimination = new BaseballElimination(basePath + "teams4a.txt");
        assertNotNull(elimination.certificateOfElimination("Bin_Ladin"));
    }
}