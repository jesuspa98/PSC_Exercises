import java.util.Random;

public class Productor extends Thread {
    Random rnd = new Random();
    int id, nv;
    private Buffer buffer;

    public Productor(int i, int n, Buffer b) {
        id = i;
        nv = n;
        buffer = b;
    }

    public void run() {
        try {
            for (int i = 0; i < nv; ++i) {
                buffer.insert(id * nv + i);
                Thread.sleep(rnd.nextInt(100));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}