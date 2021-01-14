package org.princeton.sedgewick.wayne.part1.week2.challenge;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items; // 24 + 8N
    private int index; // 4

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[8];
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        queue.enqueue("five");
        queue.enqueue("six");

        System.out.println("Sample: " + queue.sample());

        System.out.println("Random dequeue: " + queue.dequeue());

        queue.enqueue("seven");

        System.out.println("Random dequeue: " + queue.dequeue());

        System.out.println("========= Random iterator ==========");
        for (String randomStr : queue) {
            System.out.println(randomStr);
        }
        System.out.println("========= Random iterator ==========");
        for (String randomStr : queue) {
            System.out.println(randomStr);
        }
        System.out.println();
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());

    }

    private void resize(int newLength) {
        Item[] temp = (Item[]) new Object[newLength];
        System.arraycopy(items, 0, temp, 0, index);
        items = temp;
    }

    private void checkItem(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Item must not be null");
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return index == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return index;
    }

    // add the item
    public void enqueue(Item item) {
        checkItem(item);
        if (items.length == index)
            resize(index * 2);

        items[index++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");

        int randomIndex = StdRandom.uniform(index);

        Item item = items[randomIndex];
        items[randomIndex] = items[--index];
        items[index] = null;

        if (index > 0 && index == items.length / 4)
            resize(items.length / 2);

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");

        return items[StdRandom.uniform(index)];
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> { // 16(object) + 8(inner class) + 8(ref) = 32

        int counter; // 4(int size) + 4(addition to dividable by 8) = 8
        boolean[] visitedIndexes = new boolean[index]; // 24 + N

        @Override
        public boolean hasNext() {
            return counter < index;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("No next element");

            int randomIndex = StdRandom.uniform(index);
            while (visitedIndexes[randomIndex])
                randomIndex = StdRandom.uniform(index);

            visitedIndexes[randomIndex] = true;
            counter++;

            return items[randomIndex];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported");
        }
    }
}
