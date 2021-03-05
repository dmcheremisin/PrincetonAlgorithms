package org.princeton.sedgewick.wayne.part2.week5.challenge;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output 
    public static void transform() {
        String str = BinaryStdIn.readString();

        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(str);

        StringBuilder sb = new StringBuilder();
        int position = 0;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            int index = circularSuffixArray.index(i);
            if (index == 0)
                position = i;
            index = index > 0 ? index - 1 : length - 1;
            sb.append(str.charAt(index));
        }

        BinaryStdOut.write(position);
        BinaryStdOut.write(sb.toString());
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {

        BinaryStdOut.close();
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