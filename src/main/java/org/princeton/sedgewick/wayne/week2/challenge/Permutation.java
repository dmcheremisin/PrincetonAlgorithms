package org.princeton.sedgewick.wayne.week2.challenge;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int itemsToRead = Integer.parseInt(args[0]);

        RandomizedQueue<String> randomQueue = new RandomizedQueue<>();

        while (!StdIn.isEmpty())
            randomQueue.enqueue(StdIn.readString());

        for (int i = 0; i < itemsToRead; i++)
            System.out.println(randomQueue.dequeue());
    }

}
