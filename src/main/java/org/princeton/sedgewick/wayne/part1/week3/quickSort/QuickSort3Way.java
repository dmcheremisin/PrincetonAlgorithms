package org.princeton.sedgewick.wayne.part1.week3.quickSort;

import static edu.princeton.cs.algs4.StdRandom.shuffle;
import static org.princeton.sedgewick.wayne.util.SortUtils.*;

public class QuickSort3Way {

    public static void main(String[] args) {
        Integer[] permutation = permutation(20);
        printArr(permutation);
        //12 10 8 9 15 5 2 19 6 13 14 7 1 16 4 3 0 18 11 17
        sort(permutation);
        printArr(permutation);
        //0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
    }

    private static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;

        int lt = lo, i = lo, gt = hi;

        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);

            if (cmp < 0)
                exch(a, lt++, i++);
            else if (cmp > 0)
                exch(a, i, gt--);
            else i++;
        }

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

}
