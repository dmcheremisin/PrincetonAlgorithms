package org.princeton.sedgewick.wayne.chapter1;

import edu.princeton.cs.algs4.StdOut;

public class GenericFixedCapacityStack<Item> {

    private int N;
    private Item[] arr;

    public GenericFixedCapacityStack(int capacity) {
        arr = (Item[]) new Object[capacity];
    }

    public void push(Item str) {
        arr[N++] = str;
    }

    public Item pop() {
        return arr[--N];
    }
}

class TestGenericFixedCapacityStack {
    public static void main(String[] args) {
        GenericFixedCapacityStack<Integer> stack = new GenericFixedCapacityStack<>(10);
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

        System.out.println(stack.pop()); // 1
        System.out.println(stack.pop()); // 2
        System.out.println(stack.pop()); // 3
        System.out.println(stack.pop()); // 4
        System.out.println(stack.pop()); // 5
        StdOut.print(stack.pop()); // 6
    }
}