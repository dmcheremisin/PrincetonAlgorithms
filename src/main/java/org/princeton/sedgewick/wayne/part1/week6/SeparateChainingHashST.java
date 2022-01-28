package org.princeton.sedgewick.wayne.part1.week6;

import java.util.ArrayList;
import java.util.List;

public class SeparateChainingHashST<K, V> {

    private double N; // number of key-value pairs
    private Node<K, V>[] st; // array of ST objects

    private class Node<K, V> { // linked-list node
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public SeparateChainingHashST(int s) { // 16 for HashMap is default
        st = (Node<K, V>[]) new Node[s];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % st.length);
    }

    public V get(K key) {
        int i = hash(key);
        for (Node<K, V> x = st[i]; x != null; x = x.next)
            if (key.equals(x.key)) return x.value; // found

        return null; // not found
    }

    public void put(K key, V val) {
        int i = hash(key);
        for (Node<K, V> x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = val; // override value
                return;
            }
        }
        st[i] = new Node<>(key, val, st[i]); // Search miss: add new node.
        N++;
        //System.out.println("Put key: " + key + ", load factor: " + (N / st.length));
        if ((N / st.length) >= .75) resize(st.length * 2);
    }

    public V remove(K key) {
        int i = hash(key);
        Node<K, V> prev = null;
        for (Node<K, V> node = st[i]; node != null; node = node.next) {
            if (key.equals(node.key)) {
                if (prev != null)
                    prev.next = node.next;
                else
                    st[i] = null;

                N--;
                //System.out.println("Removed key: " + key + ", load factor: " + (N / st.length));
                if ((N / st.length) <= .25) resize(st.length / 2);

                return node.value;
            }
            prev = node;
        }
        return null;
    }

    private void resize(int s) {
        //System.out.println("Resize to: " + s);
        SeparateChainingHashST<K, V> newST = new SeparateChainingHashST<>(s);
        for (Node<K, V> node : st) {
            while (node != null) {
                newST.put(node.key, node.value);
                node = node.next;
            }
        }
        this.st = newST.st;
    }

    public Iterable<K> keys() {
        List<K> list = new ArrayList<>();
        for (Node<K, V> node : st) {
            while (node != null) {
                list.add(node.key);
                node = node.next;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(4);
        st.put("1", 1);
        st.put("2", 2);
        st.put("3", 3);
        System.out.println(st.get("3"));
        st.put("4", 4);

        st.put("5", 5);
        System.out.println(st.get("5"));
        st.put("6", 6);
        st.put("7", 7);
        System.out.println(st.get("8"));
        st.remove("7");
        st.remove("5");
        st.remove("6");
        st.remove("3");
        st.remove("4");
    }

    //Put key: 1, load factor: 0.25
    //Put key: 2, load factor: 0.5
    //Put key: 3, load factor: 0.75
    //Resize to: 8
    //Put key: 1, load factor: 0.125
    //Put key: 2, load factor: 0.25
    //Put key: 3, load factor: 0.375
    //3
    //Put key: 4, load factor: 0.5
    //Put key: 5, load factor: 0.625
    //5
    //Put key: 6, load factor: 0.75
    //Resize to: 16
    //Put key: 1, load factor: 0.0625
    //Put key: 2, load factor: 0.125
    //Put key: 3, load factor: 0.1875
    //Put key: 4, load factor: 0.25
    //Put key: 5, load factor: 0.3125
    //Put key: 6, load factor: 0.375
    //Put key: 7, load factor: 0.4375
    //null
    //Removed key: 7, load factor: 0.375
    //Removed key: 5, load factor: 0.3125
    //Removed key: 6, load factor: 0.25
    //Resize to: 8
    //Put key: 1, load factor: 0.125
    //Put key: 2, load factor: 0.25
    //Put key: 3, load factor: 0.375
    //Put key: 4, load factor: 0.5
    //Removed key: 3, load factor: 0.375
    //Removed key: 4, load factor: 0.25
    //Resize to: 4
    //Put key: 1, load factor: 0.25
    //Put key: 2, load factor: 0.5

}
