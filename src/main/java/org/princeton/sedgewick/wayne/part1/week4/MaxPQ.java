package org.princeton.sedgewick.wayne.part1.week4;

import static org.princeton.sedgewick.wayne.util.SortUtils.exch;
import static org.princeton.sedgewick.wayne.util.SortUtils.less;

public class MaxPQ<T extends Comparable<T>> {

    private T[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
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
        // we can compare parent(k/2) with child(k) and if parent is smaller - then exchange them
        while (k > 1 && less(pq[k / 2], pq[k])) {
            exch(pq, k / 2, k);
            k = k / 2;
        }
    }

    public T delMax() {
        T max = pq[1]; // get max item
        exch(pq, 1, N--); // exchange first item(max) with last item
        pq[N + 1] = null; // last item becomes null, so max item becomes removed from array
        sink(1); // sink last item
        return max;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k; // find child index
            if (j < N && less(pq[j], pq[j + 1])) // understand which child is bigger
                j++;

            if (!less(pq[k], pq[j])) // if parent is bigger, then break loop
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
        MaxPQ<Integer> pq = new MaxPQ<>(10);
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
        pq.printQueue(); // null 45 39 22 36 14 10 11 3 18 4

        pq.delMax();
        pq.printQueue(); // null 39 36 22 18 14 10 11 3 4 null

        pq.delMax();
        pq.printQueue(); // null 36 18 22 4 14 10 11 3 null null
    }


}
