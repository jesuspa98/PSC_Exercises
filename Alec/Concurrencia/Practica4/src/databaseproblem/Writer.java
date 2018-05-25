package databaseproblem;

import java.util.Random;

public class Writer extends Thread{
    private int id;
    private DBController controller;
    private static Random r = new Random();

    public Writer(int id, DBController controller){
        this.id = id;
        this.controller = controller;
    }

    @Override
    public void run() {
        try{
            for (int i = 0; i < 10; i++){
                controller.beginWriting(id);
                System.out.println("Writer with id " + id + " begins to write...");
                Thread.sleep(r.nextInt(500));
                System.out.println("Writer with id " + id + " finished writing...");
                controller.stopWriting(id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
