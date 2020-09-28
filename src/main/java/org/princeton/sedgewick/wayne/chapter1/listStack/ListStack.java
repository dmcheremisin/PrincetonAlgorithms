package org.princeton.sedgewick.wayne.chapter1.listStack;

import java.util.Iterator;

public class ListStack<Item> implements Iterable<Item> {

    private Node head;
    private int size = 0;

    private class Node {
        Item value;
        Node next;

        public Node(Item value) {
            this.value = value;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void push(Item item) {
        Node newHead = new Node(item);
        newHead.next = head;
        head = newHead;
        size++;
    }

    public Item pop() {
        if (isEmpty())
            return null;

        Item item = head.value;
        head = head.next;
        size--;

        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListStackIterator();
    }

    private class ListStackIterator implements Iterator<Item> {

        Node current = head;

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
