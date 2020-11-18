package org.princeton.sedgewick.wayne.week3.challenge;

import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {

    final double THRESHOLD = .0001;

    private final List<LineSegment> segmentsList = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
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
        int length = points.length;
        for (int i = 0; i < length - 3; i++) {
            for (int j = i + 1; j < length - 2; j++) {
                for (int k = j + 1; k < length - 1; k++) {
                    for (int l = k + 1; l < length; l++) {
                        addLineSegment(points, i, j, k, l);
                    }
                }
            }
        }
    }

    private void addLineSegment(Point[] points, int i, int j, int k, int l) {
        if (checkCollinear(points, i, j, k, l)) {
            int[] maxMin = getMaxMin(points, i, j, k, l);
            segmentsList.add(new LineSegment(points[maxMin[0]], points[maxMin[1]]));
        }
    }

    private int[] getMaxMin(Point[] points, int... arr) {
        int min = arr[0];
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            Point current = points[arr[i]];
            if (current.compareTo(points[min]) < 0)
                min = arr[i];
            if (current.compareTo(points[max]) > 0)
                max = arr[i];
        }

        return new int[]{min, max};
    }

    private boolean checkCollinear(Point[] points, int i, int j, int k, int l) {
        double slope1 = points[i].slopeTo(points[j]);
        double slope2 = points[i].slopeTo(points[k]);
        double slope3 = points[i].slopeTo(points[l]);
        return slopesEqual(slope1, slope2) && slopesEqual(slope1, slope3);
    }

    private boolean slopesEqual(double first, double second) {
        return Math.abs(first - second) < THRESHOLD;
    }

    public int numberOfSegments() { // the number of line segments
        return segmentsList.size();
    }

    public LineSegment[] segments() { // the line segments
        return segmentsList.toArray(new LineSegment[0]);
    }
}