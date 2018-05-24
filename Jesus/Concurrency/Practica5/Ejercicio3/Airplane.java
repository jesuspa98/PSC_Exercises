import java.util.Random;

public class Airplane extends Thread {
    private Random rnd = new Random();
    private int id;
    private Control[] control;

    public Airplane(int id, Control[] c) {
        this.id = id;
        this.control = c;
    }

    public void run() {
        try {
            while (true) {
                fly();
                toLand();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void fly() throws InterruptedException {
        Thread.sleep(rnd.nextInt(100));
    }

    public void toLand() throws InterruptedException {
        int c = rnd.nextInt(control.length);
        control[c].requestPermission();
        System.out.println("Start the landing " + id);
        Thread.sleep(rnd.nextInt(50));
        System.out.println("End of the landing " + id);
        control[c].endOfLanding();
    }
}
