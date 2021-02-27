package org.princeton.sedgewick.wayne.part2.week4.challenge;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.TST;

import java.util.HashSet;
import java.util.Set;

public class BoggleSolver {

    private final TST<Object> words = new TST<>();

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        for (String word : dictionary)
            words.put(word, new Object());
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        int rows = board.rows();
        int cols = board.cols();
        Set<String> validWords = new HashSet<>();

        Queue<String> prefixes = new Queue<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char letter = board.getLetter(i, j);
            }
        }
        return null;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        return 0;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] strings = in.readAllStrings();
        BoggleSolver boggleSolver = new BoggleSolver(strings);

    }
}