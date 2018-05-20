package exercise4;

import exercise4.NarrowBridge;
import java.util.Random;

public class Car extends Thread{
    NarrowBridge puente;
    int sentido;
    int numVueltas;
    Random rnd = new Random ();
    int id;

    public Car (NarrowBridge p, int sentido, int nVueltas, int identificador) {
        this.sentido = sentido;
        puente = p;
        numVueltas = nVueltas;
        id = identificador;
    }

    public void run () {
        for (int i = 0; i < numVueltas; i++) {
            try {
                Thread.sleep(rnd.nextInt(200));
                //Por qué entran i coches?
                puente.getIn(sentido, id);
                Thread.sleep(rnd.nextInt(100));
                puente.getOut(sentido, id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}