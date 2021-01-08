package org.princeton.sedgewick.wayne.week5.challenge;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private Node root;
    private int size;

    private static final class Node {

        private Point2D point2D;
        private Node leftBottom;
        private Node rightTop;
        private RectHV rect;

        public Node(Point2D point2D) {
            this.point2D = point2D;
        }
    }

    public KdTree() {// construct an empty set of points
    }

    public boolean isEmpty() {// is the set empty?
        return root == null;
    }

    public int size() { // number of points in the set
        return size;
    }

    public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
        root = insert(root, p, true);
    }

    private Node insert(Node node, Point2D p, boolean isVertical) {
        if (node == null)
            return new Node(p);

        Point2D nodePoint = node.point2D;
        double cmp = isVertical ? p.x() - nodePoint.x() : p.y() - node.point2D.y();
        if (cmp < 0) {
            node.leftBottom = insert(node.leftBottom, p, !isVertical);
        } else {
            node.rightTop = insert(node.rightTop, p, !isVertical);
        }

        return node;
    }

    public boolean contains(Point2D p) { // does the set contain point p?
    }

    public void draw() { // draw all points to standard draw
    }

    public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle (or on the boundary)
    }

    public Point2D nearest(Point2D p) { // a nearest neighbor in the set to point p; null if the set is empty

    }

    public static void main(String[] args) {  // unit testing of the methods (optional)
        KdTree pointSET = new KdTree();

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