import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Montacargas {
    //Pequeño tipo == 0
    //Grande tipo == 1
    private final int SMALL = 0;
    private final int BIG = 1;
    private int[] dentro = {0, 0};
    private String[] nom = {"pequeño", "grande"};
    private Lock lock = new ReentrantLock();
    private Condition[] espera = {lock.newCondition(), lock.newCondition()};

    public void entrar(int id, int tipo) throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Coche " + id + " de tamaño " + nom[tipo] + " quiere entrar");
            while (!puedeEntrar(tipo)) {
                System.out.println("Coche " + id + " de tamaño " + nom[tipo] + " se espera");
                espera[tipo].await();
            }
            dentro[tipo]++;
            System.out.println("Coche " + id + " de tamaño " + nom[tipo] + " esta dentro");

        } finally {
            lock.unlock();
        }
    }

    private boolean puedeEntrar(int tipo) {
        if (tipo == SMALL) {
            return dentro[SMALL] <= 3 && dentro[BIG] == 0 ||
                    dentro[SMALL] <= 1 && dentro[BIG] <= 1;
        } else {
            return dentro[SMALL] <= 2 && dentro[BIG] == 0;
        }
    }

    public void salir(int id, int tipo) {
        lock.lock();
        try {
            dentro[tipo]--;
            despertar(tipo);
            System.out.println("Coche " + id + " de tamaño " + nom[tipo] + " sale");
        } finally {
            lock.unlock();
        }
    }

    private void despertar(int tipo) {
        if (tipo == BIG) {
            espera[SMALL].signal();
            espera[SMALL].signal();
            espera[BIG].signal();
        } else {
            espera[SMALL].signal();
            if(puedeEntrar(BIG)) {
                espera[BIG].signal();
            }
        }
    }
}
