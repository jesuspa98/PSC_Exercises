package exercise4;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Puente p = new Puente();
        Random rnd = new Random();
        Coche[] cs = new Coche[10];
        for(int i = 0; i < 10; i++){
            cs[i] = new Coche(p, rnd.nextInt(2), 5 ,i);
        }
        for(int i = 0; i < 10; i++){
            cs[i].start();
        }
        for(int i = 0; i < 10; i++){
            try {
                cs[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
