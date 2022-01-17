package org.princeton.sedgewick.wayne.part1.week2.elementarysorts;

import static org.princeton.sedgewick.wayne.util.SortUtils.exch;
import static org.princeton.sedgewick.wayne.util.SortUtils.less;

public class ShellSort {

    public static void main(String[] args) {
        Integer[] arr = {15, 4, 10, 12, 1, 8, 13, 5, 9, 2, 11, 14, 7, 6, 3, 17, 16, 18};
        sort(arr);
        for (Integer str : arr)
            System.out.println(str);
    }

    public static void sort(Comparable... a) {
        int N = a.length; // N = 18
        for (int h = N / 2; h >= 1; h = h / 2) { // h = 9, 4, 2, 1
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(a[j], a[j - h])) {
                        exch(a, j, j - h);
                    } else break; // already h-sorted
                }
            }
        }
    }
}
