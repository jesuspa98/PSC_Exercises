package exercise4;

import java.util.Random;

public class Monitor {
    private int resources;
    private int requestTurn = 0;
    private int currentTurn = 0;

    public Monitor(int resources) {
        this.resources = resources;
    }

    public synchronized void queueResources(int id, int numberOfResources) throws InterruptedException {
        int turn = requestTurn;
        requestTurn++;
        while(currentTurn != turn || resources < numberOfResources) {
            System.out.println("Process " + id + " waits with turn " + turn +
                    ". Requested resources: " + numberOfResources + " currently there are " + resources);
            wait();
        }
        currentTurn++;
        resources -= numberOfResources;
        System.out.println("Process " + id + " gets " + numberOfResources + " resources. Still remaining " + resources);
        notifyAll();
    }

    public synchronized void freeResources(int id, int numberOfResources) {
        resources += numberOfResources;
        System.out.println("Process " + id + " frees " + numberOfResources + " resources. Now there are " + resources);
        notifyAll();
    }

    public static void main(String[] args) {
        Monitor c = new Monitor(6);
        Process[] p = new Process[10];
        Random rnd = new Random();
        for(int i = 0; i<p.length; ++i) {
            p[i] = new Process(c,i, 1 + rnd.nextInt(5));
            p[i].start();
        }
        try {
            for (Process aP : p) {
                aP.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
