package org.princeton.sedgewick.wayne.part2.week5.challenge;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.LinkedList;

public class MoveToFront {

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        LinkedList<Character> charStack = getCharStack();
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            int index = charStack.indexOf(c);
            BinaryStdOut.write(index, 8);

            charStack.remove(index);
            charStack.push(c);
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        LinkedList<Character> charStack = getCharStack();
        while (!BinaryStdIn.isEmpty()) {
            int charIndex = BinaryStdIn.readInt(8);
            Character aChar = charStack.remove(charIndex);

            BinaryStdOut.write(aChar, 8);
            charStack.push(aChar);
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-"))
            encode();
        else if (args[0].equals("+"))
            decode();
        else
            throw new IllegalArgumentException("Illegal command line argument");
    }

    private static LinkedList<Character> getCharStack() {
        LinkedList<Character> stack = new LinkedList<>();
        for (char i = 0; i < 256; i++)
            stack.add(i);

        return stack;
    }

}