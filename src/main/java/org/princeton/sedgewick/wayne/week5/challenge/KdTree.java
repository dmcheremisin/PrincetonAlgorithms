package org.princeton.sedgewick.wayne.week5.challenge;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KdTree {

    private Node root;
    private int size;

    public KdTree() { // construct an empty set of points
    }

    public boolean isEmpty() { // is the set empty?
        return root == null;
    }

    public int size() { // number of points in the set
        return size;
    }

    public void insert(Point2D point2D) { // add the point to the set (if it is not already in the set)
        if (point2D == null)
            throw new IllegalArgumentException("Point must not be null");

        root = insert(root, point2D, true, root, false);
        size++;
    }

    private Node insert(Node node, Point2D point, boolean isVertical, Node parentNode, boolean isLeftBottom) {
        if (node == null)
            return new Node(point, parentNode, !isVertical, isLeftBottom);

        Point2D nodePoint = node.point2D;
        double cmp = isVertical ? point.x() - nodePoint.x() : point.y() - nodePoint.y();
        if (cmp < 0)
            node.leftBottom = insert(node.leftBottom, point, !isVertical, node, true);
        else
            node.rightTop = insert(node.rightTop, point, !isVertical, node, false);

        return node;
    }

    public boolean contains(Point2D p) { // does the set contain point p?
        if (p == null)
            throw new IllegalArgumentException("Point must not be null");

        if (isEmpty())
            return false;

        return contains(p, root, true);
    }

    private boolean contains(Point2D point, Node node, boolean isVertical) {
        if (node == null)
            return false;

        Point2D nodePoint = node.point2D;
        if (nodePoint.equals(point))
            return true;

        double cmp = isVertical ? point.x() - nodePoint.x() : point.y() - nodePoint.y();
        if (cmp < 0)
            return contains(point, node.leftBottom, !isVertical);
        else
            return contains(point, node.rightTop, !isVertical);
    }

    public void draw() { // draw all points to standard draw
        draw(root);
    }

    private void draw(Node node) {
        if (node == null)
            return;

        draw(node.leftBottom);
        node.point2D.draw();
        draw(node.rightTop);
    }

    public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle (or on the boundary)
        if (rect == null)
            throw new IllegalArgumentException("Rectangle must not be null");

        if (isEmpty())
            return Collections.emptyList();

        List<Point2D> points = new ArrayList<>();
        range(rect, root, points);
        return points;
    }

    private void range(RectHV rect, Node node, List<Point2D> points) {
        if (node == null)
            return;

        RectHV nodeRect = node.rect;
        if (!nodeRect.intersects(rect))
            return;

        Point2D nodePoint = node.point2D;
        if (rect.contains(nodePoint))
            points.add(nodePoint);

        range(rect, node.leftBottom, points);
        range(rect, node.rightTop, points);
    }

    public Point2D nearest(Point2D p) { // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null)
            throw new IllegalArgumentException("Point must not be null");

        if (isEmpty())
            return null;

        return nearest(p, root, root.point2D, true);
    }

    private Point2D nearest(Point2D point, Node node, Point2D nearest, boolean isVertical) {
        if (node == null)
            return nearest;

        double distance = nearest.distanceTo(point);

        if (node.rect.distanceTo(point) > distance)
            return nearest;

        Point2D nodePoint = node.point2D;
        if (nodePoint.distanceTo(point) < distance)
            nearest = nodePoint;

        nearest = nearest(point, node.leftBottom, nearest, !isVertical);
        return nearest(point, node.rightTop, nearest, !isVertical);
    }

    private static final class Node {

        private Point2D point2D;
        private Node leftBottom;
        private Node rightTop;
        private RectHV rect;

        public Node(Point2D point2D, Node parentNode, boolean isVertical, boolean isLeftBottom) {
            this.point2D = point2D;
            if (parentNode == null) {
                rect = new RectHV(0.0, 0.0, 1.0, 1.0);
                return;
            }

            Point2D parentPoint = parentNode.point2D;
            double ppX = parentPoint.x();
            double ppY = parentPoint.y();

            RectHV parentRect = parentNode.rect;
            double pXmin = parentRect.xmin();
            double pXmax = parentRect.xmax();
            double pYmin = parentRect.ymin();
            double pYmax = parentRect.ymax();

            if (isVertical && isLeftBottom) { // vertical and left
                rect = new RectHV(pXmin, pYmin, ppX, pYmax);
            } else if (isVertical) { // vertical and right
                rect = new RectHV(ppX, pYmin, pXmax, pYmax);
            } else if (isLeftBottom) { // horizontal and bottom
                rect = new RectHV(pXmin, pYmin, pXmax, ppY);
            } else { // horizontal and top
                rect = new RectHV(pXmin, ppY, pXmax, pYmax);
            }
        }
    }

    public static void main(String[] args) {  // unit testing of the methods (optional)
        KdTree kdTree = new KdTree();

        In in = new In(args[0]);
        while (in.hasNextLine()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D point2D = new Point2D(x, y);
            kdTree.insert(point2D);
        }
        Point2D newPoint = new Point2D(0.3, 0.5);
        StdDraw.filledCircle(newPoint.x(), newPoint.y(), 0.005);
        Point2D nearest = kdTree.nearest(newPoint);
        StdDraw.line(newPoint.x(), newPoint.y(), nearest.x(), nearest.y());

        kdTree.draw();
    }

}