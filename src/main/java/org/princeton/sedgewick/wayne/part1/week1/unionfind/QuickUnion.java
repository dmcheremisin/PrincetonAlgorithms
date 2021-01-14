package org.princeton.sedgewick.wayne.part1.week1.unionfind;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class QuickUnion {

    int[] id;

    public QuickUnion(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
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
        id[rootP] = rootQ;
    }
}

class QuickUnionTest {

    public static void main(String[] args) {
        int N = 10;
        QuickUnion quickUnion = new QuickUnion(N);
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int p = random.nextInt(N);
            int q = random.nextInt(N);
            System.out.println("Union p = " + p + "; q = " + q);
            quickUnion.union(p, q);
        }
        //Union p = 7; q = 9
        //Union p = 4; q = 8
        //Union p = 8; q = 5
        //Union p = 3; q = 1
        //Union p = 0; q = 8

        IntStream.range(0, N).forEach(i -> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(quickUnion.id).forEach(i -> System.out.print(i + " "));
        System.out.println();
        //0 1 2 3 4 5 6 7 8 9
        //5 1 2 1 8 5 6 9 5 9

        System.out.println("Connected 3 & 7 ? Result = " + quickUnion.connected(3, 7));
        System.out.println("Connected 0 & 4 ? Result = " + quickUnion.connected(0, 4));
        System.out.println("Connected 5 & 9 ? Result = " + quickUnion.connected(5, 9));
        //Connected 3 & 7 ? Result = false
        //Connected 0 & 4 ? Result = true
        //Connected 5 & 9 ? Result = false
    }
}
