import java.util.Random;

public class Consumidor extends Thread {
    Random rnd = new Random();
    int id, numveces;
    private Buffer buffer;

    public Consumidor(int i, int n, Buffer b) {
        id = i;
        numveces = n;
        buffer = b;
    }

    public void run() {
        try {
            for (int i = 0; i < numveces; ++i) {
                int n = buffer.extract(id);
                System.out.println("Consumidor " + id + " consume " + n);
                Thread.sleep(rnd.nextInt(100));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
