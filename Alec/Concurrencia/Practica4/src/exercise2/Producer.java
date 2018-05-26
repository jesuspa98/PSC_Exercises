package exercise2;

import java.util.Random;

public class Producer extends Thread{
    private Buffer buffer;
    private int id, times;
    private static Random random = new Random();

    public Producer(Buffer buffer, int id, int times) { this.id = id; this.times = times; this.buffer = buffer; }

    @Override
    public void run() {
        try {
            for(int i = 0; i < times; i++) {
                buffer.insert(id, random.nextInt(200));
                Thread.sleep(random.nextInt(500));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
