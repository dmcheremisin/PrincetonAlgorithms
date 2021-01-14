package org.princeton.sedgewick.wayne.part1.week3.quickSort;

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

    private static void sort(Comparable[] permutation) {
        sort(permutation, 0, permutation.length - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo)
            return;

        int pivot = partition(arr, lo, hi);
        sort(arr, lo, pivot - 1);
        sort(arr, pivot + 1, hi);
    }

    private static int partition(Comparable[] arr, int lo, int hi) {
        int i = lo, j = hi + 1;

        Comparable pivot = arr[lo];
        while (true) {
            while (less(arr[++i], pivot))
                if (i == hi)
                    break;

            while (less(pivot, arr[--j]))
                if (j == lo)
                    break;

            if (i >= j)
                break;

            exch(arr, i, j);
        }

        exch(arr, lo, j);
        return j;
    }

}
