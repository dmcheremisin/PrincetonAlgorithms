package org.princeton.sedgewick.wayne.part1.week4.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {

    private final int[] tiles;
    private final int dimension;
    private final int size;
    private Board twin;
    private int zeroPosition;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        dimension = tiles.length;
        size = dimension * dimension;
        this.tiles = new int[size];

        int k = 0;
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++) {
                if (tiles[i][j] == 0)
                    zeroPosition = k;
                this.tiles[k++] = tiles[i][j];
            }
    }

    // string representation of this board
    public String toString() {
        StringBuilder result = new StringBuilder(dimension + "\n");
        for (int i = 0; i < size; i++) {
            result.append(String.format("%2d ", tiles[i]));
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

        return Arrays.equals(tiles, that.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int zeroH = zeroPosition % dimension;
        int zeroV = zeroPosition / dimension;

        if (zeroH != 0) {
            int exchangePosition = getPositionByHV(zeroV, zeroH - 1);
            Board board = getNeighborBoard(exchangePosition);
            neighbors.add(board);
        }
        if (zeroH < dimension - 1) {
            int exchangePosition = getPositionByHV(zeroV, zeroH + 1);
            Board board = getNeighborBoard(exchangePosition);
            neighbors.add(board);
        }
        if (zeroV > 0) {
            int exchangePosition = getPositionByHV(zeroV - 1, zeroH);
            Board board = getNeighborBoard(exchangePosition);
            neighbors.add(board);
        }
        if (zeroV < dimension - 1) {
            int exchangePosition = getPositionByHV(zeroV + 1, zeroH);
            Board board = getNeighborBoard(exchangePosition);
            neighbors.add(board);
        }

        return neighbors;
    }

    private Board getNeighborBoard(int exchangePosition) {
        int[] tilesCopy = tiles.clone();
        exchange(tilesCopy, zeroPosition, exchangePosition);
        int[][] twoDimTiles = getTwoDimTiles(tilesCopy);
        return new Board(twoDimTiles);
    }

    private int getPositionByHV(int V, int H) {
        return V * dimension + H;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        if (twin != null)
            return twin;

        twin = getTwin();
        return twin;
    }

    private Board getTwin() {
        int[] tilesCopy = tiles.clone();
        int first = getRandomTile();
        int second = getRandomTile();
        while (first == second) {
            first = getRandomTile();
            second = getRandomTile();
        }
        exchange(tilesCopy, getRandomTile(), getRandomTile());
        int[][] tilesArr = getTwoDimTiles(tilesCopy);
        return new Board(tilesArr);
    }

    private int getRandomTile() {
        Random random = new Random();
        int randomPosition;
        do {
            randomPosition = random.nextInt(size);
        } while (randomPosition == zeroPosition);
        return randomPosition;
    }

    private void exchange(int[] tiles, int index1, int index2) {
        int tempTile = tiles[index1];
        tiles[index1] = tiles[index2];
        tiles[index2] = tempTile;
    }

    private int[][] getTwoDimTiles(int[] tiles) {
        int[][] tilesArr = new int[dimension][dimension];
        int[] temp = new int[dimension];

        for (int i = 0; i < size; i++) {
            int pos = i >= dimension ? i % dimension : i;
            temp[pos] = tiles[i];
            if ((i + 1) >= dimension && (i + 1) % dimension == 0) {
                tilesArr[i / dimension] = temp;
                temp = new int[dimension];
            }
        }

        return tilesArr;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        Board goal = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
        System.out.println(goal.isGoal()); // true

        Board board = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
        System.out.println(board.isGoal()); // false
        System.out.println(board.toString());
        //3
        // 8  1  3
        // 4  0  2
        // 7  6  5
        System.out.println(board.hamming()); // 5
        System.out.println(board.manhattan()); // 10

        Board board1 = new Board(new int[][]{{8, 3, 1}, {4, 0, 2}, {7, 6, 5}});
        System.out.println(board.equals(board1)); // false

        Board board2 = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
        System.out.println(board.equals(board2)); // true

        System.out.println(">>>>>>>>>> Twin");
        Board twin1 = board.twin();
        System.out.println(twin1.toString());
        //3
        // 8  1  7
        // 4  0  2
        // 3  6  5
        System.out.println(">>>>>>>>>> Twin");
        Board twin2 = board.twin();
        System.out.println(twin2.toString());
        //3
        // 8  1  7
        // 4  0  2
        // 3  6  5

        System.out.println("Twin equals: " + twin1.equals(twin2));

        System.out.println(">>>>>>>>>> Neighbors");
        for (Board neighbor : board.neighbors())
            System.out.println(neighbor.toString());
        //3
        // 8  1  3
        // 0  4  2
        // 7  6  5
        //
        //3
        // 8  1  3
        // 4  2  0
        // 7  6  5
        //
        //3
        // 8  0  3
        // 4  1  2
        // 7  6  5
        //
        //3
        // 8  1  3
        // 4  6  2
        // 7  0  5

        Board twin3 = board.twin();
        System.out.println(twin3.toString());
        //3
        // 8  1  7
        // 4  0  2
        // 3  6  5

        System.out.println("Twin equals: " + twin1.equals(twin3));

    }

}
