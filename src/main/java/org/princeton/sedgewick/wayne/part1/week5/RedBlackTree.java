package org.princeton.sedgewick.wayne.part1.week5;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.princeton.sedgewick.wayne.util.PrintUtils.printTree;

public class RedBlackTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {

        private K key;
        private V value;

        private Node left;
        private Node right;

        private int size;
        private boolean color;

        public Node(K key, V value, int size, boolean color) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.color = color;
        }
    }

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

    private V get(Node node, K key) {
        if (node == null) return null;

        int cmp = node.key.compareTo(key);
        if (cmp > 0)
            return get(node.right, key);
        if (cmp < 0)
            return get(node.left, key);

        return node.value;
    }

    // ------------------- Diff from BinarySearchTree --------------------------

    private boolean isRed(Node node) {
        return node != null && node.color;
    }

    private Node rotateLeft(Node top) {
        Node right = top.right;
        top.right = right.left;
        right.left = top;

        right.color = top.color;
        right.left.color = RED;

        right.size = top.size;
        top.size = size(top.left) + size(top.right);

        return right;
    }

    private Node rotateRight(Node top) {
        Node left = top.left;
        top.left = left.right;
        left.right = top;

        left.color = top.color;
        top.color = RED;

        left.size = top.size;
        top.size = size(top.left) + size(top.right);

        return left;
    }

    private void flipColors(Node top) {
        top.color = RED;
        if (top.left != null)
            top.left.color = BLACK;
        if (top.right != null)
            top.right.color = BLACK;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, K key, V value) {
        if (h == null)
            return new Node(key, value, 1, RED);

        int cmp = key.compareTo(h.key);
        if (cmp > 0)
            h.right = put(h.right, key, value);
        else if (cmp < 0)
            h.left = put(h.left, key, value);
        else
            h.value = value;

        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);

        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);

        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    public void delete(K key) {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);

        if (!isEmpty())
            root.color = BLACK;
    }

    private Node delete(Node node, K key) {
        if (key.compareTo(node.key) < 0) {
            if (!isRed(node.left) && !isRed(node.left.left))
                node = moveRedLeft(node);

            node.left = delete(node.left, key);
        } else {
            if (isRed(node.left))
                node = rotateRight(node);

            if (key.compareTo(node.key) == 0 && (node.right == null))
                return null;

            if (!isRed(node.right) && !isRed(node.right.left))
                node = moveRedRight(node);

            if (key.compareTo(node.key) == 0) {
                node.value = get(node.right, min(node.right).key);
                node.key = min(node.right).key;
                node.right = deleteMin(node.right);
            } else
                node.right = delete(node.right, key);
        }

        return balance(node);
    }

    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);

        if (!isEmpty())
            root.color = BLACK;
    }

    private Node deleteMax(Node node) {
        if (isRed(node.left))
            node = rotateRight(node);

        if (node.right == null)
            return null;

        if (!isRed(node.right) && !isRed(node.right.left))
            node = moveRedRight(node);

        node.right = deleteMax(node.right);

        return balance(node);
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node node) {
        if (node.left == null)
            return null;

        if (!isRed(node.left) && !isRed(node.left.left))
            node = moveRedLeft(node);

        node.left = deleteMin(node.left);

        return balance(node);
    }

    private Node balance(Node node) {
        if (isRed(node.right))
            node = rotateLeft(node);

        return node;
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }
        return node;
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (node.left != null && !isRed(node.left.left))
            node = rotateRight(node);

        return node;
    }

    // ------------------- Diff from BinarySearchTree end ------------------------

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
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp < 0)  return floor(node.left, key);

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
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp > 0)  return ceiling(node.right, key);

        Node t = ceiling(node.left, key);
        if (t != null) return t;

        return node;
    }

    public K select(int k) {
        Node node = select(root, k);
        return node == null ? null : node.key;
    }

    private Node select(Node node, int k) {
        if (node == null) return null;

        int size = size(node.left);
        if (k < size) return select(node.left, k);
        if (k > size) return select(node.right, k - size - 1);

        return node;
    }

    public int rank(K key) {
        return rank(root, key);
    }

    private int rank(Node node, K key) {
        if (node == null) return 0;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) return rank(node.left, key);
        if (cmp > 0) return 1 + size(node.left) + rank(node.right, key);

        return size(node.left);
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
        if (node == null) return;

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
        if (node == null) return;

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

    public static void main(String[] args) {
        RedBlackTree<String, Integer> bst = new RedBlackTree<>();
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
        printTree(bst.getBreadthFirstTree()); // Z B C H X

        System.out.println(bst.keys()); // [B, C, H, X, Z]
        System.out.println(bst.keys("D", "Y")); // [H, X]
    }
}
