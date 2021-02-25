package org.princeton.sedgewick.wayne.part2.week4.tries;

import edu.princeton.cs.algs4.Queue;

public class TrieST<V> {

    private static final int R = 256;
    private Node root;

    private static class Node {

        private Object value;
        private Node[] next = new Node[R];
    }

    public void put(String key, V value) {
        root = put(root, key, 0, value);
    }

    private Node put(Node node, String key, int position, V value) {
        if (node == null)
            node = new Node();

        if (key.length() == position) {
            node.value = value;
            return node;
        }

        char c = key.charAt(position);
        node.next[c] = put(node.next[c], key, position + 1, value);

        return node;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public V get(String key) {
        Node node = get(root, key, 0);
        return node == null ? null : (V) node.value;
    }

    private Node get(Node node, String key, int position) {
        if (node == null)
            return null;
        if (key.length() == position)
            return node;
        char c = key.charAt(position);
        return get(node.next[c], key, position + 1);
    }

    public Iterable<String> keys() {
        Queue<String> queue = new Queue<>();
        collect(root, "", queue);
        return queue;
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Node node = get(root, prefix, 0);

        Queue<String> queue = new Queue<>();
        collect(node, prefix, queue);

        return queue;
    }

    private void collect(Node node, String prefix, Queue<String> queue) {
        if (node == null)
            return;

        if (node.value != null)
            queue.enqueue(prefix);

        for (char c = 0; c < R; c++)
            collect(node.next[c], prefix + c, queue);
    }

    public static void main(String[] args) {
        TrieST<Integer> trieST = new TrieST<>();
        trieST.put("one", 1);
        trieST.put("two", 2);
        trieST.put("three", 3);
        trieST.put("four", 4);
        trieST.put("apple", 4);
        trieST.put("bicycle", 4);

        System.out.println(trieST.contains("one")); // true
        System.out.println(trieST.contains("two")); // true
        System.out.println(trieST.contains("three")); // true
        System.out.println(trieST.contains("four")); // true
        System.out.println(trieST.contains("five")); // false

        System.out.println(trieST.get("one")); // 1
        System.out.println(trieST.get("two")); // 2
        System.out.println(trieST.get("three")); // 3
        System.out.println(trieST.get("four")); // 4
        System.out.println(trieST.get("five")); // null

        System.out.println(trieST.keys()); // apple bicycle four one three two
        System.out.println(trieST.keysWithPrefix("t")); // three two

    }

}
