package org.princeton.sedgewick.wayne.week4.challenge;

import java.util.Random;

public class Board {

    private final int[] tiles;
    private final int dimension;
    private final int size;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        dimension = tiles.length;
        size = dimension * dimension;
        this.tiles = new int[size];

        int k = 0;
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                this.tiles[k++] = tiles[i][j];
    }

    public int[] getTiles() {
        return tiles;
    }

    // string representation of this board
    public String toString() {
        StringBuilder result = new StringBuilder(dimension + "\n");
        for (int i = 0; i < size; i++) {
            result.append(String.format("%1$4s", tiles[i]));
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
            if (tiles[i] != 0 && tiles[i] != i + 1)
                count++;
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int count = 0;
        for (int position = 0; position < size; position++) {
            int tile = tiles[position];
            if (tile == 0)
                continue;

            int tileV = (tile - 1) / dimension;
            int tileH = (tile - 1) % dimension;
            int actualV = position / dimension;
            int actualH = position % dimension;

            int vertical = Math.abs(tileV - actualV);
            int horizontal = Math.abs(tileH - actualH) == 0 ? 0 : Math.abs(tileH - actualH);

            int manh = vertical + horizontal;
            //System.out.println(String.format("Tile: %s, position: %s, Manhattan: %s", tile, position, manh));
            count += manh;
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (!(y instanceof Board))
            return false;

        Board that = (Board) y;

        if (this.dimension() != that.dimension())
            return false;

        int[] thatTiles = that.getTiles();

        for (int i = 0; i < that.dimension; i++)
            if (tiles[i] != thatTiles[i])
                return false;

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[] tilesCopy = new int[size];
        System.arraycopy(tiles, 0, tilesCopy, 0, size);
        Random random = new Random();
        int exchange1 = random.nextInt(size - 1);
        int exchange2 = random.nextInt(size - 1);
        int tempTile = tilesCopy[exchange1];
        tilesCopy[exchange1] = tilesCopy[exchange2];
        tilesCopy[exchange2] = tempTile;

        int[][] tilesArr = new int[dimension][dimension];
        int[] temp = new int[dimension];
        for (int i = 0; i < size; i++) {
            int pos = i >= dimension ? i % dimension : i;
            temp[pos] = tilesCopy[i];
            if ((i + 1) >= dimension && (i + 1) % dimension == 0) {
                tilesArr[i / dimension] = temp;
                temp = new int[dimension];
            }
        }

        return new Board(tilesArr);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        Board board = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
        System.out.println(board.toString());
        System.out.println(board.hamming()); // 5
        System.out.println(board.manhattan()); // 10

        Board board1 = new Board(new int[][]{{8, 3, 1}, {4, 0, 2}, {7, 6, 5}});
        System.out.println(board.equals(board1)); // false

        Board board2 = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
        System.out.println(board.equals(board2)); // true

        Board twin = board.twin();
        System.out.println(twin.toString());
    }

}
