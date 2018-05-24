import java.util.Random;

public class Car extends Thread {
    private Bridge bridge;
    private int direction;
    private int lapsNumber;
    private int id;
    private Random rnd = new Random();

    public Car(int id, Bridge bridge, int lapsNumber) {
        this.direction = rnd.nextInt(2);
        this.id = id;
        this.bridge = bridge;
        this.lapsNumber = lapsNumber;
    }

    public void run() {
        for (int i = 0; i < lapsNumber; i++) {
            try {
                Thread.sleep(rnd.nextInt(50));
                bridge.getIn(direction, id);
                System.out.println("Car " + id + " get in the bridge in direction " + direction);
                Thread.sleep(rnd.nextInt(50));
                System.out.println("Car " + id + " get out the bridge in direction " + direction);
                bridge.getOut(direction);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
