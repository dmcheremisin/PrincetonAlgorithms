package org.princeton.sedgewick.wayne.part1.week2.containers.stack.array;

import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStack {

    private int N;
    private String[] arr;

    public FixedCapacityStack(int capacity) {
        arr = new String[capacity];
    }

    public void push(String str) {
        arr[N++] = str;
    }

    public String pop() {
        return arr[--N];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int getSize() {
        return N;
    }
}

class Test {
    public static void main(String[] args) {
        FixedCapacityStack stack = new FixedCapacityStack(10);
        stack.push("London");
        stack.push("is");
        stack.push("the");
        stack.push("capital");
        stack.push("of");
        stack.push("Great");
        stack.push("Britain");

        System.out.println(stack.pop()); // Britain
        System.out.println(stack.pop()); // Great
        System.out.println(stack.pop()); // of
        System.out.println(stack.pop()); // capital
        System.out.println(stack.pop()); // the
        StdOut.print(stack.pop()); // is
    }
}
