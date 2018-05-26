package exercise2;

import java.util.Random;

public class Consumer extends Thread {
    private Buffer buffer;
    private int id, times;
    private static Random random = new Random();

    public Consumer(Buffer buffer, int id, int times) { this.id = id; this.times = times; this.buffer = buffer; }

    @Override
    public void run() {
        try {
            for(int i = 0; i < times; i++) {
                int n = buffer.extract(id);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
