package org.princeton.sedgewick.wayne.part2.week1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler { 

    public static void main(String[] args) { 

        // timeout connection after 500 miliseconds
        System.setProperty("sun.net.client.defaultConnectTimeout", "500");
        System.setProperty("sun.net.client.defaultReadTimeout",    "1000");

        // initial web page
        String s = args[0];

        // initial web page
        int size = Integer.parseInt(args[1]);

        // list of web pages to be examined
        Queue<String> queue = new Queue<>();
        queue.enqueue(s);

        // set of examined web pages
        Set<String> marked = new HashSet<>();
        marked.add(s);

        // breadth first search crawl of web
        int counter = 0;
        while (!queue.isEmpty() && counter++ < size) {
            String v = queue.dequeue();
            System.out.println(v);

            String input;
            try {
                In in = new In(v);
                input = in.readAll().toLowerCase();
            }
            catch (IllegalArgumentException e) {
                System.out.println("[could not open " + v + "]");
                continue;
            }

            // if (input == null) continue;


           /*************************************************************
            *  Find links of the form: http://xxx.yyy.com
            *  \\w+ for one or more alpha-numeric characters
            *  \\. for dot
            *  could take first two statements out of loop
            *************************************************************/
            String regexp = "(http|https)://(\\w+\\.)+(edu|com|gov|org)";
            Pattern pattern = Pattern.compile(regexp);

            Matcher matcher = pattern.matcher(input);

            // find and print all matches
            while (matcher.find()) {
                String w = matcher.group();
                if (!marked.contains(w)) {
                    queue.enqueue(w);
                    marked.add(w);
                }
            }

        }
    }
}
