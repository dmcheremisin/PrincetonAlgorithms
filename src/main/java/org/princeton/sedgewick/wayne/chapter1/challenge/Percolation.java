package org.princeton.sedgewick.wayne.chapter1.challenge;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int N;
    private int fictiveTop;
    private int fictiveBottom;
    private boolean[] openSites;
    private int openCounter = 0;
    private WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n should be greater than 0");

        N = n;
        int size = n * n;
        int length = size + 2; // 2 additional elements are fictive
        fictiveTop = size;
        fictiveBottom = size + 1;

        openSites = new boolean[size];
        for (int i = 0; i < size; i++)
            openSites[i] = false;

        uf = new WeightedQuickUnionUF(length);

        for (int i = 0; i < N; i++) // connect fictive top
            uf.union(fictiveTop, i);

        for (int i = size - N; i < size; i++) // connect fictive bottom
            uf.union(fictiveBottom, i);

    }

    // test client (optional)
    public static void main(String[] args) {
        int N = 7;
        Percolation percolation = getUntilPercolates(N);
        System.out.println(percolation.numberOfOpenSites());
        System.out.println(N * N);
        System.out.println(percolation.numberOfOpenSites() * 100 / (N * N));
    }

    public static Percolation getUntilPercolates(int n) {
        Percolation percolation = new Percolation(n);

        while (!percolation.percolates()) {
            int row = StdRandom.uniform(n);
            int col = StdRandom.uniform(n);
            percolation.open(row, col);
        }

        return percolation;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col))
            return;

        int index = getIndex(row, col);

        if (row - 1 > 0 && isOpen(getIndex(row - 1, col))) // top
            uf.union(index, getIndex(row - 1, col));
        if (row + 1 < N && isOpen(getIndex(row + 1, col))) // bottom
            uf.union(index, getIndex(row + 1, col));
        if (col - 1 > 0 && isOpen(getIndex(row, col - 1))) // left
            uf.union(index, getIndex(row, col - 1));
        if (col + 1 < N && isOpen(getIndex(row, col + 1))) // right
            uf.union(index, getIndex(row, col + 1));

        openSites[index] = true;
        openCounter++;
    }

    private int getIndex(int row, int col) {
        return row * N + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return openSites[getIndex(row, col)];
    }

    private boolean isOpen(int index) {
        return openSites[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCounter;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(fictiveTop, fictiveBottom);
    }
}
