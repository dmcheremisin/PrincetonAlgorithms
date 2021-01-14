package org.princeton.sedgewick.wayne.part1.week1.unionfind;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class PathCompressionWeightedQuickUnion {

    int[] id;
    int[] sizes;

    public PathCompressionWeightedQuickUnion(int N) {
        id = new int[N];
        sizes = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sizes[i] = 1;
        }
    }

    private int root(int i) {
        while (id[i] != i) {
            id[i] = id[id[i]]; // <<<<<<< path compression
            i = id[i];
        }

        return i;
    }
    // Path compression example:
    //  0  1  2  3  4  5  6  7  8  9
    //  1  4  4  9  8  8  7  7  7  9
    // id[0] = 1
    // id[id[0]] = id[1] = 4
    // id[0] = id[id[0]]
    // => set to 0-element value 4, removing link with 1 => compress path

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

class PathCompressionWeightedQuickUnionTest {

    public static void main(String[] args) {
        int N = 20;
        PathCompressionWeightedQuickUnion quickUnion = new PathCompressionWeightedQuickUnion(N);
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            int p = random.nextInt(N);
            int q = random.nextInt(N);
            System.out.println("Union p = " + p + "; q = " + q);
            quickUnion.union(p, q);
        }
        //Union p = 4; q = 2
        //Union p = 0; q = 3
        //Union p = 16; q = 1
        //Union p = 14; q = 6
        //Union p = 10; q = 4
        //Union p = 14; q = 2
        //Union p = 13; q = 3
        //Union p = 19; q = 3
        //Union p = 12; q = 15
        //Union p = 8; q = 18
        //Union p = 8; q = 0
        //Union p = 2; q = 6
        //Union p = 5; q = 7
        //Union p = 19; q = 3
        //Union p = 1; q = 18
        //Union p = 19; q = 0
        //Union p = 3; q = 11
        //Union p = 13; q = 4
        //Union p = 11; q = 8
        //Union p = 1; q = 13

        IntStream.range(0, N).forEach(i -> System.out.printf("%1$3d", i));
        System.out.println();
        Arrays.stream(quickUnion.id).forEach(i -> System.out.printf("%1$3d", i));
        System.out.println();
        //  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
        //  0  0  4  0  0  5  4  5  0  9  4  0 12  0  4 12  0 17  0  0

        System.out.println("Connected 3 & 7 ? Result = " + quickUnion.connected(3, 7));
        System.out.println("Connected 0 & 4 ? Result = " + quickUnion.connected(0, 4));
        System.out.println("Connected 5 & 9 ? Result = " + quickUnion.connected(5, 9));
        //Connected 3 & 7 ? Result = false
        //Connected 0 & 4 ? Result = true
        //Connected 5 & 9 ? Result = false
    }
}
