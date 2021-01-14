package org.princeton.sedgewick.wayne.part1.week6;

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
        for (; keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }

        keys[i] = key;
        vals[i] = val;
        N++;
    }

    private int index(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return i;

        return -1;
    }

    public Value get(Key key) {
        int index = index(key);
        return index == -1 ? null : vals[index];
    }

    public void delete(Key key) {
        int i = index(key);
        if (i == -1)
            return;

        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null)
        {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }

        N--;
        if (N > 0 && N == M / 8)
            resize(M / 2);
    }

    public Iterable<Key> keys() {
        List<Key> list = new ArrayList<>();
        for (Key key : keys)
            if (key != null)
                list.add(key);

        return list;
    }

    public static void main(String[] args) {
        LinearProbingHashST<Integer, String> lph = new LinearProbingHashST<>();
        lph.put(1, "a");
        lph.put(2, "b");
        lph.put(3, "c");
        lph.put(4, "d");
        lph.put(5, "e");
        lph.put(6, "f");
        lph.put(10, "r");
        System.out.println(lph.get(5)); // e
        lph.delete(5);
        System.out.println(lph.get(5)); // null
        System.out.println(lph.get(6)); // f
    }
}