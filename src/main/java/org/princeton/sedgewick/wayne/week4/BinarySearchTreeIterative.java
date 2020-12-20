package org.princeton.sedgewick.wayne.week4;

import static org.princeton.sedgewick.wayne.util.PrintUtils.printTree;

public class BinarySearchTreeIterative<K extends Comparable<K>, V> extends BinarySearchTree<K, V> {

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

        Node min = min(root);
        return min == null ? null : min.key;
    }

    private Node min(Node node) {
        while (node != null && node.left != null)
            node = node.left;

        return node;
    }

    public K max() {
        if (isEmpty())
            return null;

        Node max = max(root);
        return max == null ? null : max.key;
    }

    private Node max(Node node) {
        while (node != null && node.right != null)
            node = node.right;

        return node;
    }

    public static void main(String[] args) {
        BinarySearchTreeIterative<String, Integer> bst = new BinarySearchTreeIterative<>();
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
