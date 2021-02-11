package org.princeton.sedgewick.wayne.part2.week2.challenge;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {

    private final Picture picture;
    private double[][] energy;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        this.picture = picture;
        energy = new double[picture.width()][picture.height()];

        for (int i = 0; i < picture.width(); i++)
            for (int j = 0; j < picture.height(); j++)
                energy[i][j] = -1;
    }

    // current picture
    public Picture picture() {
        return picture;
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x >= width() || y >= height())
            throw new IllegalArgumentException("Coordinates are out of range");

        if (energy[x][y] != -1)
            return energy[x][y];

        if (x == 0 || y == 0 || (x + 1) == width() || (y + 1) == height())
            return 1000;

        int dx = getDeltaSquared(x + 1, y, x - 1, y);
        int dy = getDeltaSquared(x, y + 1, x, y - 1);

        double energy = Math.sqrt(dx + dy);
        this.energy[x][y] = energy;

        return energy;
    }

    private int getDeltaSquared(int x1, int y1, int x2, int y2) {
        Color color1 = picture.get(x1, y1);
        int r1 = color1.getRed();
        int g1 = color1.getGreen();
        int b1 = color1.getBlue();

        Color color2 = picture.get(x2, y2);
        int r2 = color2.getRed();
        int g2 = color2.getGreen();
        int b2 = color2.getBlue();

        int dr = r1 - r2;
        int dg = g1 - g2;
        int db = b1 - b2;
        return dr * dr + dg * dg + db * db;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return new int[0];
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return new int[0];
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || seam.length != picture.width())
            throw new IllegalArgumentException("Argument is invalid");
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || seam.length != picture.height())
            throw new IllegalArgumentException("Argument is invalid");
    }

    //  unit testing (optional)
    public static void main(String[] args) {
        //HJoceanSmall.png
        Picture picture = new Picture(args[0]);
        SeamCarver seamCarver = new SeamCarver(picture);
        SCUtility.showEnergy(seamCarver);
    }

}