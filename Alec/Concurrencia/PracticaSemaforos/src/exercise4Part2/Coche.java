package exercise4Part2;

import java.util.Random;

public class Coche extends Thread {
    private Puente puente;
    private int sentido;
    private int id;
    private int numVueltas;
    Random rnd = new Random();

    public Coche(Puente p, int sentido, int num, int id){
        this.sentido = sentido;
        puente = p;
        numVueltas = num;
        this.id = id;
    }

    public void run(){
        for (int i = 0; i < numVueltas; i++){
            try{
                Thread.sleep(rnd.nextInt(200));
                puente.getIn(sentido, i);
                Thread.sleep(rnd.nextInt(100));
                puente.getOut(sentido, i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
