package org.princeton.sedgewick.wayne.part2.week1;

import edu.princeton.cs.algs4.In;
import org.princeton.sedgewick.wayne.part1.week2.containers.bag.Bag;
import org.princeton.sedgewick.wayne.part1.week2.containers.queue.ListQueue;

public class DigraphDepthFirstOrder {

    private final Digraph digraph;
    private final boolean marked[];

    private final ListQueue<Integer> pre;   // queue
    private final ListQueue<Integer> post;  // queue
    private final Bag<Integer> reversePost; // stack

    public DigraphDepthFirstOrder(Digraph digraph) {
        this.digraph = digraph;
        this.marked = new boolean[digraph.V()];

        pre = new ListQueue<>();
        post = new ListQueue<>();
        reversePost = new Bag<>();

        for (int v = 0; v < digraph.V(); v++)
            if (!marked[v])
                dfs(v);
    }

    private void dfs(int v) {
        pre.enqueue(v);

        marked[v] = true;
        for (Integer w : digraph.adj(v))
            if (!marked[w])
                dfs(w);

        post.enqueue(v);
        reversePost.add(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

    public static void main(String[] args) {
        Digraph graph = new Digraph(new In(args[0]));
        DigraphDepthFirstOrder order = new DigraphDepthFirstOrder(graph);

        System.out.println(order.pre);
        //[0, 5, 4, 3, 2, 1, 6, 9, 11, 12, 10, 8, 7]

        System.out.println(order.post);
        //[2, 3, 4, 5, 1, 0, 12, 11, 10, 9, 8, 6, 7]

        System.out.println(order.reversePost);
        //[7, 6, 8, 9, 10, 11, 12, 0, 1, 5, 4, 3, 2]
    }
}
