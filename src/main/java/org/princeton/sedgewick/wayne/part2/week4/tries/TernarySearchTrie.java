package org.princeton.sedgewick.wayne.part2.week4.tries;

public class TernarySearchTrie<V> {

    private Node root;

    private class Node {

        private char c;
        private V value;
        private Node left, middle, right;

    }

    public void put(String key, V value) {
        root = put(root, key, 0, value);
    }

    private Node put(Node node, String key, int position, V value) {
        char c = key.charAt(position);

        if (node == null) {
            node = new Node();
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

    public V get(String key) {
        Node node = get(root, key, 0);
        return node == null ? null : node.value;
    }

    private Node get(Node node, String key, int position) {
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

    public static void main(String[] args) {
        TernarySearchTrie<Integer> ternarySearchTrie = new TernarySearchTrie<>();
        ternarySearchTrie.put("one", 1);
        ternarySearchTrie.put("two", 2);
        ternarySearchTrie.put("three", 3);
        ternarySearchTrie.put("four", 4);

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
    }

}
