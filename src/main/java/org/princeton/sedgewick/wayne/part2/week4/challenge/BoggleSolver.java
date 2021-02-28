package org.princeton.sedgewick.wayne.part2.week4.challenge;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.TST;

import java.util.HashSet;
import java.util.Set;

public class BoggleSolver {

    private final TST<Object> words = new TST<>();
    private final Set<String> wordSet = new HashSet<>();

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        for (String word : dictionary) {
            words.put(word, new Object());
            wordSet.add(word);
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        int rows = board.rows();
        int cols = board.cols();
        Set<String> validWords = new HashSet<>();

        boolean[][] visited = new boolean[rows][];
        for (int i = 0; i < rows; i++)
            visited[i] = new boolean[cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String prefix = getNewPrefixWithQ(board, row, col, "");
                addValidNeighbors(row, col, copy2dArray(visited), prefix, board, validWords);
            }
        }

        return validWords;
    }

    private String getNewPrefixWithQ(BoggleBoard board, int row, int col, String prefix) {
        char letter = board.getLetter(row, col);
        return letter == 'Q' ? prefix + letter + 'U' : prefix + letter;
    }

    private void addValidNeighbors(int row, int col, boolean[][] visited, String prefix,
                                   BoggleBoard board, Set<String> validWords) {
        visited[row][col] = true;

        if (prefix.length() >= 3 && wordSet.contains(prefix))
            validWords.add(prefix);

        for (int i = -1; i < 2; i++) {
            int iRow = row + i;
            if (iRow < 0 || iRow >= board.rows())
                continue;

            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0)
                    continue;

                int jCol = col + j;
                if (jCol < 0 || jCol >= board.cols())
                    continue;

                if (visited[iRow][jCol])
                    continue;

                String newPrefix = getNewPrefixWithQ(board, iRow, jCol, prefix);

                Iterable<String> keysWithPrefix = words.keysWithPrefix(newPrefix);
                if (((Queue<String>) keysWithPrefix).size() > 0)
                    addValidNeighbors(iRow, jCol, copy2dArray(visited), newPrefix, board, validWords);
            }
        }
    }

    private boolean[][] copy2dArray(boolean[][] arr) {
        boolean[][] newArr = new boolean[arr.length][];
        for (int i = 0; i < arr.length; i++)
            newArr[i] = arr[i].clone();
        return newArr;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (!words.contains(word))
            return 0;

        int length = word.length();
        if (length < 3)
            return 0;
        else if (length <= 4)
            return 1;
        else if (length == 5)
            return 2;
        else if (length == 6)
            return 3;
        else if (length == 7)
            return 5;

        return 11;
    }

    public static void main(String[] args) {
        String basePath = "C:\\Workspace\\PrincetonAlgorithms\\src\\main\\resources\\part2\\week4\\challenge\\";
        String[] strings = new In(basePath + args[0]).readAllStrings();
        BoggleSolver boggleSolver = new BoggleSolver(strings);
        BoggleBoard boggleBoard = new BoggleBoard(basePath + args[1]);
        Set<String> allValidWords = (Set<String>) boggleSolver.getAllValidWords(boggleBoard);
        allValidWords.forEach(System.out::println);
        System.out.println("Size: " + allValidWords.size());

        int score = allValidWords.stream()
                .mapToInt(boggleSolver::scoreOf)
                .sum();
        System.out.println("Score: " + score);
    }
}