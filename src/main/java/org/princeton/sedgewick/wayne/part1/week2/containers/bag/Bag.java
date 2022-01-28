package org.princeton.sedgewick.wayne.part1.week2.containers.bag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Bag<Item> implements Iterable<Item> {

    private Node first;

    private class Node {
        Item value;
        Node next;

        Node(Item value) {
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
            if (current == null)
                throw new NoSuchElementException("No next element");

            Item next = current.value;
            current = current.next;
            return next;
        }
    }

    @Override
    public String toString() {
        List<Item> values = new ArrayList<>();
        Node node = first;
        while(node != null) {
            values.add(node.value);
            node = node.next;
        }
        String joinedValues = values.stream().map(Item::toString).collect(Collectors.joining(", "));
        return String.format("[%s]", joinedValues);
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

        System.out.println(stack.toString());
    }
}
