package org.princeton.sedgewick.wayne.part2.week3.radixSort;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

import static org.princeton.sedgewick.wayne.util.SortUtils.printArr;

public class Quick3wayStrings {

    public static void main(String[] args) {
        //C:\Workspace\PrincetonAlgorithms\src\main\resources\part2\week3\shells.txt
        In in = new In(args[0]);
        List<String> stringList = new ArrayList<>();

        while (in.hasNextLine())
            stringList.add(in.readString());

        String[] a = stringList.toArray(new String[]{});
        sort(a);

        printArr(a);
        //are by sea seashells seashells sells sells she she shells shore surely the the
    }

    private static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo)
            return;

        int lt = lo;
        int gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;

        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v)
                exch(a, lt++, i++);
            else if (t > v)
                exch(a, i, gt--);
            else
                i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt - 1, d);
        if (v >= 0)
            sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
    }

    public static int charAt(String str, int d) {
        if (d == str.length())
            return -1;

        return str.charAt(d);
    }

    // exchange a[i] and a[j]
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
