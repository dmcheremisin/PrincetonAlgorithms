package org.princeton.sedgewick.wayne.part1.week3.mergeSort;

import static org.princeton.sedgewick.wayne.util.SortUtils.*;

public class BottomUpMergeSort {

    public static void main(String[] args) {
        Integer[] permutation = permutation(20);
        printArr(permutation);
        //18 1 8 3 2 7 11 4 10 13 19 15 6 5 12 16 0 14 17 9
        sort(permutation);
        printArr(permutation);
        //0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz *= 2)
            for (int lo = 0; lo < N - sz; lo += sz * 2)
                merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

}
