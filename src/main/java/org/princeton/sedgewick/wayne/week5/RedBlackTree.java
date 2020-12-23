package org.princeton.sedgewick.wayne.week5;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.princeton.sedgewick.wayne.util.PrintUtils.printTree;

public class RedBlackTree<K extends Comparable<K>, V> {

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

    // ------------------- Diff from BinarySearchTree --------------------------
    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = Node.BLACK;
    }

    public Node put(Node node, K key, V value) {
        if (node == null)
            return new Node(key, value, 1, false);

        int cmp = key.compareTo(node.key);
        if (cmp > 0)
            node.right = put(node.right, key, value);
        else if (cmp < 0)
            node.left = put(node.left, key, value);
        else
            node.value = value;

        if (isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);

        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private boolean isRed(Node node) {
        return node != null && node.color;
    }

    private Node rotateLeft(Node top) {
        Node right = top.right;
        top.right = right.left;
        right.left = top;

        right.color = top.color;
        right.left.color = Node.RED;

        right.size = top.size;
        top.size = size(top.left) + size(top.right);

        return right;
    }

    private Node rotateRight(Node top) {
        Node left = top.left;
        top.left = left.right;
        left.right = top;

        left.color = top.color;
        top.color = Node.RED;

        left.size = top.size;
        top.size = size(top.left) + size(top.right);

        return left;
    }

    private void flipColors(Node top) {
        top.color = Node.RED;
        top.left.color = Node.BLACK;
        top.right.color = Node.BLACK;
    }

    // ------------------- Diff from BinarySearchTree end ------------------------

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

    private class Node {

        private static final boolean RED = true;
        private static final boolean BLACK = false;

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
        printTree(bst.getBreadthFirstTree()); // H B Z C X

        System.out.println(bst.keys()); // [B, C, H, X, Z]
        System.out.println(bst.keys("D", "Y")); // [H, X]
    }
}
