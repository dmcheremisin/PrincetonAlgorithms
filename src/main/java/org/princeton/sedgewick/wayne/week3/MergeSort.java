package org.princeton.sedgewick.wayne.week3;

import static org.princeton.sedgewick.wayne.util.SortUtils.less;
import static org.princeton.sedgewick.wayne.util.SortUtils.permutation;
import static org.princeton.sedgewick.wayne.util.SortUtils.printArr;

public class MergeSort {

    private static Comparable[] aux;

    public static void main(String[] args) {
        Integer[] permutation = permutation(20);
        printArr(permutation);
        //8 6 2 10 4 14 1 18 7 0 16 15 13 5 17 9 12 3 11 19
        sort(permutation);
        printArr(permutation);
        //0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
    }

    public static Comparable[] sort(Comparable[] arr) {
        int length = arr.length;
        aux = new Comparable[length];
        sort(arr, 0, length - 1);
        return arr;
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo)
            return;

        int mid = (hi + lo) / 2;

        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    private static void merge(Comparable[] arr, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++)
            aux[k] = arr[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                arr[k] = aux[j++];
            else if (j > hi)
                arr[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                arr[k] = aux[j++];
            else
                arr[k] = aux[i++];
        }
    }
}