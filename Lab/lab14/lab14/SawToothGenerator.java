package lab14;
import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private double period;
    private int state;

    public SawToothGenerator(double period) {
        state = 0;
        this.period = period;
    }

    private double normalize(double state) {
        double val = (2 * state) / period - 1;
        return val;
    }

    @Override
    public double next() {
        state = state + 1;
        return normalize(state % period);
    }
}