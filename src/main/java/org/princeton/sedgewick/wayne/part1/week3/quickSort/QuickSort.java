package org.princeton.sedgewick.wayne.part1.week3.quickSort;

import static edu.princeton.cs.algs4.StdRandom.shuffle;
import static org.princeton.sedgewick.wayne.util.SortUtils.*;

public class QuickSort {

    public static void main(String[] args) {
        Integer[] permutation = permutation(20);
        printArr(permutation);
        //8 3 11 18 7 10 2 16 1 14 9 12 13 19 6 15 17 5 0 4
        sort(permutation);
        printArr(permutation);
        //0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
    }

    private static void sort(Comparable[] a) {
        shuffle(a); // prevents quadratic running time
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;

        while (true) {
            while (less(a[++i], a[lo])) // find item on left to swap
                if (i == hi) break;

            while (less(a[lo], a[--j])) // find item on right to swap
                if (j == lo) break;

            if (i >= j) break; // check if pointers cross

            exch(a, i, j); // exchange items
        }

        exch(a, lo, j);
        return j;
    }

}
