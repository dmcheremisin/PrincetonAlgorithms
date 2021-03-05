package org.princeton.sedgewick.wayne.part2.week5.challenge;

import java.util.*;

import static org.princeton.sedgewick.wayne.util.SortUtils.printArr;

public class CircularSuffixArray {

    private final String str;
    private final int length;
    private final int[] indexes;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException("Illegal input string");
        str = s;
        length = str.length();

        Map<String, Integer> suffixMap = new HashMap<>();
        StringBuilder sb = new StringBuilder(str);
        suffixMap.put(str, 0);
        for (int i = 1; i < length; i++) {
            char firstChar = sb.charAt(0);
            StringBuilder suffix = sb.deleteCharAt(0);
            suffix.append(firstChar);
            suffixMap.put(suffix.toString(), i);
        }
        System.out.println(suffixMap);

        List<String> sortedSuffixes = new ArrayList<>(suffixMap.keySet());
        sortedSuffixes.sort(Comparator.naturalOrder());
        System.out.println(sortedSuffixes);

        indexes = new int[length];
        for (int i = 0; i < length; i++) {
            String sortedSuffix = sortedSuffixes.get(i);
            int indexOf = suffixMap.get(sortedSuffix);
            indexes[i] = indexOf;
        }
        printArr(indexes);
    }

    // length of s
    public int length() {
        return length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= length)
            throw new IllegalArgumentException("Illegal index");

        return indexes[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray("ABRACADABRA!");
        System.out.println(circularSuffixArray.length());
        System.out.println(circularSuffixArray.index(0));
        System.out.println(circularSuffixArray.index(10));
    }

}