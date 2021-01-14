package org.princeton.sedgewick.wayne.part1.week4;

public class BinarySearchSortedArray<K extends Comparable<K>, V> {

    private K[] keys;
    private V[] values;
    private int N;

    public BinarySearchSortedArray(int size) {
        this.keys = (K[]) new Comparable[size];
        this.values = (V[]) new Object[size];
        this.N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public K select(int rank) {
        if (isEmpty() || rank >= N)
            return null;

        return keys[rank];
    }

    public int rank(K key) {
        if (isEmpty())
            return 0;

        return rank(key, 0, N);
    }

    private int rank(K key, int lo, int hi) {
        if (lo > hi)
            return lo;
        if (lo == N)
            return N;

        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0)
            return rank(key, lo, mid - 1);
        else if (cmp > 0)
            return rank(key, mid + 1, hi);
        return mid;
    }

    public V get(K key) {
        if (isEmpty())
            return null;

        int rank = rank(key);
        if (rank < N && keys[rank].compareTo(key) == 0)
            return values[rank];
        return null;
    }

    public void put(K key, V value) {
        int rank = rank(key);
        if (rank < N && keys[rank].compareTo(key) == 0) {
            values[rank] = value;
            return;
        }

        for (int i = N; i > rank; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[rank] = key;
        values[rank] = value;
        N++;
    }

    public void delete(K key) {
        if (isEmpty())
            return;

        int rank = rank(key);

        if (rank >= N)
            return;

        if (rank == 0 && keys[0].compareTo(key) != 0)
            return;

        for (int i = rank; i < N - 1; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }
        keys[--N] = null;
        values[N] = null;
    }

    public K min() {
        if (isEmpty())
            return null;

        return keys[0];
    }

    public K max() {
        if (isEmpty())
            return null;

        return keys[N - 1];
    }

    public K ceiling(K key) {
        if (isEmpty())
            return null;

        int rank = rank(key);
        return keys[rank];
    }

    public K floor(K key) {
        if (isEmpty())
            return null;

        int rank = rank(key);
        return rank - 1 < 0 ? null : keys[rank - 1] ;
    }

    public void printTreeKeys(){
        System.out.println();
        for (K key : keys)
            System.out.printf("%1$3d", key);
        System.out.println();
    }

    public static void main(String[] args) {
        BinarySearchSortedArray<Integer, String> arr = new BinarySearchSortedArray<>(10);
        System.out.println(arr.isEmpty());

        arr.put(2, "a");
        arr.put(5, "a");
        arr.put(1, "a");
        arr.put(9, "a");
        arr.put(4, "a");
        arr.put(7, "a");
        arr.put(3, "a");
        arr.put(8, "a");
        arr.put(10, "a");
        arr.put(6, "a");
        arr.printTreeKeys(); // 1  2  3  4  5  6  7  8  9 10
        System.out.println(arr.rank(6)); // 5
        System.out.println(arr.select(6)); // 7
        System.out.println(arr.select(12)); // null

        arr.delete(12);
        arr.printTreeKeys(); // 1  2  3  4  5  6  7  8  9 10

        arr.delete(3);
        arr.printTreeKeys(); //  1  2  4  5  6  7  8  9 10null

        arr.put(12, "a");
        arr.printTreeKeys(); //   1  2  4  5  6  7  8  9 10 12
        System.out.println(arr.select(10)); // 12
        System.out.println(arr.floor(11)); // 10
        System.out.println(arr.ceiling(11)); // 12
    }


}
