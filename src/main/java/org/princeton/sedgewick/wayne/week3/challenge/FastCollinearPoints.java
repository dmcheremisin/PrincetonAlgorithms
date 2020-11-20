package org.princeton.sedgewick.wayne.week3.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

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
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("Points should be unique");
            }
        }
    }

    private void addLineSegments(Point[] points) {
        int length = points.length;
        Point[] pointsForSort = new Point[length];

        for (int i = 0; i < length - 2; i++) {
            Point current = points[i];

            System.arraycopy(points, i + 1, pointsForSort, i + 1, length - (i + 1));
            Arrays.sort(pointsForSort, i + 1, length - 1, current.slopeOrder());

            int counter = 0;
            double slopeForCompare = current.slopeTo(pointsForSort[i + 1]);
            for (int j = i + 2; j < length; j++) {
                Point next = pointsForSort[j];
                double nextSlope = current.slopeTo(next);

                if (slopesEqual(slopeForCompare, nextSlope)) {
                    counter++;
                } else {
                    if (counter >= 2) {
                        segmentsList.add(new LineSegment(current, pointsForSort[j - 1]));
                        counter = 0;
                    }

                    slopeForCompare = nextSlope;
                }
            }
            if (counter >= 4)
                segmentsList.add(new LineSegment(current, points[length - 1]));
        }
    }

    private boolean slopesEqual(double first, double second) {
        return Double.compare(first, second) == 0;
    }

    public int numberOfSegments() { // the number of line segments
        return segmentsList.size();
    }

    public LineSegment[] segments() { // the line segments
        return segmentsList.toArray(new LineSegment[0]);
    }

}
