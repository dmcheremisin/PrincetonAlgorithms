package org.princeton.sedgewick.wayne.week3.challenge;

import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {

    private final List<LineSegment> segmentsList = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        int length = points.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1; j++){
                segmentsList.add(new LineSegment(points[i], points[j]));
            }
        }
    }

    public int numberOfSegments() { // the number of line segments
        return segmentsList.size();
    }

    public LineSegment[] segments() { // the line segments
        return segmentsList.toArray(new LineSegment[0]);
    }
}