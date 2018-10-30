package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.HashSet;

public class Percolation {
    private WeightedQuickUnionUF sites;
    private WeightedQuickUnionUF sitesNOBottom;
    private int N;
    private HashSet<Integer> open;


    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        sites  =  new WeightedQuickUnionUF(N * N + 2);
        sitesNOBottom = new WeightedQuickUnionUF(N * N + 2);

        open = new HashSet();
    }


    public void open(int row, int col) {
        if (ooB(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int index = rctoNum(row, col);
        open.add(index);

        if (row == 0) {
            sites.union(N * N, index);
            sitesNOBottom.union(N * N, index);
        }
        if (row == N - 1) {
            sites.union(N * N + 1, index);
        }

        if (!ooB(row - 1, col)) {
            int indexA = rctoNum(row - 1, col);
            if (open.contains(indexA)) {
                sites.union(index, indexA);
                sitesNOBottom.union(index, indexA);
            }
        }

        if (!ooB(row + 1, col)) {
            int indexB = rctoNum(row + 1, col);
            if (open.contains(indexB)) {
                sites.union(index, indexB);
                sitesNOBottom.union(index, indexB);
            }
        }

        if (!ooB(row, col - 1)) {
            int indexL = rctoNum(row, col - 1);
            if (open.contains(indexL)) {
                sites.union(index, indexL);
                sitesNOBottom.union(index, indexL);
            }
        }

        if (!ooB(row, col + 1)) {
            int indexR = rctoNum(row, col + 1);
            if (open.contains(indexR)) {
                sites.union(index, indexR);
                sitesNOBottom.union(index, indexR);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (ooB(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int index = rctoNum(row, col);
        return open.contains(index);
    }


    public boolean isFull(int row, int col) {
        if (ooB(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int index = rctoNum(row, col);
        return sitesNOBottom.connected(N * N, index) && open.contains(index);
    }

    public int numberOfOpenSites() {
        return open.size();
    }

    public boolean percolates() {
        return sites.connected(N * N, N * N + 1);
    }


    private int rctoNum(int row, int col) {
        return N * row  + col;
    }

    private boolean ooB(int row, int col) {
        if (row >= N || row < 0) {
            return true;
        }
        if (col >= N || col < 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
