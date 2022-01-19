package org.princeton.sedgewick.wayne.part1.week2.containers.queue;

import java.util.NoSuchElementException;

public class CircularQueue<T> {

    private T[] queue;
    private int front;
    private int back;

    public CircularQueue() {
        queue = (T[]) new Object[4];
    }

    public int size() {
        if (front <= back)
            return back - front;
        else
            return back - front + queue.length;
    }

    public void enqueue(T item) {
        if (size() == queue.length - 1) {
            int numItems = size();
            T[] newArray = (T[]) new Object[2 * queue.length];

            System.arraycopy(queue, front, newArray, 0, queue.length - front);
            System.arraycopy(queue, 0, newArray, queue.length - front, back);

            queue = newArray;

            front = 0;
            back = numItems;
        }

        queue[back] = item;
        if (back < queue.length - 1) back++;
        else back = 0;
    }

    public T dequeue() {
        if (size() == 0)
            throw new NoSuchElementException();

        T item = queue[front];
        queue[front] = null;
        front++;

        if (size() == 0) front = back = 0;
        else if (front == queue.length) front = 0;

        return item;
    }

    public T peek() {
        if (size() == 0)
            throw new NoSuchElementException();

        return queue[front];
    }

    public void printQueue() {
        if (front <= back)
            for (int i = front; i < back; i++)
                System.out.println(queue[i]);

        else
            for (int i = front; i < queue.length; i++)
                System.out.println(queue[i]);
            for (int i = 0; i < back; i++)
                System.out.println(queue[i]);
    }

    public static void main(String[] args) {
        CircularQueue<Integer> queue = new CircularQueue<>();
        queue.enqueue(1);
        System.out.println(queue.size());
        queue.enqueue(2);
        System.out.println(queue.size());
        queue.enqueue(3);
        System.out.println(queue.size());
        queue.enqueue(4);
        System.out.println(queue.size());
        queue.enqueue(5);
        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.dequeue());
        System.out.println(queue.size());
        System.out.println(queue.peek());
        queue.enqueue(6);
        System.out.println(queue.peek());
        System.out.println(queue.dequeue());
        System.out.println(queue.size());

        queue.printQueue();
    }


}
