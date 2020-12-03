package org.princeton.sedgewick.wayne.week4.challenge;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {

    private MinPQ<Board> queue;
    private List<Board> solution;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException("Board must not be null");

        queue = getQueue();
        solution = new ArrayList<>();

        queue.insert(initial);
        while (true) {
            Board board = queue.delMin();
            solution.add(board);
            if (board.isGoal())
                break;

            Iterable<Board> neighbors = board.neighbors();
            queue = getQueue();
            for (Board neighbor : neighbors) {
//                boolean isDuplicate = false;
//                for (Board solutionBoard : solution) {
//                    if (neighbor.equals(solutionBoard)) {
//                        isDuplicate = true;
//                        break;
//                    }
//                }
//                if (!isDuplicate)
                    queue.insert(neighbor);
            }
        }
    }

    private MinPQ<Board> getQueue() {
        return new MinPQ<>(Comparator.comparingInt(Board::manhattan));
    }


    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return true;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return solution.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solution;
    }

    // test client (see below) 
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}