package org.princeton.sedgewick.wayne.week2.challenge;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first; // 8
    private Node last; // 8
    private int length; // 4(int size) + 4(addition to dividable by 8) = 8
    // total 24

    // construct an empty deque
    public Deque() {
        first = last = null;
        length = 0;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> integers = new Deque<>();
        integers.addFirst(3);
        integers.addFirst(2);
        integers.addFirst(1);
        integers.addLast(2);
        System.out.println("One: " + integers.removeFirst());
        System.out.println("Two: " + integers.removeFirst());
        integers.addLast(3);
        System.out.println("Three: " + integers.removeLast());
        for (Integer integer : integers) {
            System.out.println(integer);
        }
        System.out.println("Is empty?: " + integers.isEmpty());
        System.out.println("Size: " + integers.size());
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return length;
    }

    private void checkItem(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Item must not be null");
    }

    private void checkSize() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
    }

    // add the item to the front
    public void addFirst(Item item) {
        checkItem(item);
        Node newFirst = new Node(item);

        if (isEmpty()) {
            first = last = newFirst;
        } else {
            newFirst.next = first;
            first.previous = newFirst;
            first = newFirst;
        }

        length++;
    }

    // add the item to the back
    public void addLast(Item item) {
        checkItem(item);
        Node newLast = new Node(item);

        if (isEmpty()) {
            first = last = newLast;
        } else {
            last.next = newLast;
            newLast.previous = last;
            last = newLast;
        }

        length++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        checkSize();

        final Item firstValue = first.value;

        first = first.next;
        first.previous = null;

        length--;

        return firstValue;
    }

    // remove and return the item from the back
    public Item removeLast() {
        checkSize();

        final Item lastValue = last.value;
        last = last.previous;
        last.next = null;

        length--;

        return lastValue;
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class Node { // 16(object) + 8(as inner class) + 8 + 8 + 8 = 48
        Item value; // 8
        Node next; // 8
        Node previous; // 8

        Node(Item value) {
            this.value = value;
        }
    }

    private class DequeIterator implements Iterator<Item> { // 16(object) + 8(inner class) + 8(ref) = 32

        Node current = first; // 8

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null)
                throw new NoSuchElementException("No next element");

            Item next = current.value;
            current = current.next;
            return next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported");
        }
    }

}
