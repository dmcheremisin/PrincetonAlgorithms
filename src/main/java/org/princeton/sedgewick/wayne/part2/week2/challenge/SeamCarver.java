package org.princeton.sedgewick.wayne.part2.week2.challenge;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {

    private Picture picture;
    private int height;
    private int width;
    private double[][] energy;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        this.picture = picture;
        this.height = picture.height();
        this.width = picture.width();

        updateEnergy();
    }

    private void updateEnergy() {
        energy = new double[height][width];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                energy[y][x] = energy(x, y);
    }

    // current picture
    public Picture picture() {
        return picture;
    }

    // width of current picture
    public int width() {
        return width;
    }

    // height of current picture
    public int height() {
        return height;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            throw new IllegalArgumentException("Coordinates are out of range");

        if (x == 0 || y == 0 || (x + 1) == width || (y + 1) == height)
            return 1000;

        int dx = getDeltaSquared(x + 1, y, x - 1, y);
        int dy = getDeltaSquared(x, y + 1, x, y - 1);

        return Math.sqrt(dx + dy);
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

    private double[][] copy2dArray(double[][] arr) {
        double[][] newArr = new double[arr.length][];
        for (int i = 0; i < arr.length; i++)
            newArr[i] = arr[i].clone();
        return newArr;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[][] seamEnergy = copy2dArray(energy);
        int[][] seamX = new int[height][width];
        for (int y = 1; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double[] minEnergyAndX = minVerticalEnergy(seamEnergy, x, y - 1);
                double minEnergy = energy[y][x] + minEnergyAndX[0];
                int minX = (int) minEnergyAndX[1];

                seamEnergy[y][x] = minEnergy;
                seamX[y][x] = minX;
            }
        }
        int minEnergyIndex = findMinIndexInArray(seamEnergy[height - 1]);

        int[] seam = new int[height];
        seam[height - 1] = minEnergyIndex;
        for (int y = height - 1; y > 0; y--) {
            seam[y - 1] = seamX[y][minEnergyIndex];
            minEnergyIndex = seam[y - 1];
        }

        return seam;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        double[][] seamEnergy = copy2dArray(energy);
        int[][] seamY = new int[height][width];
        for (int x = 1; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double[] minEnergyAndY = minHorizontalEnergy(seamEnergy, x - 1, y);
                double minEnergy = energy[y][x] + minEnergyAndY[0];
                int minY = (int) minEnergyAndY[1];

                seamEnergy[y][x] = minEnergy;
                seamY[y][x] = minY;
            }
        }
        int minEnergyIndex = findMinIndexInHorizontalArray(seamEnergy, width - 1);

        int[] seam = new int[width];
        seam[width - 1] = minEnergyIndex;
        for (int x = width - 1; x > 0; x--) {
            seam[x - 1] = seamY[minEnergyIndex][x];
            minEnergyIndex = seam[x - 1];
        }

        return seam;
    }

    private double[] minVerticalEnergy(double[][] seamEnergy, int x, int y) {
        double left = (x - 1) < 0 ? Double.MAX_VALUE : seamEnergy[y][x - 1];
        double mid = seamEnergy[y][x];
        double right = (x + 1) >= seamEnergy[y].length ? Double.MAX_VALUE : seamEnergy[y][x + 1];

        double min = Math.min(left, mid);
        double minX = min == left ? x - 1 : x;

        if (right < min)
            return new double[]{right, x + 1};
        else
            return new double[]{min, minX};
    }

    private double[] minHorizontalEnergy(double[][] seamEnergy, int x, int y) {
        double top = (y - 1) < 0 ? Double.MAX_VALUE : seamEnergy[y - 1][x];
        double mid = seamEnergy[y][x];
        double bottom = (y + 1) >= seamEnergy.length ? Double.MAX_VALUE : seamEnergy[y + 1][x];

        double min = Math.min(top, mid);
        double minY = min == top ? y - 1 : y;

        if (bottom < min)
            return new double[]{bottom, y + 1};
        else
            return new double[]{min, minY};
    }

    private int findMinIndexInArray(double[] arr) {
        double min = arr[0];
        int minIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private int findMinIndexInHorizontalArray(double[][] arr, int x) {
        double min = arr[0][x];
        int minIndex = 0;
        for (int y = 1; y < arr.length; y++) {
            if (min > arr[y][x]) {
                min = arr[y][x];
                minIndex = y;
            }
        }
        return minIndex;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || seam.length != width)
            throw new IllegalArgumentException("Argument is invalid");

        Picture croppedPicture = new Picture(width, height - 1);
        for (int x = 0; x < width; x++) {
            boolean shifted = false;
            int ySeamPosition = seam[x];

            for (int y = 0; y < height; y++) {
                if (y == ySeamPosition) {
                    shifted = true;
                    continue;
                }

                if (shifted)
                    croppedPicture.setRGB(x, y - 1, picture.getRGB(x, y));
                else
                    croppedPicture.setRGB(x, y, picture.getRGB(x, y));
            }
        }
        this.picture = croppedPicture;
        this.height = height - 1;
        updateEnergy();
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || seam.length != height)
            throw new IllegalArgumentException("Argument is invalid");

        Picture croppedPicture = new Picture(width - 1, height);
        for (int y = 0; y < height; y++) {
            boolean shifted = false;
            int xSeamPosition = seam[y];

            for (int x = 0; x < width; x++) {
                if (x == xSeamPosition) {
                    shifted = true;
                    continue;
                }

                if (shifted)
                    croppedPicture.setRGB(x - 1, y, picture.getRGB(x, y));
                else
                    croppedPicture.setRGB(x, y, picture.getRGB(x, y));
            }
        }
        this.picture = croppedPicture;
        this.width = width - 1;
        updateEnergy();
    }

    //  unit testing (optional)
    public static void main(String[] args) {
        SeamCarver seamCarver = new SeamCarver(new Picture(args[0]));
//        int[] verticalSeam = seamCarver.findVerticalSeam();
//        for (int x : verticalSeam) {
//            System.out.println(x);
//        }
//        System.out.println("==============");
//        int[] horizontalSeam = seamCarver.findHorizontalSeam();
//        for (int y : horizontalSeam) {
//            System.out.println(y);
//        }
//        ShowSeams.showVerticalSeam(seamCarver);

        seamCarver.picture().show();

//        for (int i = 0; i < 150; i++)
//            seamCarver.removeVerticalSeam(seamCarver.findVerticalSeam());

        for (int i = 0; i < 150; i++)
            seamCarver.removeHorizontalSeam(seamCarver.findHorizontalSeam());

        seamCarver.picture().show();
    }

}