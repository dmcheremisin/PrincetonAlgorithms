package org.princeton.sedgewick.wayne.part1.week1.unionfind;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class QuickFind {

    int[] id;

    public QuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int idp = id[p];
        int idq = id[q];
        for (int i = 0; i < id.length; i++)
            if (id[i] == idp)
                id[i] = idq;
    }
}

class QuickFindTest {

    public static void main(String[] args) {
        Random random = new Random();
        int N = 10;
        QuickFind quickFind = new QuickFind(N);
        for (int i = 0; i < 5; i++) {
            int p = random.nextInt(N);
            int q = random.nextInt(N);
            System.out.println("Union p = " + p + "; q = " + q);
            quickFind.union(p, q);
        }
        //Union p = 0; q = 4
        //Union p = 7; q = 8
        //Union p = 5; q = 9
        //Union p = 7; q = 6
        //Union p = 8; q = 2

        IntStream.range(0, N).forEach(i -> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(quickFind.id).forEach(i -> System.out.print(i + " "));
        System.out.println();
        //0 1 2 3 4 5 6 7 8 9
        //4 1 2 3 4 9 2 2 2 9

        System.out.println("Connected 3 & 7 ? Result = " + quickFind.connected(3, 7));
        System.out.println("Connected 0 & 4 ? Result = " + quickFind.connected(0, 4));
        System.out.println("Connected 5 & 9 ? Result = " + quickFind.connected(5, 9));
        //Connected 3 & 7 ? Result = false
        //Connected 0 & 4 ? Result = true
        //Connected 5 & 9 ? Result = true
    }
}
