package org.princeton.sedgewick.wayne.part1.week4;

import static org.princeton.sedgewick.wayne.util.SortUtils.exch;
import static org.princeton.sedgewick.wayne.util.SortUtils.less;

public class MinPQ<T extends Comparable<T>> {

    private T[] pq;
    private int N = 0;

    public MinPQ(int maxN) {
        pq = (T[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(T v) {
        pq[++N] = v;
        swim(N);
    }

    private void swim(int k) {
        // parent item may be found by formula parentIndex = childIndex / 2
        // we can compare parent(k/2) with child(k) and if parent is bigger then exchange them
        while (k > 1 && less(pq[k], pq[k / 2])) {
            exch(pq, k, k / 2);
            k = k / 2;
        }
    }

    public T delMin() {
        T min = pq[1]; // get min item
        exch(pq, 1, N--); // exchange first item(min) with last item
        pq[N + 1] = null; // last item becomes null, so min item becomes removed from array
        sink(1); // sink last item
        return min;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(pq[j + 1], pq[j]))
                j++;

            if (less(pq[k], pq[j]))
                break;

            exch(pq, k, j);
            k = j;
        }
    }

    public void printQueue() {
        System.out.println();
        for (T t : pq)
            System.out.print(t + " ");
    }

    public static void main(String[] args) {
        MinPQ<Integer> pq = new MinPQ<>(10);
        pq.insert(10);
        pq.insert(4);
        pq.insert(39);
        pq.insert(3);
        pq.insert(18);
        pq.insert(11);
        pq.insert(22);
        pq.insert(45);
        pq.insert(36);
        pq.insert(14);
        pq.printQueue(); // null 3 4 11 10 14 39 22 45 36 18

        pq.delMin();
        pq.printQueue(); // null 4 10 11 18 14 39 22 45 36 null

        pq.delMin();
        pq.printQueue(); // null 10 14 11 18 36 39 22 45 null null
    }


}
