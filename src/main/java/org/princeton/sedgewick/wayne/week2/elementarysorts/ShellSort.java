package org.princeton.sedgewick.wayne.week2.elementarysorts;

import static org.princeton.sedgewick.wayne.util.SortUtils.exch;
import static org.princeton.sedgewick.wayne.util.SortUtils.less;

public class ShellSort {

    public static void main(String[] args) {
        Integer[] arr = {15, 4, 10, 12, 1, 8, 13, 5, 9, 2, 11, 14, 7, 6, 3, 17, 16, 18};
        sort(arr);
        for (Integer str : arr)
            System.out.println(str);
    }

    public static void sort(Comparable... arr) {
        int length = arr.length;

        for (int h = length / 2; h >= 1; h = h / 2) {
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && less(arr[j], arr[j - h]); j -= h)
                    exch(arr, j, j - h);
            }
        }
    }
}
