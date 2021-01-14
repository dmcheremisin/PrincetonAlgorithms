package org.princeton.sedgewick.wayne.part1.week6;

import java.util.ArrayList;
import java.util.List;

public class SeparateChainingHashST<Key, Value> {

    private int N; // number of key-value pairs
    private int M; // hash table size
    private SequentialSearchST<Key, Value>[] st; // array of ST objects

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) { // Create M linked lists.
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST();
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        int hash = hash(key);
        return st[hash].get(key);
    }

    public void put(Key key, Value val) {
        int hash = hash(key);
        st[hash].put(key, val);
    }

    public Iterable<Key> keys() {
        List<Key> list = new ArrayList<>();
        for (SequentialSearchST<Key, Value> sst : st)
            list.addAll(sst.keys());

        return list;
    }

}