package org.princeton.sedgewick.wayne.part2.week4.challenge;

import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoggleSolverTest {

    private String BASE_PATH = "C:\\Workspace\\PrincetonAlgorithms\\src\\main\\resources\\part2\\week4\\challenge\\";

    private BoggleSolver getBoggleSolver(String dictionary) {
        System.out.println("Dictionary: " + dictionary);
        String[] strings = new In(BASE_PATH + dictionary).readAllStrings();
        return new BoggleSolver(strings);
    }

    private int getScore(BoggleSolver boggleSolver, Set<String> allValidWords) {
        return allValidWords.stream()
                .mapToInt(boggleSolver::scoreOf)
                .sum();
    }

    private void checkSize(int size, int expectedSize) {
        System.out.println("Size: " + size);
        assertEquals(expectedSize, size);
    }

    private void checkScore(BoggleSolver boggleSolver, Set<String> allValidWords, int expectedScore) {
        int score = getScore(boggleSolver, allValidWords);
        System.out.println("Score: " + score);
        assertEquals(expectedScore, score);
    }

    private BoggleBoard getBoggleBoard(String board) {
        System.out.println("Board: " + board);
        return new BoggleBoard(BASE_PATH + board);
    }

    @Test
    void getAllValidWords1() {
        BoggleSolver boggleSolver = getBoggleSolver("dictionary-algs4.txt");
        BoggleBoard boggleBoard = getBoggleBoard("board4x4.txt");
        Set<String> allValidWords = (Set<String>) boggleSolver.getAllValidWords(boggleBoard);

        checkSize(allValidWords.size(), 29);
        checkScore(boggleSolver, allValidWords,  33);
    }

    @Test
    void getAllValidWords2() {
        BoggleSolver boggleSolver = getBoggleSolver("dictionary-algs4.txt");
        BoggleBoard boggleBoard = getBoggleBoard("board-q.txt");
        Set<String> allValidWords = (Set<String>) boggleSolver.getAllValidWords(boggleBoard);

        checkSize(allValidWords.size(), 29);
        checkScore(boggleSolver, allValidWords, 84);
    }

    @Test
    void getAllValidWords3() {
        BoggleSolver boggleSolver = getBoggleSolver("dictionary-yawl.txt");
        BoggleBoard boggleBoard = getBoggleBoard("board4x4.txt");
        Set<String> allValidWords = (Set<String>) boggleSolver.getAllValidWords(boggleBoard);

        checkSize(allValidWords.size(), 204);
    }

    @Test
    void getAllValidWords4() {
        BoggleSolver boggleSolver = getBoggleSolver("dictionary-yawl.txt");
        BoggleBoard boggleBoard = getBoggleBoard("board-points2.txt");
        Set<String> allValidWords = (Set<String>) boggleSolver.getAllValidWords(boggleBoard);

        checkSize(allValidWords.size(), 2);
        checkScore(boggleSolver, allValidWords, 2);
    }

    @Test
    void getAllValidWords5() {
        BoggleSolver boggleSolver = getBoggleSolver("dictionary-yawl.txt");
        BoggleBoard boggleBoard = getBoggleBoard("board-points3.txt");
        Set<String> allValidWords = (Set<String>) boggleSolver.getAllValidWords(boggleBoard);

        checkSize(allValidWords.size(), 3);
        checkScore(boggleSolver, allValidWords, 3);
    }

    @Test
    void getAllValidWords6() {
        BoggleSolver boggleSolver = getBoggleSolver("dictionary-yawl.txt");
        BoggleBoard boggleBoard = getBoggleBoard("board-points5.txt");
        Set<String> allValidWords = (Set<String>) boggleSolver.getAllValidWords(boggleBoard);

        checkSize(allValidWords.size(), 5);
        checkScore(boggleSolver, allValidWords, 5);
    }

    @Test
    void getAllValidWords7() {
        BoggleSolver boggleSolver = getBoggleSolver("dictionary-16q.txt");
        BoggleBoard boggleBoard = getBoggleBoard("board-16q.txt");
        Set<String> allValidWords = (Set<String>) boggleSolver.getAllValidWords(boggleBoard);

        checkSize(allValidWords.size(), 15);
    }

}