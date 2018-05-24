import java.util.Random;

public class Consumer extends Thread {
    private Random rnd = new Random();
    private int id, times;
    private Buffer buffer;

    public Consumer(int id, int times, Buffer buffer) {
        this.id = id;
        this.times = times;
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 0; i < times; ++i) {
                int n = buffer.extract(id);
                System.out.println("Consumer " + id + " consume " + n);
                Thread.sleep(rnd.nextInt(100));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
