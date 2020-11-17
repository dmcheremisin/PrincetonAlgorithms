package org.princeton.sedgewick.wayne.week3.challenge;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import org.princeton.sedgewick.wayne.util.SortUtils;

public class TestBruteCollinearPoints {

    public static final int BOUND = 32768;

    public static void main(String[] args) {

        int number = 400;
        int[] pointsCoordinates = SortUtils.randomArr(number, BOUND);
        Point[] points = new Point[number / 2];
        for (int i = 0; i < number; i += 2) {
            int x = pointsCoordinates[i];
            int y = pointsCoordinates[i + 1];
            points[i / 2] = new Point(x, y);
        }
        points[10] = new Point(1000, 1000);
        points[15] = new Point(2000, 2000);
        points[24] = new Point(3000, 3000);
        points[35] = new Point(4000, 4000);
        points[6] = new Point(5000, 7000);
        points[18] = new Point(10000, 14000);
        points[29] = new Point(15000, 21000);
        points[48] = new Point(20000, 28000);

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, BOUND);
        StdDraw.setYscale(0, BOUND);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
