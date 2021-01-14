package org.princeton.sedgewick.wayne.part1.week2.containers.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListQueue<Item> implements Iterable<Item> { // 16 as object
     /*
     Memory usage:
     ListQueue object = 16
     Inner references(first,last,size) = 24
     Inner class Node = 40N
     Inner class ListStackIterator = 32
     ------------------------
     total: 72 + 40N
     */

    private Node first; // 8
    private Node last; // 8
    private int size; // 4(int size) + 4(addition to dividable by 8) = 8
    // total 24

    public boolean isEmpty() {
        return first == null;
    }

    public int getSize() {
        return size;
    }

    // add to tail
    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node(item);

        if (isEmpty())
            first = last;
        else
            oldLast.next = last;

        size++;
    }

    // get first element
    public Item dequeue() {
        if (isEmpty()) {
            last = null;
            return null;
        }

        Item item = first.value;
        first = first.next;
        size--;

        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListStackIterator();
    }

    private class Node { // 16(object) + 8(as inner class) + 8 + 8 = 40
        Item value; // 8
        Node next; // 8

        Node(Item value) {
            this.value = value;
        }
    }

    private class ListStackIterator implements Iterator<Item> { // 16(object) + 8(inner class) + 8(ref) = 32

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
    }
}

class TestQueue {
    public static void main(String[] args) {
        ListQueue<Integer> stack = new ListQueue<>();
        stack.enqueue(10);
        stack.enqueue(9);
        stack.enqueue(8);
        stack.enqueue(7);
        stack.enqueue(6);
        stack.enqueue(5);
        stack.enqueue(4);
        stack.enqueue(3);
        stack.enqueue(2);
        stack.enqueue(1);

        for (Integer value : stack)
            System.out.print(value + " "); // 10 9 8 7 6 5 4 3 2 1

        System.out.println();

        System.out.println(stack.dequeue()); // 10
        System.out.println(stack.dequeue()); // 9
        System.out.println(stack.dequeue()); // 8
        System.out.println(stack.dequeue()); // 7
        System.out.println(stack.dequeue()); // 6
        System.out.println(stack.dequeue()); // 5
        System.out.println(stack.dequeue()); // 4
        System.out.println(stack.dequeue()); // 3
        System.out.println(stack.dequeue()); // 2
        System.out.println(stack.dequeue()); // 1
        System.out.println(stack.dequeue()); // null

    }
}
