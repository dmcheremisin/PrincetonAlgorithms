package org.princeton.sedgewick.wayne.week6;

import java.util.ArrayList;
import java.util.List;

public class LinearProbingHashST<Key, Value> {

    private int N; // number of key-value pairs in the table
    private int M; // size of linear-probing table
    private Key[] keys; // the keys
    private Value[] vals; // the values

    public LinearProbingHashST() {
        this(16);
    }

    public LinearProbingHashST(int cap) {
        M = cap;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(cap);
        for (int i = 0; i < M; i++)
            if (keys[i] != null)
                t.put(keys[i], vals[i]);

        keys = t.keys;
        vals = t.vals;
        M = t.M;
        N = t.N;
    }

    public void put(Key key, Value val) {
        if (N >= M / 2)
            resize(2 * M); // double M (see text)

        int i = hash(key);
        for (; keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }

        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return vals[i];

        return null;
    }

    public Iterable<Key> keys() {
        List<Key> list = new ArrayList<>();
        for (Key key : keys)
            if (key != null)
                list.add(key);

        return list;
    }
}