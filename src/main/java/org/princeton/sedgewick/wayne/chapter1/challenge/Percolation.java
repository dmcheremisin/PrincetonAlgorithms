package org.princeton.sedgewick.wayne.chapter1.challenge;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int N;
    private final int fictiveTop;
    private final int fictiveBottom;
    private final boolean[] openSites;
    private final WeightedQuickUnionUF uf;
    private int openCounter;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n should be greater than 0");

        N = n;
        int size = n * n;
        fictiveTop = size;
        fictiveBottom = size + 1;

        openSites = new boolean[size];
        for (int i = 0; i < size; i++)
            openSites[i] = false;

        uf = new WeightedQuickUnionUF(size + 2); // with 2 fictive sites(top & bottom)

        for (int i = 0; i < N; i++) // connect fictive top
            uf.union(fictiveTop, i);

        for (int i = size - N; i < size; i++) // connect fictive bottom
            uf.union(fictiveBottom, i);
    }

    // test client (optional)
    public static void main(String[] args) {
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col))
            return;
        row--;
        col--;

        int index = getIndex(row, col);

        if (row - 1 > 0 && getOpen(row - 1, col)) // top
            uf.union(index, getIndex(row - 1, col));

        if (row + 1 < N && getOpen(row + 1, col)) // bottom
            uf.union(index, getIndex(row + 1, col));

        if (col - 1 > 0 && getOpen(row, col - 1)) // left
            uf.union(index, getIndex(row, col - 1));

        if (col + 1 < N && getOpen(row, col + 1)) // right
            uf.union(index, getIndex(row, col + 1));

        openSites[index] = true;
        openCounter++;
    }

    private int getIndex(int row, int col) {
        return row * N + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return getOpen(row - 1, col - 1);
    }

    private boolean getOpen(int row, int col) {
        checkIndex(row, col);
        int index = getIndex(row, col);
        return openSites[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkIndex(row - 1, col - 1);
        return false; // I don't need this method
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCounter;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(fictiveTop, fictiveBottom);
    }

    private void checkIndex(int row, int col) {
        if (row < 0 || row >= N)
            throw new IllegalArgumentException("Row is out of range");

        if (col < 0 || col >= N)
            throw new IllegalArgumentException("Column is out of range");
    }
}
