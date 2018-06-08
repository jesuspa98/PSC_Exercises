package semaphore;

import java.util.Random;

public class Jugador extends Thread {
    private int id;
    private static Random random = new Random();
    private Mesa mesa;

    public Jugador(Mesa mesa, int id) {
        this.mesa = mesa;
        this.id = id;
    }

    @Override
    public void run() {
        int numJuego = 0;
        while(true) {
            try {
                mesa.nuevaJugada(id, random.nextInt(3), numJuego);
                Thread.sleep(750);
            } catch(InterruptedException a) {
                a.getStackTrace();
            }
            numJuego++;
        }
    }
}
