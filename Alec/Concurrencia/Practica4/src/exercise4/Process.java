package exercise4;

import java.util.Random;

public class Process extends Thread {
    private int id, numberOfResources;
    private Monitor monitor;
    private static Random random = new Random();

    public Process(Monitor monitor, int id, int numberOfResources){
        this.id = id; this.numberOfResources = numberOfResources;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while(true){
            try {
                monitor.queueResources(id, numberOfResources);
                Thread.sleep(random.nextInt(500));
                monitor.freeResources(id, numberOfResources);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
