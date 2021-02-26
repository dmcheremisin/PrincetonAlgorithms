package org.princeton.sedgewick.wayne.part2.week4.substringSearch;

public class KMP {

    public static int findSubstring(String pattern, String txt) {
        int patternLength = pattern.length();
        int[][] dfa = getDfa(pattern, patternLength);

        int i, j;
        for (i = 0, j = 0; i < txt.length() && j < patternLength; i++) {
            char c = txt.charAt(i);
            j = dfa[c][j];
        }

        if (j == patternLength)
            return i - patternLength;

        return -1;
    }

    private static int[][] getDfa(String pattern, int patternLength) {
        int R = 256;

        int[][] dfa = new int[R][patternLength];
        dfa[pattern.charAt(0)][0] = 1;

        for (int x = 0, j = 1; j < patternLength; j++) {

            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][x];

            dfa[pattern.charAt(j)][j] = j + 1;

            x = dfa[pattern.charAt(j)][x];
        }
        return dfa;
    }

    public static void main(String[] args) {
        System.out.println(findSubstring("abra", "abacadabrac")); // 6
        System.out.println(findSubstring("yahoo", "abacadabrac")); // -1
    }
}
