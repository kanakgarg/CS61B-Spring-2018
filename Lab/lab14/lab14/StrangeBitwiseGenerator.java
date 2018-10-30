package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {

    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        this.period = period;
    }

    public double next() {
        state = state + 1;
        int otherState = state & (state >>> 3) % period;
        double newNum = otherState % period;
        return normalize(newNum);
    }

    public double normalize(double num) {
        double val = (2 * num) / period - 1;
        return val;
    }

}
