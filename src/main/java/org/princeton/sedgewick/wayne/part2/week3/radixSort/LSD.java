package org.princeton.sedgewick.wayne.part2.week3.radixSort;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

import static org.princeton.sedgewick.wayne.util.SortUtils.printArr;

public class LSD {
    public static void main(String[] args) {
        //C:\Workspace\PrincetonAlgorithms\src\main\resources\part2\week3\words.txt
        In in = new In(args[0]);
        List<String> stringList = new ArrayList<>();

        while (in.hasNextLine())
            stringList.add(in.readString());

        String[] a = stringList.toArray(new String[]{});

        // sort the strings
        sort(a, a[0].length());// length == 3

        printArr(a);
        //all bad bed bug dad dim dug egg fee few for gig hut ilk jam jay jot joy men nob now owl rap sky sob tag tap
        //tar tip wad was wee yes yet zoo
    }

    public static void sort(String[] a, int w) { // fixed-length W strings
        int n = a.length;
        int R = 256;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < n; i++)
                count[a[i].charAt(d) + 1]++;

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];

            // move data
            for (int i = 0; i < n; i++)
                aux[count[a[i].charAt(d)]++] = a[i];

            // copy back
            for (int i = 0; i < n; i++)
                a[i] = aux[i];
        }
    }
}
