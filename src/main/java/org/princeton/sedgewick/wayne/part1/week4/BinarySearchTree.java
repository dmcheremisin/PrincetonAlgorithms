package org.princeton.sedgewick.wayne.part1.week4;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.princeton.sedgewick.wayne.util.PrintUtils.printTree;

public class BinarySearchTree<K extends Comparable<K>, V> {

    protected Node root;

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public V get(K key) {
        return get(root, key);
    }

    public V get(Node node, K key) {
        if (node == null) return null;

        int cmp = node.key.compareTo(key);
        if (cmp > 0)
            return get(node.right, key);
        if (cmp < 0)
            return get(node.left, key);

        return node.value;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public Node put(Node node, K key, V value) {
        if (node == null) return new Node(key, value, 1);

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
        return root == null ? null : min(root).key;
    }

    private Node min(Node node) {
        return node.left == null ? node : min(node.left);
    }

    public K max() {
        return root == null ? null : max(root).key;
    }

    private Node max(Node node) {
        return node.right == null ? node : max(node.right);
    }

    public K floor(K key) {
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    private Node floor(Node node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp == 0)
            return node;
        if (cmp < 0)
            return floor(node.left, key);

        Node t = floor(node.right, key);
        if (t != null) return t;

        return node;
    }

    public K ceiling(K key) {
        Node x = ceiling(root, key);
        return x == null ? null : x.key;
    }

    private Node ceiling(Node node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp == 0)
            return node;
        if (cmp > 0)
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
        if (k < size)
            return select(node.left, k);
        if (k > size)
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

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = delete(node.left, key);
        else if (cmp > 0)
            node.right = delete(node.right, key);
        else {
            if (node.right == null)
                return node.left;
            if (node.left == null)
                return node.left;

            Node temp = node;
            node = min(node.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public Iterable<K> keys() {
        return keys(min(), max());
    }

    public Iterable<K> keys(K lo, K hi) {
        Queue<K> queue = new LinkedList<>();
        keys(queue, root, lo, hi);
        return queue;
    }

    private void keys(Queue<K> queue, Node node, K lo, K hi) {
        if (node == null)
            return;

        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);

        if (cmplo < 0)
            keys(queue, node.left, lo, hi);
        if (cmplo <= 0 && cmphi >= 0)
            queue.add(node.key);
        if (cmphi > 0)
            keys(queue, node.right, lo, hi);
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

    protected class Node {

        protected K key;
        protected V value;

        protected Node left;
        protected Node right;

        protected int size; // nodes in subtree

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
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

        bst.delete("Y");
        printTree(bst.getBreadthFirstTree()); // H B Z C X

        System.out.println(bst.keys()); // [B, C, H, X, Z]
        System.out.println(bst.keys("D", "Y")); // [H, X]
    }
}
