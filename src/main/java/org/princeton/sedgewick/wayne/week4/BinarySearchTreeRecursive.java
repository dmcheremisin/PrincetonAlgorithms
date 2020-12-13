package org.princeton.sedgewick.wayne.week4;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.princeton.sedgewick.wayne.util.PrintUtils.printTree;

public class BinarySearchTreeRecursive<K extends Comparable<K>, V> {

    private Node root;

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)
            return 0;

        return node.size;
    }

    private boolean isEmpty() {
        return root == null;
    }

    public V get(K key) {
        return get(root, key);
    }

    public V get(Node node, K key) {
        if (node == null)
            return null;

        int cmp = node.key.compareTo(key);
        if (cmp > 0)
            return get(node.right, key);
        else if (cmp < 0)
            return get(node.left, key);
        else
            return node.value;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public Node put(Node node, K key, V value) {
        if (node == null)
            return new Node(key, value, 1);

        int cmp = key.compareTo(node.key);
        if (cmp > 0)
            node.right = put(node.right, key, value);
        else if (cmp < 0)
            node.left = put(node.left, key, value);
        else
            node.value = value;

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public K min() {
        if (isEmpty())
            return null;

        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null)
            return node;

        return min(node.left);
    }

    public K max() {
        if (isEmpty())
            return null;

        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null)
            return node;

        return max(node.right);
    }

    public Iterable<K> getDepthFirstTree() {
        Node node = root;
        List<K> keys = new LinkedList<>();
        addNode(node, keys);
        return keys;
    }

    private void addNode(Node node, List<K> keys) {
        if (node == null)
            return;

        keys.add(node.key);
        addNode(node.left, keys);
        addNode(node.right, keys);
    }

    public Iterable<K> getBreadthFirstTree() {
        List<K> keys = new LinkedList<>();

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            keys.add(node.key);

            if (node.left != null)
                queue.add(node.left);

            if (node.right != null)
                queue.add(node.right);
        }

        return keys;
    }

    private class Node {

        private K key;
        private V value;

        private Node left;
        private Node right;

        private int size; // nodes in subtree

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public static void main(String[] args) {
        BinarySearchTreeRecursive<String, Integer> bst = new BinarySearchTreeRecursive<>();
        System.out.println(bst.size()); // 0
        System.out.println(bst.get("H")); // null
        bst.put("H", 1);
        System.out.println(bst.get("H")); // 1
        bst.put("B", 1);
        bst.put("Y", 1);
        bst.put("A", 1);
        bst.put("X", 1);
        bst.put("C", 1);
        bst.put("Z", 1);
        printTree(bst.getDepthFirstTree()); // H B A C Y X Z
        printTree(bst.getBreadthFirstTree()); // H B Y A C X Z
        System.out.println(bst.min()); // A
        System.out.println(bst.max()); // Z
    }
}
