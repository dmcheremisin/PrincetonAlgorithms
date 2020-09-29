package org.princeton.sedgewick.wayne.chapter1.unionFind;

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
        for (int i = 0; i < N; i++) {
            int p = new Random().nextInt(N);
            int q = new Random().nextInt(N);
            System.out.println("Union p = " + p + "; q = " + q);
            quickUnion.union(p, q);
        }
        //Union p = 11; q = 8
        //Union p = 1; q = 17
        //Union p = 7; q = 7
        //Union p = 10; q = 8
        //Union p = 1; q = 4
        //Union p = 15; q = 1
        //Union p = 0; q = 3
        //Union p = 19; q = 9
        //Union p = 6; q = 10
        //Union p = 12; q = 14
        //Union p = 12; q = 0
        //Union p = 0; q = 7
        //Union p = 6; q = 12
        //Union p = 0; q = 15
        //Union p = 9; q = 3
        //Union p = 15; q = 12
        //Union p = 16; q = 18
        //Union p = 0; q = 15
        //Union p = 1; q = 16
        //Union p = 3; q = 2

        IntStream.range(0, N).forEach(i -> System.out.printf("%1$3s", i));
        System.out.println();
        Arrays.stream(quickUnion.id).forEach(i -> System.out.printf("%1$3s", i));
        System.out.println();
        //  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
        // 12 12 12  0  1  5 11 12 11 19 11 12 12 13 12  1 12  1 16 12

        System.out.println("Connected 3 & 7 ? Result = " + quickUnion.connected(3, 7));
        System.out.println("Connected 0 & 4 ? Result = " + quickUnion.connected(0, 4));
        System.out.println("Connected 5 & 9 ? Result = " + quickUnion.connected(5, 9));
        //Connected 3 & 7 ? Result = true
        //Connected 0 & 4 ? Result = true
        //Connected 5 & 9 ? Result = false
    }
}
