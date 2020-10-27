package org.princeton.sedgewick.wayne.week2.elementarysorts;

import static org.princeton.sedgewick.wayne.util.SortUtils.exch;
import static org.princeton.sedgewick.wayne.util.SortUtils.less;

public class ShellSort {

    public static void main(String[] args) {
        Integer[] arr = {15, 4, 10, 12, 1, 8, 13, 5, 9, 2, 11, 14, 7, 6, 3};
        sort(arr);
        for (Integer str : arr)
            System.out.println(str);
    }

    public static void sort(Comparable... arr) {
        int length = arr.length;
        int h = 1;
        while (h < length / 3)
            h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && less(arr[j], arr[j - h]); j -= h)
                    exch(arr, j, j - h);
            }
            h = h / 3;
        }
    }
}
