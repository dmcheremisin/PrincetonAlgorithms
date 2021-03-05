package org.princeton.sedgewick.wayne.part2.week5.challenge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CircularSuffixArray {

    private final int length;
    private final int[] indexes;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException("Illegal input string");

        length = s.length();

        Map<String, Integer> suffixMap = new HashMap<>();
        suffixMap.put(s, 0);

        StringBuilder sb = new StringBuilder(s);
        for (int i = 1; i < length; i++) {
            char firstChar = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(firstChar);
            suffixMap.put(sb.toString(), i);
        }

        List<String> sortedSuffixes = new ArrayList<>(suffixMap.keySet());
        sortedSuffixes.sort(Comparator.naturalOrder());

        indexes = new int[length];
        for (int i = 0; i < length; i++) {
            String sortedSuffix = sortedSuffixes.get(i);
            int indexOf = suffixMap.get(sortedSuffix);
            indexes[i] = indexOf;
        }
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