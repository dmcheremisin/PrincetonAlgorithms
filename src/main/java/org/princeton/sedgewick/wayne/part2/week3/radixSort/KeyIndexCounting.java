package org.princeton.sedgewick.wayne.part2.week3.radixSort;

import static org.princeton.sedgewick.wayne.util.SortUtils.printArr;

public class KeyIndexCounting {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 1, 3, 2, 5, 4, 2, 1, 0, 3};
        sortArray(arr, 6);
        printArr(arr); // 0 1 1 2 2 3 3 4 4 5

        System.out.println("======================");

        arr = new int[]{4, 1, 3, 2, 5, 4, 2, 1, 0, 3};
        sortAsInCourse(arr, 6);
        printArr(arr); // 0 1 1 2 2 3 3 4 4 5
    }

    public static void sortArray(int[] arr, int R) {
        int[] count = new int[R];

        for (int arrValue : arr)
            count[arrValue]++;

        int index = 0;
        for (int i = 0; i < R; i++) { // possible values
            for (int j = 0; j < count[i]; j++) { // count[i] => value frequency
                arr[index] = i;
                index++;
            }
        }
    }

    public static void sortAsInCourse(int[] arr, int R) {
        int N = arr.length;
        int[] count = new int[R + 1];
        int[] aux = new int[N];

        for (int value : arr)
            count[value + 1]++;

        for (int i = 0; i < R; i++)
            count[i + 1] += count[i];

        for (int arrValue : arr) {
            int countIndex = count[arrValue]++;
            aux[countIndex] = arrValue;
        }

        for (int i = 0; i < N; i++)
            arr[i] = aux[i];
    }
}









