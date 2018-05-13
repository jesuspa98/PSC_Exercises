package exercise1;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(4);
        final int veces = 100;
        Producer p = new Producer(buffer, veces);
        Consumer c = new Consumer(buffer, veces);
        p.start();
        c.start();
        try {
            p.join();
            c.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
