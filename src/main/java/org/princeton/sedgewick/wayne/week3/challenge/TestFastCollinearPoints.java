package org.princeton.sedgewick.wayne.week3.challenge;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import org.princeton.sedgewick.wayne.util.SortUtils;

public class TestFastCollinearPoints {

    public static final int BOUND = 32768;

    public static void main(String[] args) {

        int number = 60;
        int[] pointsCoordinates = SortUtils.randomArr(number, BOUND);
        Point[] points = new Point[number / 2];
        for (int i = 0; i < number; i += 2) {
            int x = pointsCoordinates[i];
            int y = pointsCoordinates[i + 1];
            points[i / 2] = new Point(x, y);
        }
        addSet3(points);

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, BOUND);
        StdDraw.setYscale(0, BOUND);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    private static void addSet1(Point[] points) {
        points[10] = new Point(1000, 1000);
        points[15] = new Point(2000, 2000);
        points[24] = new Point(3000, 3000);
        points[5] = new Point(4000, 4000);
        points[6] = new Point(5000, 7000);
        points[18] = new Point(10000, 14000);
        points[29] = new Point(15000, 21000);
        points[13] = new Point(20000, 28000);
    }

    private static void addSet2(Point[] points) {
        points[10] = new Point(10000, 0);
        points[15] = new Point(0, 10000);
        points[24] = new Point(3000, 7000);
        points[5] = new Point(7000, 3000);
        points[6] = new Point(20000, 21000);
        points[18] = new Point(3000, 4000);
        points[29] = new Point(14000, 15000);
        points[13] = new Point(6000, 7000);
    }

    private static void addSet3(Point[] points) {
        points[10] = new Point(19000, 10000);
        points[15] = new Point(18000, 10000);
        points[24] = new Point(32000, 10000);
        points[6] = new Point(1234, 5678);
        points[29] = new Point(14000, 10000);
    }

}
