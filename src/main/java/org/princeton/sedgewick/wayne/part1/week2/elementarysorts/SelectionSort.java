package org.princeton.sedgewick.wayne.part1.week2.elementarysorts;

import static org.princeton.sedgewick.wayne.util.SortUtils.exch;
import static org.princeton.sedgewick.wayne.util.SortUtils.less;

public class SelectionSort {

    public static void main(String[] args) {
        String[] arr = {"mno", "ghi", "jkl", "abc", "pqr", "def"};
        selectionSort(arr);
        for (String str : arr)
            System.out.println(str);
    }

    public static void selectionSort(Comparable... arr) {
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (less(arr[j], arr[min]))
                    min = j;
            }
            exch(arr, i, min);
        }
    }
}
