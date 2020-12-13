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

    public V get(K key) {
        if (size() == 0)
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
        if (size() == 0) {
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
        BinarySearchTreeIteration<String, Integer> bst = new BinarySearchTreeIteration<>();
        System.out.println(bst.size());
        System.out.println(bst.get("H"));
        bst.put("H", 1);
        System.out.println(bst.get("H"));
        bst.put("B", 1);
        bst.put("Y", 1);
        bst.put("A", 1);
        bst.put("X", 1);
        bst.put("C", 1);
        bst.put("Z", 1);
        printTree(bst.getDepthFirstTree());
        printTree(bst.getBreadthFirstTree());
    }
}
