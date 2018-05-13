package exercise0;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(4);
        Producer p = new Producer(buffer);
        Consumer c = new Consumer(buffer);
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
