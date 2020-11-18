package org.princeton.sedgewick.wayne.week3;

import static org.princeton.sedgewick.wayne.util.SortUtils.less;
import static org.princeton.sedgewick.wayne.util.SortUtils.permutation;
import static org.princeton.sedgewick.wayne.util.SortUtils.printArr;

public class BottomUpMergeSort {

    private static Comparable[] aux;

    public static void main(String[] args) {
        Integer[] permutation = permutation(20);
        printArr(permutation);
        //18 1 8 3 2 7 11 4 10 13 19 15 6 5 12 16 0 14 17 9
        sort(permutation);
        printArr(permutation);
        //0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
    }

    public static void sort(Comparable[] arr) {
        int length = arr.length;
        aux = new Comparable[length];
        for (int sz = 1; sz < length; sz = sz + sz)
            for (int lo = 0; lo < length - sz; lo += sz * 2)
                merge(arr, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, length - 1));
    }

    private static void merge(Comparable[] arr, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++)
            aux[k] = arr[k];

        int i = lo;
        int j = mid + 1;
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
