package org.princeton.sedgewick.wayne.week2.containers.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListQueue<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

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

    private class Node {
        Item value;
        Node next;

        Node(Item value) {
            this.value = value;
        }
    }

    private class ListStackIterator implements Iterator<Item> {

        Node current = first;

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
