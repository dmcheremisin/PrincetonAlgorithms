package org.princeton.sedgewick.wayne.week3.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private static final double THRESHOLD = .0001;

    private final List<LineSegment> segmentsList = new ArrayList<>();

    public FastCollinearPoints(Point[] points) { // finds all line segments containing 4 or more points
        Arrays.sort(points);
        checkPoints(points);
        addLineSegments(points);
    }

    private void checkPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Points should not be null");

        for (Point point : points) {
            if (point == null)
                throw new IllegalArgumentException("Point should not be null");
        }
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                Point point1 = points[i];
                Point point2 = points[j];
                if (point1.compareTo(point2) == 0)
                    throw new IllegalArgumentException("Points should be unique");
            }
        }
    }

    private void addLineSegments(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            Point current = points[i];
            Arrays.sort(points, i + 1, points.length - 1, current.slopeOrder());

            double initialSlope = current.slopeTo(points[i+1]);
            int counter = 0;
        }
    }

    private boolean slopesEqual(double first, double second) {
        return Math.abs(first - second) < THRESHOLD;
    }

    public int numberOfSegments() { // the number of line segments
        return 0;
    }

    public LineSegment[] segments() { // the line segments
        return null;
    }
}
