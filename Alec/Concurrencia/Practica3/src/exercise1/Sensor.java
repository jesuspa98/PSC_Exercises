package exercise1;

import java.util.Random;

public class Sensor extends Thread{

    private Random r = new Random();
    private int id;
    private Measurements m;
    public Sensor(int id, Measurements m){
        this.id = id;
        this.m = m;
    }


    public void run(){
        while(true){
            try {
                m.comenzarMedicion(id);
                Thread.sleep(r.nextInt(100));
                m.nuevaMedicion(id, r.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
