package org.princeton.sedgewick.wayne.part2.week3.radixSort;

import static org.princeton.sedgewick.wayne.util.SortUtils.printArr;

public class LSD {

    public static void main(String[] args) {
        String[] strArr = {"dacffbdbfbea", "adaaeaaeeebc", "bdbdeddeddbe"};
        sort(strArr, 12);
        printArr(strArr); // adaaeaaeeebc bdbdeddeddbe dacffbdbfbea
    }

    public static void sort(String[] a, int w) { // fixed-length W strings
        int n = a.length;
        int R = 256;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < n; i++)
                count[a[i].charAt(d) + 1]++;

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];

            // move data
            for (int i = 0; i < n; i++)
                aux[count[a[i].charAt(d)]++] = a[i];

            // copy back
            for (int i = 0; i < n; i++)
                a[i] = aux[i];
        }
    }
}
