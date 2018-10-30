import java.awt.Color;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture pic;
    private SeamRemover sr = new SeamRemover();

    public SeamCarver(Picture picture) {
        pic = new Picture(picture);
    }

    public Picture picture() {
        return new Picture(pic);
    }

    public int width() {
        return pic.width();
    }

    public int height() {
        return pic.height();
    }

    public double energy(int x, int y) {
        if (x < 0 || x > width() - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (y < 0 || y > height() - 1) {
            throw new IndexOutOfBoundsException();
        }

        Color rgbr, rgbl, rgbu, rgbd;
        int xr = x + 1;
        int xl = x - 1;
        int yu = y + 1;
        int yd = y - 1;

        if (x == width() - 1) {
            xr = 0;
        }
        if (x == 0) {
            xl = width() - 1;
        }

        if (y == height() - 1) {
            yu = 0;
        }
        if (y == 0) {
            yd = height() - 1;
        }

        rgbr = pic.get(xr, y);
        rgbl = pic.get(xl, y);
        rgbu = pic.get(x, yu);
        rgbd = pic.get(x, yd);

        double dx = (Math.pow(rgbr.getRed() - rgbl.getRed(), 2)
                + Math.pow(rgbr.getBlue() - rgbl.getBlue(), 2)
                + Math.pow(rgbr.getGreen() - rgbl.getGreen(), 2));

        double dy = (Math.pow(rgbu.getRed() - rgbd.getRed(), 2)
                + Math.pow(rgbu.getBlue() - rgbd.getBlue(), 2)
                + Math.pow(rgbu.getGreen() - rgbd.getGreen(), 2));

        return dx + dy;
    }


    public int[] findHorizontalSeam() {
        Picture thi = new Picture(height(), width());
        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                thi.set(y, x, this.pic.get(x, y));
            }
        }

        SeamCarver temp = new SeamCarver(thi);
        int[] seam = temp.findVerticalSeam();

        return seam;
    }

    public int[] findVerticalSeam() {
        double[][] energyPath = new double[width()][height()];
        double[][] energys = new double[width()][height()];
        int[][] indexTo = new int[width()][height()];

        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (y == 0) {
                    energyPath[x][y] = energy(x, y);
                } else {
                    energyPath[x][y] = Double.MAX_VALUE;
                }
                energys[x][y] = energy(x, y);
            }
        }

        for (int y = 0; y < height() - 1; y++) {
            for (int x = 0; x < width(); x++) {
                if (energyPath[x][y + 1] > energyPath[x][y] + energys[x][y + 1]) {
                    energyPath[x][y + 1] = energyPath[x][y] + energys[x][y + 1];
                    indexTo[x][y + 1] = x;
                }

                if (x > 0) {
                    if (energyPath[x - 1][y + 1] > energyPath[x][y] + energys[x - 1][y + 1]) {
                        energyPath[x - 1][y + 1] = energyPath[x][y] + energys[x - 1][y + 1];
                        indexTo[x - 1][y + 1] = x;
                    }
                }

                if (x < width() - 1) {
                    if (energyPath[x + 1][y + 1] > energyPath[x][y] + energys[x + 1][y + 1]) {
                        energyPath[x + 1][y + 1] = energyPath[x][y] + energys[x + 1][y + 1];
                        indexTo[x + 1][y + 1] = x;
                    }
                }
            }
        }

        double minoo = Double.MAX_VALUE;

        int lowestenergy = 0;

        for (int i = 0; i < width(); i++) {
            if (energyPath[i][height() - 1] < minoo) {
                minoo = energyPath[i][height() - 1];
                lowestenergy = i;
            }
        }

        int[] semen = new int[height()];

        semen[height() - 1] = lowestenergy;

        lowestenergy = indexTo[lowestenergy][height() - 1];
        for (int k = height() - 2; k >= 0; k--) {
            semen[k] = lowestenergy;
            lowestenergy = indexTo[lowestenergy][k];
        }

        return semen;

    }

    public void removeHorizontalSeam(int[] seam) {
        sr.removeHorizontalSeam(pic, seam);
    }

    public void removeVerticalSeam(int[] seam) {
        sr.removeVerticalSeam(pic, seam);
    }


}
