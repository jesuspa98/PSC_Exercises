package philosopherproblem;

import java.util.Random;

public class Philosopher extends Thread {
    private static Random r = new Random();
    private int id;
    private Fork left, right;
    private Chair chair;

    public Philosopher(int id, Fork left, Fork rightL, Chair cadira) {
        this.id = id;
        this.left = left;
        this.right = rightL;
        this.chair = cadira;
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("I'm the philosopher " + id + " and currently I'm thinking...");
                Thread.sleep(r.nextInt(500));
                chair.use();
                left.use();
                right.use();
                System.out.println("I'm the philosopher " + id + " and currently I'm eating...");
                Thread.sleep(r.nextInt(100));
                left.giveBack();
                right.giveBack();
                chair.giveBack();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //comer
        }
    }
}
