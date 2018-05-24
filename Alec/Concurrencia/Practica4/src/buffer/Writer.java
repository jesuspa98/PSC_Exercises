package buffer;

import java.util.Random;

public class Writer extends Thread {
    private int id;
    private Buffer buffer;
    private Random r = new Random();

    public Writer(Buffer buffer, int id){
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true){
            try {
                int value = 0;
                Thread.sleep(500);
                value = r.nextInt(50);
                System.out.println("Writer " + id + " writes to buffer the value " + value);
                buffer.put(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
