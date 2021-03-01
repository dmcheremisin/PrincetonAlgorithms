package org.princeton.sedgewick.wayne.part2.week4.challenge;

import edu.princeton.cs.algs4.Queue;

public class TernarySearchTrie<V> {

    private Node<V> root;

    public static class Node<V> {

        private char c;
        private V value;
        private Node<V> left, middle, right;

    }

    public void put(String key, V value) {
        root = put(root, key, 0, value);
    }

    private Node<V> put(Node<V> node, String key, int position, V value) {
        char c = key.charAt(position);

        if (node == null) {
            node = new Node<>();
            node.c = c;
        }

        if (c < node.c)
            node.left = put(node.left, key, position, value);
        else if (c > node.c)
            node.right = put(node.right, key, position, value);
        else if (position < key.length() - 1)
            node.middle = put(node.middle, key, position + 1, value);
        else
            node.value = value;

        return node;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Node<V> getRoot() {
         return root;
    }

    public Node<V> getNode(String key) {
        return get(root, key, 0);
    }

    public V get(String key) {
        Node<V> node = get(root, key, 0);
        return node == null ? null : node.value;
    }

    public Node<V> get(Node<V> node, String key, int position) {
        if (node == null)
            return null;

        char c = key.charAt(position);
        if (c < node.c)
            return get(node.left, key, position);
        else if (c > node.c)
            return get(node.right, key, position);
        else if (position < key.length() - 1)
            return get(node.middle, key, position + 1);
        else
            return node;
    }

    public Iterable<String> keys() {
        Queue<String> queue = new Queue<String>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        if (prefix == null)
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");

        Node<V> node = get(root, prefix, 0);

        return keysWithPrefix(node, prefix);
    }

    public Queue<String> keysWithPrefix(Node<V> node, String prefix) {
        if (prefix == null)
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");

        Queue<String> queue = new Queue<>();

        if (node == null)
            return queue;

        if (node.value != null)
            queue.enqueue(prefix);

        collect(node.middle, new StringBuilder(prefix), queue);

        return queue;
    }

    private void collect(Node<V> x, StringBuilder prefix, Queue<String> queue) {
        if (x == null)
            return;

        collect(x.left, prefix, queue);
        if (x.value != null)
            queue.enqueue(prefix.toString() + x.c);

        collect(x.middle, prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }

    public boolean containsPrefix(Node<V> node, String prefix) {
        if (prefix == null)
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");

        Queue<String> queue = new Queue<>();

        if (node == null)
            return false;

        if (node.value != null)
            queue.enqueue(prefix);

        collectForContains(node.middle, new StringBuilder(prefix), queue);

        return queue.size() > 0;
    }

    private void collectForContains(Node<V> x, StringBuilder prefix, Queue<String> queue) {
        if (x == null || queue.size() > 0)
            return;

        collectForContains(x.left, prefix, queue);
        if (x.value != null)
            queue.enqueue(prefix.toString() + x.c);

        collectForContains(x.middle, prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collectForContains(x.right, prefix, queue);
    }

    public static void main(String[] args) {
        TernarySearchTrie<Integer> ternarySearchTrie = new TernarySearchTrie<>();
        ternarySearchTrie.put("one", 1);
        ternarySearchTrie.put("two", 2);
        ternarySearchTrie.put("three", 3);
        ternarySearchTrie.put("four", 4);
        ternarySearchTrie.put("apple", 4);
        ternarySearchTrie.put("bicycle", 4);

        System.out.println(ternarySearchTrie.contains("one")); // true
        System.out.println(ternarySearchTrie.contains("two")); // true
        System.out.println(ternarySearchTrie.contains("three")); // true
        System.out.println(ternarySearchTrie.contains("four")); // true
        System.out.println(ternarySearchTrie.contains("five")); // false

        System.out.println(ternarySearchTrie.get("one")); // 1
        System.out.println(ternarySearchTrie.get("two")); // 2
        System.out.println(ternarySearchTrie.get("three")); // 3
        System.out.println(ternarySearchTrie.get("four")); // 4
        System.out.println(ternarySearchTrie.get("five")); // null

        System.out.println(ternarySearchTrie.keys()); // apple bicycle four one three two
        System.out.println(ternarySearchTrie.keysWithPrefix("t")); // three two

    }

}
