package org.princeton.sedgewick.wayne.part1.week2.containers.stack.array;

import edu.princeton.cs.algs4.StdOut;

public class GenericFixedCapacityStack<Item> { //  // 16 as object
    /*
    Memory usage:
    GenericFixedCapacityStack = 16
    Inner structure: 24 + 8N
    -------------
    Total: 40 + 8N
     */

    private int N; // 4(int size) + 4(addition to dividable by 8) = 8
    private Item[] arr; // 24(as array) + 8N(references to objects String) = 24 + 8N
    // total: 24 + 8N

    public GenericFixedCapacityStack(int capacity) {
        arr = (Item[]) new Object[capacity];
    }

    public void push(Item str) {
        arr[N++] = str;
    }

    public Item pop() {
        return arr[--N];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int getSize() {
        return N;
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
