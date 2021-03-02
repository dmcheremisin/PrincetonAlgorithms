package org.princeton.sedgewick.wayne.part2.week5.regExp;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.Stack;

public class NFA {

    private char[] re;
    private Digraph digraph;
    private int M;

    public NFA(String regexp) {
        M = regexp.length();
        re = regexp.toCharArray();
        digraph = buildEpsilonTransitionDigraph();
    }

    public Digraph buildEpsilonTransitionDigraph() {
        Digraph digraph = new Digraph(M + 1);
        Stack<Integer> ops = new Stack<>();

        for (int i = 0; i < M; i++) {
            int lp = i;

            if (re[i] == '(' || re[i] == '|') {
                ops.push(i);
            } else if (re[i] == ')') {
                int or = ops.pop();
                if (re[or] == '|') {
                    lp = ops.pop();
                    digraph.addEdge(lp, or + 1);
                    digraph.addEdge(or, i);
                } else {
                    lp = or;
                }
            }

            if (i < M - 1 && re[i + 1] == '*') {
                digraph.addEdge(lp, i + 1);
                digraph.addEdge(i+1, lp);
            }

            if(re[i] == '(' || re[i] == '*' || re[i] == ')')
                digraph.addEdge(i, i + 1);
        }
        return digraph;
    }

    public boolean recognizes(String txt) {
        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(digraph, 0);
        for (int v = 0; v < digraph.V(); v++) {
            if (dfs.marked(v))
                pc.add(v);
        }

        for (int i = 0; i < txt.length(); i++) {
            Bag<Integer> match = new Bag<>();
            for (Integer v : pc) {
                if (v == M)
                    continue;
                if (((re[v] == txt.charAt(i))) || re[v] == '.')
                    match.add(v + 1);
            }
            dfs = new DirectedDFS(digraph, match);
            pc = new Bag<>();
            for (int v = 0; v < digraph.V(); v++) {
                if (dfs.marked(v))
                    pc.add(v);
            }

        }
        for (Integer v : pc) {
            if (v == M)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        NFA nfa = new NFA("(.*(regexp).*)");
        System.out.println(nfa.recognizes("this string contains regexp expression")); // true
        System.out.println(nfa.recognizes("this string doesn't contain regExp expression")); // false
    }

}
