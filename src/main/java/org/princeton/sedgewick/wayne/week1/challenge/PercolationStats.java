package org.princeton.sedgewick.wayne.week1.challenge;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_CONST = 1.96;
    private final int T;
    private final double[] openSites;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n should be greater than 0");

        T = trials;
        double size = n * n;
        openSites = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = getUntilPercolates(n);
            openSites[i] = percolation.numberOfOpenSites() / size;
        }
    }

    private Percolation getUntilPercolates(int n) {
        Percolation percolation = new Percolation(n);

        while (!percolation.percolates()) {
            int row = 1 + StdRandom.uniform(n);
            int col = 1 + StdRandom.uniform(n);
            percolation.open(row, col);
        }

        return percolation;
    }


    // test client (see below)
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(N, T);
        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.print("95% confidence interval = [" + percolationStats.confidenceLo() + ", ");
        System.out.println(percolationStats.confidenceHi() + "]");
        // mean = 0.5926019999999999
        // stddev = 0.009986745432994394
        // 95% confidence interval = [0.5906445978951329, 0.5945594021048668]
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
        return mean() - (CONFIDENCE_CONST * stddev() / Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_CONST * stddev() / Math.sqrt(T));
    }
}
