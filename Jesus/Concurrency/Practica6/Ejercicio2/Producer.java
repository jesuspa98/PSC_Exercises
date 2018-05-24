import java.util.Random;

public class Producer extends Thread {
    private Random rnd = new Random();
    private int id, number;
    private Buffer buffer;

    public Producer(int id, int number, Buffer buffer) {
        this.id = id;
        this.number = number;
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 0; i < number; ++i) {
                buffer.insert(id * number + i);
                Thread.sleep(rnd.nextInt(100));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
