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

            for (int r = 0; r < R; r++)
                dfa[r][j] = dfa[r][x]; // Copy mismatch cases

            char c = pattern.charAt(j);

            dfa[c][j] = j + 1; // Set match case

            x = dfa[c][x]; // Update restart state
        }

        return dfa;
    }

    public static void main(String[] args) {
        System.out.println(findSubstring("abra", "abacadabrac")); // 6
        System.out.println(findSubstring("yahoo", "abacadabrac")); // -1
    }
}
