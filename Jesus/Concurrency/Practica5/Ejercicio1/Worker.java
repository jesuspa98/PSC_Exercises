import java.util.Random;

public class Worker extends Thread{
    private Random rnd = new Random();
    private Measurements measurements;

    public Worker(Measurements measurements) {
        this.measurements = measurements;
    }

    public void run(){
        while (true){
            try {
                measurements.readMeasurements();
                Thread.sleep(rnd.nextInt(100));
                measurements.endTasks();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
