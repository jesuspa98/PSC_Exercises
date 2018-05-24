import java.util.Random;

public class Sensor extends Thread {
    private Random rnd = new Random();
    private int id;
    private Measurements measurements;

    public Sensor(int id, Measurements measurements){
        this.id = id;
        this.measurements = measurements;
    }

    public void run(){
        while(true){
            try {
                measurements.startMeasurement(id);
                Thread.sleep(rnd.nextInt(100));
                measurements.newMeasure(id, rnd.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
