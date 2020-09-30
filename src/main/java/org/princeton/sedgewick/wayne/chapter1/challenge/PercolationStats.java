package org.princeton.sedgewick.wayne.chapter1.challenge;

import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int T;
    private double[] openSites;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n should be greater than 0");

        T = trials;
        double size = n * n;
        openSites = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = Percolation.getUntilPercolates(n);
            openSites[i] = percolation.numberOfOpenSites() / size;
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(200, 100);
        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.print("95% confidence interval = [" + percolationStats.confidenceLo() + ", ");
        System.out.println(percolationStats.confidenceHi() + "]");
        //mean = 0.5926019999999999
        //stddev = 0.009986745432994394
        //95% confidence interval = [0.5906445978951329, 0.5945594021048668]
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openSites);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(openSites);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }
}
