package exercise4;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        NarrowBridge p = new NarrowBridge();
        Random rnd = new Random();
        Car[] cs = new Car[10];
        for (int i = 0; i < 10; i++) {
            cs[i] = new Car(p, rnd.nextInt(2), 5, i);
        }
        for (int i = 0; i < 10; i++) {
            cs[i].start();
        }
        for (int i = 0; i < 10; i++) {
            try {
                cs[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}