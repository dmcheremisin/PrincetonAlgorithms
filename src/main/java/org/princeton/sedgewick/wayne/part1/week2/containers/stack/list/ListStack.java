package org.princeton.sedgewick.wayne.part1.week2.containers.stack.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListStack<T> implements Iterable<T> {

    private Node head;
    private int size;

    private class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void push(T item) {
        Node newHead = new Node(item);
        newHead.next = head;
        head = newHead;
        size++;
    }

    public T pop() {
        if (isEmpty())
            return null;

        T item = head.value;
        head = head.next;
        size--;

        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListStackIterator();
    }

    private class ListStackIterator implements Iterator<T> {

        Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null)
                throw new RuntimeException("No next element");

            T next = current.value;
            current = current.next;
            return next;
        }
    }
}

class TestStack {
    public static void main(String[] args) {
        ListStack<Integer> stack = new ListStack<>();
        stack.push(10);
        stack.push(9);
        stack.push(8);
        stack.push(7);
        stack.push(6);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);

        for (Integer value : stack)
            System.out.print(value + " "); // 1 2 3 4 5 6 7 8 9 10

        System.out.println();

        System.out.println(stack.pop()); // 1
        System.out.println(stack.pop()); // 2
        System.out.println(stack.pop()); // 3
        System.out.println(stack.pop()); // 4
        System.out.println(stack.pop()); // 5
        System.out.println(stack.pop()); // 6
        System.out.println(stack.pop()); // 7
        System.out.println(stack.pop()); // 8
        System.out.println(stack.pop()); // 9
        System.out.println(stack.pop()); // 10
        System.out.println(stack.pop()); // null

    }
}
