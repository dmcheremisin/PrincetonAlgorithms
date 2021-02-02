package org.princeton.sedgewick.wayne.part2.week1.challenge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class WordNetTest {

    private static final String basePath =
            "C:\\Workspace\\PrincetonAlgorithms\\src\\main\\resources\\part2\\week1\\challenge\\";


    @Test
    public void testCycle1() {
        assertThrows(IllegalArgumentException.class,
                () -> new WordNet(basePath + "synsets3.txt", basePath + "hypernyms3InvalidCycle.txt"));
    }

    @Test
    public void testCycle2() {
        assertThrows(IllegalArgumentException.class,
                () -> new WordNet(basePath + "synsets6.txt", basePath + "hypernyms6InvalidCycle.txt"));
    }

}