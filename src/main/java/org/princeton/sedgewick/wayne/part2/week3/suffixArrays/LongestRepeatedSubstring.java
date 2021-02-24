package org.princeton.sedgewick.wayne.part2.week3.suffixArrays;

import java.util.Arrays;

public class LongestRepeatedSubstring {

    public static void main(String[] args) {
        System.out.println(lrs("tgtatgcatatgcacagcat")); // tatgc
    }

    public static String lrs(String str) {
        int N = str.length();

        String[] suffixes = new String[N];
        for (int i = 0; i < N; i++)
            suffixes[i] = str.substring(i, N);

        Arrays.sort(suffixes);

        String longest = "";

        for (int i = 0; i < N - 1; i++) {
            int len = commonSubstringLength(suffixes[i], suffixes[i + 1]);
            if (len > longest.length())
                longest = suffixes[i].substring(0, len);
        }

        return longest;
    }

    private static int commonSubstringLength(String first, String second) {
        int commonLength = Math.min(first.length(), second.length());

        int commonSubstringLength = 0;
        for (int i = 0; i < commonLength; i++) {
            if (first.charAt(i) != second.charAt(i))
                break;
            commonSubstringLength = i;
        }

        return commonSubstringLength;
    }

}
