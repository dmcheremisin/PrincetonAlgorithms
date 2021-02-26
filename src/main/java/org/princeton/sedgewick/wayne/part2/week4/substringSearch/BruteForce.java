package org.princeton.sedgewick.wayne.part2.week4.substringSearch;

public class BruteForce {

    public static void main(String[] args) {
        System.out.println(findSubstring("abra", "abacadabrac")); // 6
        System.out.println(findSubstring("yahoo", "abacadabrac")); // -1
    }

    private static int findSubstring(String pattern, String txt) {
        int N = txt.length();
        int M = pattern.length();

        for (int i = 0; i < N - M; i++) {
            for (int j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pattern.charAt(j))
                    break;
                if (j == M - 1)
                    return i;
            }
        }
        return -1;
    }

}
