import java.util.Random;

public class PressureSensor extends PeriodicT {
    private static final int THRESHOLD = 1000, SAMPLES_NUMBER = 650, PERIOD = 2000;
    private double value;
    private boolean valveOpen;
    private Random rnd = new Random();

    public PressureSensor() {
        super(PERIOD);
        value = 0;
        valveOpen = false;
    }

    public void showState() {
        System.out.println("Pressure: " + value + ", pressure valve: " + (valveOpen ? "OPEN" : "CLOSED"));
    }

    @Override
    protected void task() {
        int sum = 0;

        for (int i = 0; i < SAMPLES_NUMBER; i++) {
            sum += rnd.nextInt(THRESHOLD * 2);
        }

        synchronized (this) {
            value = (double) sum / (double) SAMPLES_NUMBER;

            if (value >= THRESHOLD) {
                if (!valveOpen) {
                    System.out.println("Opening pressure valve");
                }
                valveOpen = true;
            } else {
                if (valveOpen) {
                    System.out.println("Closing pressure valve");
                }
                valveOpen = false;
            }
        }
    }
}
