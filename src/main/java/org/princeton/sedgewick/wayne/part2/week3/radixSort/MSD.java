package org.princeton.sedgewick.wayne.part2.week3.radixSort;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

import static org.princeton.sedgewick.wayne.util.SortUtils.printArr;

public class MSD {

    private static final int R = 256;

    public static void main(String[] args) {
        //C:\Workspace\PrincetonAlgorithms\src\main\resources\part2\week3\shells.txt
        In in = new In(args[0]);
        List<String> stringList = new ArrayList<>();

        while (in.hasNextLine())
            stringList.add(in.readString());

        String[] a = stringList.toArray(new String[]{});
        sort(a);

        printArr(a);
        // are by sea seashells seashells sells sells she she shells shore surely the the
    }

    public static void sort(String[] arr) {
        String[] aux = new String[arr.length];
        sort(arr, aux, 0, arr.length - 1, 0);
    }

    private static void sort(String[] arr, String[] aux, int lo, int hi, int d) {
        if (hi <= lo)
            return;

        int[] count = new int[R + 2];

        for (int i = lo; i <= hi; i++)
            count[charAt(arr[i], d) + 2]++;

        for (int r = 0; r < R + 1; r++)
            count[r + 1] += count[r];

        for (int i = lo; i <= hi; i++)
            aux[count[charAt(arr[i], d) + 1]++] = arr[i];

        for (int i = lo; i <= hi; i++)
            arr[i] = aux[i - lo];

        for (int r = 0; r < R; r++)
            sort(arr, aux, lo + count[r], lo + count[r + 1] - 1, d + 1);
    }

    public static int charAt(String str, int d) {
        if (d == str.length())
            return -1;

        return str.charAt(d);
    }
}
