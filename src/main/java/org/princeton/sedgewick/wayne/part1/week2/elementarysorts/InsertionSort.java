package org.princeton.sedgewick.wayne.part1.week2.elementarysorts;

import static org.princeton.sedgewick.wayne.util.SortUtils.exch;
import static org.princeton.sedgewick.wayne.util.SortUtils.less;

public class InsertionSort {

    public static void main(String[] args) {
        String[] arr = {"mno", "ghi", "jkl", "abc", "pqr", "def"};
        sort(arr);
        for (String str : arr)
            System.out.println(str);
    }

    public static void sort(Comparable... a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                } else break; // already sorted
            }
        }
    }
}
