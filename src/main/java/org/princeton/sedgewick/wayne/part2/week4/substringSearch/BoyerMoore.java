package org.princeton.sedgewick.wayne.part2.week4.substringSearch;

public class BoyerMoore {

    public static int findSubstring(String pattern, String txt) {
        int m = pattern.length();
        int n = txt.length();

        int[] right = getRight(pattern);
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;

            for (int j = m - 1; j >= 0; --j) {
                if (pattern.charAt(j) != txt.charAt(i + j)) {
                    skip = Math.max(1, j - right[txt.charAt(i + j)]);
                    break;
                }
            }

            if (skip == 0) {
                return i;
            }
        }

        return -1;
    }

    private static int[] getRight(String pattern) {
        int R = 256;
        int[] right = new int[R];

        for (int i = 0; i < R; ++i)
            right[i] = -1;

        int j = 0;
        while (j < pattern.length())
            right[pattern.charAt(j)] = j++;

        return right;
    }

    public static void main(String[] args) {
        System.out.println(findSubstring("abra", "abacadabrac")); // 6
        System.out.println(findSubstring("yahoo", "abacadabrac")); // -1
    }

}
