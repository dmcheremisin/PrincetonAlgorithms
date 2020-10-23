package org.princeton.sedgewick.wayne.week2.elementarysorts;

import static org.princeton.sedgewick.wayne.util.SortUtils.exch;
import static org.princeton.sedgewick.wayne.util.SortUtils.less;

public class InsertionSort {

    public static void main(String[] args) {
        String[] arr = {"mno", "ghi", "jkl", "abc", "pqr", "def"};
        sort(arr);
        for (String str : arr)
            System.out.println(str);
    }

    public static void sort(Comparable[] arr) {
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && less(arr[j], arr[j - 1]); j--)
                exch(arr, j, j - 1);
        }
    }
}
