package org.princeton.sedgewick.wayne.util;

import java.util.Random;

import static edu.princeton.cs.algs4.StdRandom.shuffle;

public class SortUtils {

    public static void exch(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    public static boolean isSorted(Comparable... arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (!less(arr[i], arr[i + 1]))
                return false;
        }

        return true;
    }

    public static Integer[] permutation(int n) {
        Integer[] perm = new Integer[n];
        for (int i = 0; i < n; i++)
            perm[i] = i;
        shuffle(perm);
        return perm;
    }

    public static int[] randomArr(int number, int bound) {
        Random random = new Random();
        int[] perm = new int[number];
        for (int i = 0; i < number; i++)
            perm[i] = random.nextInt(bound);
        return perm;
    }

    public static void printArr(Object[] arr) {
        for (Object o : arr)
            System.out.print(o + " ");
        System.out.println();
    }
}
