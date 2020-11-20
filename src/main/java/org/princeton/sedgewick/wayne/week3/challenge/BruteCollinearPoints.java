package org.princeton.sedgewick.wayne.week3.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

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
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("Points should be unique");
            }
        }
    }

    private void addLineSegments(Point[] points) {
        Arrays.sort(points);
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
            segmentsList.add(new LineSegment(points[i], points[l]));
        }
    }

    private boolean checkCollinear(Point[] points, int i, int j, int k, int l) {
        double slope1 = points[i].slopeTo(points[j]);
        double slope2 = points[i].slopeTo(points[k]);
        double slope3 = points[i].slopeTo(points[l]);
        return slopesEqual(slope1, slope2) && slopesEqual(slope1, slope3);
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
