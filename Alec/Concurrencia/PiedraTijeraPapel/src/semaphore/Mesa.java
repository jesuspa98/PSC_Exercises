package semaphore;

import java.util.concurrent.Semaphore;

public class Mesa {
    //0 - piedra, 1 - papel, 2 - tijera
    private int[] jugadas= new int[3];
    private int num;
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore espera = new Semaphore(0, true);
    private Semaphore siguiente = new Semaphore(1, true);
    private int ganador = -1;

    private void gana() {
        if ((jugadas[0]==2) && jugadas[1]==1 ) ganador= 1;
        else if ((jugadas[1]==2) && jugadas[2]==1 ) ganador= 2;
        else if ((jugadas[0]==1) && jugadas[2]==2 ) ganador= 0;
        else ganador = -1;
    }

    private String jugada(int n) {
        if (n==0) return "Piedra";
        if (n==1) return "Papel";
        if (n==2) return "Tijera";
        if (n==-1) return "Empate";
        return "Ni idea"; //aqui no debe de llegar nunca
    }
    private void inicializa(){
        for (int i = 0; i<3; i++) jugadas[i]=0;
    }

    public int nuevaJugada(int id, int juego, int numJuego) throws InterruptedException {
        int aux = -2;
        siguiente.acquire();
        mutex.acquire();
        num++;
        jugadas[juego]++;
        System.out.println(numJuego + ": Jugador " + id + " pone " + jugada(juego));
        if(num < 3) {
            mutex.release();
            siguiente.release();
            espera.acquire();
            mutex.acquire();
            aux = ganador;
        } else {
            gana();
            inicializa();
            aux = ganador;
        }
        num--;
        if(num > 0) {
            espera.release();
            mutex.release();
        } else {
            System.out.println(numJuego + ": Fin del juego: " + jugada(ganador));
            System.out.println("******************************");
            siguiente.release();
            mutex.release();
        }
        return aux;
    }

    public static void main(String[] args) {
        Mesa mesa = new Mesa();
        Jugador A = new Jugador(mesa, 0);
        Jugador B = new Jugador(mesa, 1);
        Jugador C = new Jugador(mesa, 2);
        A.start();
        B.start();
        C.start();
    }

}
