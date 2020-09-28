package org.princeton.sedgewick.wayne.chapter1.bag;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {

    private Node first;

    private class Node {
        Item value;
        Node next;

        public Node(Item value) {
            this.value = value;
        }
    }

    public void add(Item item) {
        Node newFirst = new Node(item);
        newFirst.next = first;
        first = newFirst;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListStackIterator();
    }

    private class ListStackIterator implements Iterator<Item> {

        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item next = current.value;
            current = current.next;
            return next;
        }
    }
}

class TestQueue {
    public static void main(String[] args) {
        Bag<Integer> stack = new Bag<>();
        stack.add(10);
        stack.add(9);
        stack.add(8);
        stack.add(7);
        stack.add(6);
        stack.add(5);
        stack.add(4);
        stack.add(3);
        stack.add(2);
        stack.add(1);

        for (Integer value : stack)
            System.out.print(value + " "); // 10 9 8 7 6 5 4 3 2 1
    }
}
