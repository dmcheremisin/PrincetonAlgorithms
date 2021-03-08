package org.princeton.sedgewick.wayne.part2.week5.challenge;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output 
    public static void transform() {
        String str = BinaryStdIn.readString();

        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(str);

        int length = str.length();
        char[] chars = new char[length];

        int position = 0;
        for (int i = 0; i < length; i++) {
            int index = circularSuffixArray.index(i);
            if (index == 0)
                position = i;
            index = index > 0 ? index - 1 : length - 1;
            chars[i] = str.charAt(index);
        }

        BinaryStdOut.write(position);
        for (int i = 0; i < length; i++)
            BinaryStdOut.write(chars[i]);
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int position = BinaryStdIn.readInt();
        String str = BinaryStdIn.readString();

        char[] chars = str.toCharArray();
        int length = chars.length;

        int[] count = getCharsCount(chars);
        char[] sortedChars = getSortedChars(chars, length, count);
        int[] next = getNextArray(chars, length, count);

        for (int i = 0; i < chars.length; i++, position = next[position])
            BinaryStdOut.write(sortedChars[position]);

        BinaryStdOut.close();
    }

    private static int[] getCharsCount(char[] chars) {
        int R = 256;
        int[] count = new int[R + 1];

        for (char value : chars)
            count[value + 1]++;

        for (int i = 0; i < R; i++)
            count[i + 1] += count[i];

        return count;
    }

    private static char[] getSortedChars(char[] chars, int length, int[] count) {
        int[] countForSort = count.clone();
        char[] sortedChars = new char[length];
        for (char aChar : chars) {
            int countIndex = countForSort[aChar]++;
            sortedChars[countIndex] = aChar;
        }
        return sortedChars;
    }

    private static int[] getNextArray(char[] chars, int length, int[] count) {
        int[] next = new int[length];
        for (int i = 0; i < length; i++) {
            char aChar = chars[i];
            next[count[aChar]++] = i;
        }
        return next;
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-"))
            transform();
        else if (args[0].equals("+"))
            inverseTransform();
        else
            throw new IllegalArgumentException("Illegal command line argument");
    }

}