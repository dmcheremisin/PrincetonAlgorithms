package org.princeton.sedgewick.wayne.part2.week5.challenge;

import java.util.ArrayList;
import java.util.List;

public class CircularSuffixArray {

    private final String str;
    private final int length;
    private final int[] indexes;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException("Illegal input string");

        str = s;
        length = s.length();

        List<CircularSuffix> circularSuffixes = new ArrayList<>();
        for (int i = 0; i < length; i++)
            circularSuffixes.add(new CircularSuffix(i));

        circularSuffixes.sort(CircularSuffix::compareTo);
        indexes = new int[length];
        for (int i = 0; i < length; i++) {
            CircularSuffix suffix = circularSuffixes.get(i);
            indexes[i] = suffix.shift;
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
        System.out.println(circularSuffixArray.index(11));

        CircularSuffix circularSuffix1 = circularSuffixArray.new CircularSuffix(5);
        CircularSuffix circularSuffix2 = circularSuffixArray.new CircularSuffix(5);
        System.out.println(circularSuffix1.compareTo(circularSuffix2));
    }

    private class CircularSuffix implements Comparable<CircularSuffix> {
        private final int shift;

        public CircularSuffix(int shift) {
            this.shift = shift;
        }

        public int getCharPosition(int position) {
            return (this.shift + position) < length ? this.shift + position : this.shift + position - length;
        }

        @Override
        public int compareTo(CircularSuffix other) {
            for (int i = 0; i < length; i++) {
                int charPosition1 = getCharPosition(i);
                int charPosition2 = other.getCharPosition(i);
                char thisChar = str.charAt(charPosition1);
                char otherChar = str.charAt(charPosition2);
                if (thisChar < otherChar)
                    return -1;
                else if (otherChar < thisChar)
                    return 1;
            }
            return 0;
        }
    }
}