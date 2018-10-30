package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private double a;

    public AcceleratingSawToothGenerator(int period, double a) {
        this.period = period;
        this.a = a;
    }

    public double next() {
        state = state + 1;
        double newNum = state % period;
        if (normalize(newNum) == - 1) {
            period = (int) Math.round(period * a);
        }
        return normalize(newNum);
    }

    public double normalize(double num) {
        double val = (2 * num) / period - 1;
        return val;
    }
}
