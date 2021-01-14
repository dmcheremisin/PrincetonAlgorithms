package org.princeton.sedgewick.wayne.part1.week4.challenge;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.LinkedList;

public class Solver {

    private final Board initialBoard;
    private boolean isSolvable;
    private boolean isTwinSolved;
    private SearchNode currentNode;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException("Board must not be null");
        initialBoard = initial;

        isSolvable();
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        if (isSolvable)
            return true;

        if (isTwinSolved)
            return false;

        Board twinBoard = initialBoard.twin();

        MinPQ<SearchNode> queue = getQueue();
        MinPQ<SearchNode> twinQueue = getQueue();

        queue.insert(new SearchNode(initialBoard, null, 0));
        twinQueue.insert(new SearchNode(twinBoard, null, 0));

        while (!isSolvable && !isTwinSolved) {
            isSolvable = addBoard(queue, false);
            isTwinSolved = addBoard(twinQueue, true);
        }

        return isSolvable;
    }

    private boolean addBoard(MinPQ<SearchNode> queue, boolean isTwin) {
        SearchNode searchNode = queue.delMin();
        if (!isTwin)
            currentNode = searchNode;

        Board board = searchNode.getBoard();
        if (board.isGoal())
            return true;

        SearchNode previousNode = searchNode.getPreviousNode();
        Board previousBoard = null;
        if (previousNode != null)
            previousBoard = previousNode.getBoard();

        int newMoves = searchNode.getMoves() + 1;
        Iterable<Board> neighbors = board.neighbors();
        for (Board neighbor : neighbors) {
            if (!neighbor.equals(previousBoard))
                queue.insert(new SearchNode(neighbor, searchNode, newMoves));
        }
        return false;
    }

    private MinPQ<SearchNode> getQueue() {
        return new MinPQ<>(Comparator.comparingInt(SearchNode::priority));
    }

    private boolean isNotSolvable() {
        return isTwinSolved || !isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isNotSolvable())
            return -1;
        return currentNode.getMoves();
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (isNotSolvable())
            return null;

        LinkedList<Board> solution = new LinkedList<>();
        SearchNode node = currentNode;
        solution.push(node.getBoard());
        while ((node = node.getPreviousNode()) != null)
            solution.push(node.getBoard());

        return solution;
    }

    private static class SearchNode {
        private final int moves;
        private final Board board;
        private final SearchNode previousNode;

        public SearchNode(Board board, SearchNode previousNode, int moves) {
            this.moves = moves;
            this.board = board;
            this.previousNode = previousNode;
        }

        public int priority() {
            return moves + board.manhattan();
        }

        public Board getBoard() {
            return board;
        }

        public SearchNode getPreviousNode() {
            return previousNode;
        }

        public int getMoves() {
            return moves;
        }
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
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
            StdOut.println(solver.solution());
        }
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}