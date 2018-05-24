package exercise4;

import java.util.concurrent.Semaphore;

public class NarrowBridge {
    private static final int PUENTE_VACIO = -1;
    private int dir_actual = PUENTE_VACIO;
    private int cuenta[] = { 0, 0 };
    private Semaphore mutex = new Semaphore(1);
    private Semaphore[] espera = { new Semaphore(0, true),
            new Semaphore(0, true) };


    public void getIn(int dir, int id) throws InterruptedException {
        boolean bloquear;
        mutex.acquire();
        cuenta[dir]++;
        System.out.println("El coche " + id + " quiere entrar en el sentido " + dir);
        if ((dir_actual == PUENTE_VACIO) || (dir_actual == dir)) {
            dir_actual = dir;
            bloquear = false;
        } else {
            bloquear = true;
        }
        mutex.release();
        if (bloquear) {
            System.out.println("Coche "+id+" espero dir "+dir);
            espera[dir].acquire();
        }
        System.out.println("El coche " + id + " consigue entrar en el sentido " + dir);
    }

    public void getOut(int dir, int id) throws InterruptedException {
        int dir_compl = (dir+1)%2;
        mutex.acquire();
        cuenta[dir]--;
        System.out.println("El coche " + id + " salir en el sentido " + dir);
        if (cuenta[dir] == 0) {
            if (cuenta[dir_compl] == 0) {
                dir_actual = PUENTE_VACIO;
            } else {
                dir_actual = dir_compl;
                espera[dir_compl].release(cuenta[dir_compl]);
            }
        }
        mutex.release();
    }
}
