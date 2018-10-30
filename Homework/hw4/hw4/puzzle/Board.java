package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int[][] tiles;
    private int size;

    public Board(int[][] tiles) {
        size = tiles.length;
        this.tiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }

    }

    public int tileAt(int i, int j) {
        if (i > size() - 1 || j > size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return size;
    }

    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) != 0 & tileAt(i, j) != size() * i + j + 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public int manhattan() {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                int val = tileAt(i, j) - 1;
                if (val != -1) {
                    count += Math.abs(i - (val) / size()) + Math.abs(j - (val) % size());
                }
            }
        }
        return count;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (this == y) {
            return true;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        Board other = (Board) y;
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (this.tileAt(i, j) != other.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        int code = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                code = 10 * code +  tileAt(i, j);
            }
        }
        return code;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
