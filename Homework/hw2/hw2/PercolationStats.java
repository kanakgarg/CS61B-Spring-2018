package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {
    private double[] siteVal;
    private int T;
    private int N;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        siteVal = new double[T];
        this.T = T;
        this.N = N;

        for (int i = 0; i < T; i++) {
            Percolation perc = pf.make(N);
            while (!perc.percolates()) {
                int x = StdRandom.uniform(0, N);
                int y = StdRandom.uniform(0, N);
                if (!perc.isOpen(x, y)) {
                    perc.open(x, y);
                }
            }
            siteVal[i] = (double) perc.numberOfOpenSites() / (N * N);
        }
    }


    public double mean() {
        return StdStats.mean(siteVal);
    }
    public double stddev() {
        return StdStats.stddev(siteVal);
    }
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

}
