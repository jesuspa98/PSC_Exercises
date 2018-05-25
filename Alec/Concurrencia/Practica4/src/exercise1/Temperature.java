package exercise1;

import java.util.Random;

public class Temperature extends Sensor {
    private static final int THRESHOLD = 100, SAMPLES_NUMBER = 1000, PERIOD = 1000;
    private double value;
    private boolean injectorOpen;
    private Random rnd = new Random();

    public Temperature(long period) {
        super(PERIOD);
        value = 0;
    }

    public synchronized void printState(){
        System.out.println("Temperature: " + value + ", Injection cool air: " +
                (injectorOpen ? "ACTIVATED" : "DESACTIVATED"));
    }

    @Override
    protected void task() {
        int sum = 0;
        for (int i = 0; i < SAMPLES_NUMBER; i++) {
            sum += rnd.nextInt(THRESHOLD * 2);
        }
        synchronized (this){
            value = (double) sum / (double) SAMPLES_NUMBER;

            if (value >= THRESHOLD) {
                if (!injectorOpen) {
                    System.out.println("Opening cool air injector");
                }
                injectorOpen = true;
            } else {
                if (injectorOpen) {
                    System.out.println("Closing cool air injector");
                }
                injectorOpen = false;
            }
        }

    }
}
