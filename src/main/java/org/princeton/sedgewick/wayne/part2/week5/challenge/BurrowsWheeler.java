package org.princeton.sedgewick.wayne.part2.week5.challenge;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.Arrays;

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
        char[] sortedChars = chars.clone();
        Arrays.sort(sortedChars);
        int[] next = getNextArray(chars, sortedChars);

        for (int i = 0; i < chars.length; i++, position = next[position])
            BinaryStdOut.write(sortedChars[position]);

        BinaryStdOut.close();
    }

    private static int[] getNextArray(char[] chars, char[] sortedChars) {
        int length = chars.length;
        int[] next = new int[length];
        boolean[] marked = new boolean[length];

        for (int i = 0; i < length; i++) {
            char aChar = sortedChars[i];
            for (int j = 0; j < length; j++) {
                if (aChar == chars[j] && !marked[j]) {
                    next[i] = j;
                    marked[j] = true;
                    break;
                }
            }
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