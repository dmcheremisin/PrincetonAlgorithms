package org.princeton.sedgewick.wayne.week4;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.princeton.sedgewick.wayne.util.PrintUtils.printTree;

public class BinarySearchTreeIteration<K extends Comparable<K>, V> {

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
        return size() == 0;
    }

    public V get(K key) {
        if (isEmpty())
            return null;

        Node node = root;
        while (node != null) {
            int cmp = node.key.compareTo(key);
            if (cmp > 0)
                node = node.right;
            else if (cmp < 0)
                node = node.left;
            else
                return node.value;
        }
        return null;
    }

    public void put(K key, V value) {
        if (isEmpty()) {
            root = new Node(key, value, 1);
            return;
        }

        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp > 0) {
                if (node.right == null) {
                    node.right = new Node(key, value, 1);
                    node.size += 1;
                    break;
                } else {
                    node = node.right;
                }
            } else if (cmp < 0) {
                if (node.left == null) {
                    node.left = new Node(key, value, 1);
                    node.size += 1;
                    break;
                } else {
                    node = node.left;
                }
            } else
                node.value = value;
        }
    }

    public K min() {
        if (isEmpty())
            return null;

        Node node = root;
        while (node.left != null) {
            node = node.left;
        }

        return node.key;
    }

    public K max() {
        if (isEmpty())
            return null;

        Node node = root;
        while (node.right != null) {
            node = node.right;
        }

        return node.key;
    }

    public K floor(K key) {
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    private Node floor(Node node, K key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp == 0)
            return node;
        else if (cmp < 0)
            return floor(node.left, key);

        Node t = floor(node.right, key);
        if (t != null)
            return t;

        return node;
    }

    public K ceiling(K key) {
        Node x = ceiling(root, key);
        return x == null ? null : x.key;
    }

    private Node ceiling(Node node, K key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp == 0)
            return node;
        else if (cmp > 0)
            return ceiling(node.right, key);

        Node t = ceiling(node.left, key);
        if (t != null)
            return t;

        return node;
    }

    public K select(int k) {
        Node node = select(root, k);
        return node == null ? null : node.key;
    }

    private Node select(Node node, int k) {
        if (node == null)
            return null;

        int size = size(node.left);
        if (size > k)
            return select(node.left, k);
        else if (size < k)
            return select(node.right, k - size - 1);

        return node;
    }

    public int rank(K key) {
        return rank(root, key);
    }

    private int rank(Node node, K key) {
        if (node == null)
            return 0;

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            return rank(node.left, key);
        else if (cmp > 0)
            return 1 + size(node.left) + rank(node.right, key);

        return size(node.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null)
            return node.right;

        node.left = deleteMin(node.left);
        node.size = 1 + size(node.left) + size(node.right);

        return node;
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

        private final K key;
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
        BinarySearchTreeIteration<String, Integer> bst = new BinarySearchTreeIteration<>();
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
        System.out.println(bst.floor("E")); // C
        System.out.println(bst.ceiling("W")); // X
        System.out.println(bst.select(1)); // B
        System.out.println(bst.rank("B")); // 1

        bst.deleteMin();
        printTree(bst.getBreadthFirstTree()); // H B Y C X Z
    }
}
