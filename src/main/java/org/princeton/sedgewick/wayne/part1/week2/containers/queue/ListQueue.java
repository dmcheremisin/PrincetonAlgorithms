package org.princeton.sedgewick.wayne.part1.week2.containers.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
        ListQueue<Integer> queue = new ListQueue<>();
        queue.enqueue(10);
        queue.enqueue(9);
        queue.enqueue(8);
        queue.enqueue(7);
        queue.enqueue(6);
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(1);

        System.out.println(queue);

        System.out.println();

        System.out.println(queue.dequeue()); // 10
        System.out.println(queue.dequeue()); // 9
        System.out.println(queue.dequeue()); // 8
        System.out.println(queue.dequeue()); // 7
        System.out.println(queue.dequeue()); // 6
        System.out.println(queue.dequeue()); // 5
        System.out.println(queue.dequeue()); // 4
        System.out.println(queue.dequeue()); // 3
        System.out.println(queue.dequeue()); // 2
        System.out.println(queue.dequeue()); // 1
        System.out.println(queue.dequeue()); // null

    }
}
