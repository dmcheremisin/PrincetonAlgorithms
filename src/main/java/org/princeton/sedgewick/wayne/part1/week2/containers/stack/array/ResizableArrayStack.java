package org.princeton.sedgewick.wayne.part1.week2.containers.stack.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizableArrayStack<T> implements Iterable<T> {

    private int N;
    private T[] arr;

    public ResizableArrayStack() {
        arr = (T[]) new Object[4];
    }

    private void resize(int newLength) {
        T[] temp = (T[]) new Object[newLength];
        System.arraycopy(arr, 0, temp, 0, N);
        arr = temp;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(T item) {
        if (arr.length == N)
            resize(N * 2);

        arr[N++] = item;
    }

    public T pop() {
        T item = arr[--N];
        arr[N] = null;

        if (N > 0 && N == arr.length / 4)
            resize(arr.length / 2);

        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {

        int i = 0;

        @Override
        public boolean hasNext() {
            return i < N;
        }

        @Override
        public T next() {
            if (i >= N)
                throw new NoSuchElementException("No next element");

            return arr[i++];
        }
    }

    private class ReverseStackIterator implements Iterator<T> {

        int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            if (i < 0)
                throw new NoSuchElementException("No next element");

            return arr[--i];
        }
    }
}

class TestIterableResizableGenericStack {
    public static void main(String[] args) {
        ResizableArrayStack<Integer> stack = new ResizableArrayStack<>();
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
