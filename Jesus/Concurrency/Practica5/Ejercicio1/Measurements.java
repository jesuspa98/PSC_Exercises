import java.util.concurrent.Semaphore;

public class Measurements {
    public static final int NUM_SENSORES = 3;
    private int[] values = new int[NUM_SENSORES];
    Semaphore workingSemaphore;
    Semaphore[] measuringSemaphore;

    public Measurements(){
        //Give initial values to a semaphore
        measuringSemaphore = new Semaphore[NUM_SENSORES];
        workingSemaphore = new Semaphore(0, true);
        initializeValues();
        for(int i = 0; i < measuringSemaphore.length; i++){
            measuringSemaphore[i] = new Semaphore(1, true);
        }
    }

    private void initializeValues(){
        for(int i = 0; i < values.length; i++){
            values[i] = -1;
        }
    }

    public void startMeasurement(int id) throws InterruptedException {
        measuringSemaphore[id].acquire();
    }

    public void newMeasure(int id, int value) {
        values[id] = value;
        System.out.println("Sensor " + id + " leave his measure: " + value);
        workingSemaphore.release();
    }

    public void readMeasurements() throws InterruptedException {
        for(int i = 0; i < measuringSemaphore.length; i++){
            workingSemaphore.acquire();
        }
        System.out.println("Worker has the measures: ");
        for(int i = 0; i < measuringSemaphore.length; i++){
            System.out.println("    " + i  + ": " + values[i]);
        }
    }

    public void endTasks() {
        System.out.println("Worker finished his tasks");
        for (int i = 0; i < measuringSemaphore.length; i++){
            measuringSemaphore[i].release();
        }
    }
}
