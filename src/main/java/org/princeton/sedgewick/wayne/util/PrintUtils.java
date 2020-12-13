package org.princeton.sedgewick.wayne.util;

public class PrintUtils {

    public static void printTree(Iterable iterable) {
        System.out.println();
        for (Object o : iterable)
            System.out.print(o + " ");
        System.out.println();
    }

}
