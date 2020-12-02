package org.princeton.sedgewick.wayne.week4.challenge;

public class Board {

    private final int[] board;
    private final int dimension;
    private final int size;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        dimension = tiles.length;
        size = dimension * dimension;
        board = new int[size];

        int k = 0;
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                board[k++] = tiles[i][j];
    }

    // string representation of this board
    public String toString() {
        StringBuilder result = new StringBuilder(dimension + "\n");
        for (int i = 0; i < size; i++) {
            result.append(String.format("%1$4s", board[i]));
            if ((i + 1) % dimension == 0)
                result.append("\n");
        }

        return result.toString();
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (board[i] != i + 1)
                count++;
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            int tile = board[i];
            if (tile == 0)
                continue;
            int position = i;
            int tileV = (tile - 1) / dimension;
            int tileH = (tile - 1) % dimension;
            int actualV = position / dimension;
            int actualH = position % dimension;
            int vertical = Math.abs(tileV - actualV);
            int horizontal = Math.abs(tileH - actualH) == 0 ? 0 : Math.abs(tileH - actualH);
            int manh = vertical + horizontal;
            System.out.println(String.format("Tile: %s, position: %s, Manhattan: %s", tile, i, manh));
        }
        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return false;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        Board board = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
        System.out.println(board.toString());
        System.out.println(board.hamming());
        board.manhattan();
    }

}
