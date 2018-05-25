package databaseproblem;

import java.util.Random;

public class Reader extends Thread {
    private int id;
    private DBController controller;
    private static Random r = new Random();

    public Reader(int id, DBController db){
        this.controller = db;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++){
                controller.beginReading(id);
                System.out.println("Reader with id " + id + " begins to read...");
                Thread.sleep(r.nextInt(500));
                System.out.println("Reader with id " + id + " finished reading...");
                controller.stopReading(id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
