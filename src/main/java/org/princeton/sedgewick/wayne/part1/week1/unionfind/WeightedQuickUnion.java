package org.princeton.sedgewick.wayne.part1.week1.unionfind;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class WeightedQuickUnion {

    int[] id;
    int[] sizes;

    public WeightedQuickUnion(int N) {
        id = new int[N];
        sizes = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sizes[i] = 1;
        }
    }

    private int root(int i) {
        while (id[i] != i)
            i = id[i];

        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);

        if (rootP == rootQ)
            return;

        // smaller root should be linked to larger root
        if (sizes[rootP] < sizes[rootQ]) {
            id[rootP] = rootQ;
            sizes[rootQ] += sizes[rootP];
        } else {
            id[rootQ] = rootP;
            sizes[rootP] += sizes[rootQ];
        }
    }
}

class WeightedQuickUnionTest {

    public static void main(String[] args) {
        int N = 20;
        WeightedQuickUnion quickUnion = new WeightedQuickUnion(N);
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            int p = random.nextInt(N);
            int q = random.nextInt(N);
            System.out.println("Union p = " + p + "; q = " + q);
            quickUnion.union(p, q);
        }
        //Union p = 18; q = 13
        //Union p = 8; q = 4
        //Union p = 19; q = 19
        //Union p = 9; q = 3
        //Union p = 1; q = 0
        //Union p = 11; q = 6
        //Union p = 17; q = 18
        //Union p = 13; q = 17
        //Union p = 16; q = 10
        //Union p = 0; q = 13
        //Union p = 1; q = 9
        //Union p = 12; q = 12
        //Union p = 7; q = 14
        //Union p = 17; q = 10
        //Union p = 3; q = 7
        //Union p = 18; q = 2
        //Union p = 0; q = 14
        //Union p = 16; q = 8
        //Union p = 16; q = 18
        //Union p = 11; q = 5

        IntStream.range(0, N).forEach(i -> System.out.printf("%1$3d", i));
        System.out.println();
        Arrays.stream(quickUnion.id).forEach(i -> System.out.printf("%1$3d", i));
        System.out.println();
        //  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
        //  1 18 18  9  8 11 11 18 18 18 16 11 12 18  7 15 18 18 18 19

        System.out.println("Connected 3 & 7 ? Result = " + quickUnion.connected(3, 7));
        System.out.println("Connected 0 & 4 ? Result = " + quickUnion.connected(0, 4));
        System.out.println("Connected 5 & 9 ? Result = " + quickUnion.connected(5, 9));
        //Connected 3 & 7 ? Result = true
        //Connected 0 & 4 ? Result = true
        //Connected 5 & 9 ? Result = false
    }
}
