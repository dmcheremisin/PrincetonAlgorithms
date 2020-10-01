package org.princeton.sedgewick.wayne.week2.containers.stack.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterableResizableGenericStack<Item> implements Iterable<Item> {

    private int N;
    private Item[] arr;

    public IterableResizableGenericStack() {
        arr = (Item[]) new Object[4];
    }

    public void push(Item str) {
        if (arr.length == N)
            resize(N * 2);

        arr[N++] = str;
    }

    private void resize(int newLength) {
        Item[] temp = (Item[]) new Object[newLength];
        System.arraycopy(arr, 0, temp, 0, N);
        arr = temp;
    }

    public Item pop() {
        Item item = arr[--N];
        arr[N] = null;

        if (N > 0 && N == arr.length / 4)
            resize(arr.length / 2);

        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int getSize() {
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseStackIterator();
    }

    private class ReverseStackIterator implements Iterator<Item> {

        int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            if (i < 0)
                throw new NoSuchElementException("No next element");

            return arr[--i];
        }
    }
}

class TestIterableResizableGenericStack {
    public static void main(String[] args) {
        IterableResizableGenericStack<Integer> stack = new IterableResizableGenericStack<>();
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

        for (Integer integer : stack)
            System.out.println(integer);
        //1
        //2
        //3
        //4
        //5
        //6
        //7
        //8
        //9
        //10
    }
}
