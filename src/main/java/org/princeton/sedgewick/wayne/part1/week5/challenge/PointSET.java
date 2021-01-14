package org.princeton.sedgewick.wayne.part1.week5.challenge;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Collections;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PointSET {

    private final TreeSet<Point2D> pointsSet;

    public PointSET() {// construct an empty set of points
        pointsSet = new TreeSet<>();
    }

    public boolean isEmpty() {// is the set empty?
        return pointsSet.isEmpty();
    }

    public int size() { // number of points in the set
        return pointsSet.size();
    }

    public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
        if (p == null)
            throw new IllegalArgumentException("Point must not be null");

        pointsSet.add(p);
    }

    public boolean contains(Point2D p) { // does the set contain point p?
        if (p == null)
            throw new IllegalArgumentException("Point must not be null");

        if (isEmpty())
            return false;

        return pointsSet.contains(p);
    }

    public void draw() { // draw all points to standard draw
        pointsSet.forEach(p -> StdDraw.filledCircle(p.x(), p.y(), 0.005));
    }

    public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle (or on the boundary)
        if (rect == null)
            throw new IllegalArgumentException("Rectangle must not be null");

        if (isEmpty())
            return Collections.emptyList();

        return pointsSet.stream()
                .filter(rect::contains)
                .collect(Collectors.toList());
    }

    public Point2D nearest(Point2D p) { // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null)
            throw new IllegalArgumentException("Point must not be null");

        if (isEmpty())
            return null;

        Point2D nearest = pointsSet.first();
        double distance = p.distanceTo(nearest);
        for (Point2D pointOfSet : pointsSet) {
            double newDistance = pointOfSet.distanceTo(p);
            if (newDistance < distance) {
                nearest = pointOfSet;
                distance = newDistance;
            }
        }

        return nearest;
    }

    public static void main(String[] args) {  // unit testing of the methods (optional)
        PointSET pointSET = new PointSET();

        In in = new In(args[0]);
        while (in.hasNextLine()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D point2D = new Point2D(x, y);
            pointSET.insert(point2D);
        }
        Point2D newPoint = new Point2D(0.3, 0.5);
        StdDraw.filledCircle(newPoint.x(), newPoint.y(), 0.005);
        Point2D nearest = pointSET.nearest(newPoint);
        StdDraw.line(newPoint.x(), newPoint.y(), nearest.x(), nearest.y());

        pointSET.draw();
    }
}