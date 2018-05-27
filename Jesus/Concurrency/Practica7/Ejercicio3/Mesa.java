import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mesa {
    enum EnumIngr {TABACO, PAPEL, CERILLAS;}

    static final EnumIngr[] INGR = EnumIngr.values();
    public static final int NINGR = INGR.length;

    private boolean libre = true;
    private boolean[] enmesa = {false, false, false};

    private Lock lock = new ReentrantLock();
    private Condition agent = lock.newCondition();
    private Condition []fumador  = {lock.newCondition(), lock.newCondition(), lock.newCondition()};


    public void poner_ingredientes(boolean[] ingr) throws InterruptedException {
        lock.lock();
        try {
            while (!libre) agent.await();
            libre = false;
            System.out.print("Agente pone ingredientes: ");
            for (int i = 0; i < ingr.length; ++i) {
                enmesa[i] = ingr[i];
                if (ingr[i]) System.out.print(INGR[i] + " ");
            }
            System.out.println();
            fumador[1].signalAll();//TODO fix it
        } finally {
            lock.unlock();
        }
    }

    public void tomar_ingredientes(int id) throws InterruptedException {
        lock.lock();
        try {
            int i1 = (id + 1) % NINGR;
            int i2 = (id + 2) % NINGR;
            while (!(enmesa[i1] && enmesa[i2]))
                fumador[id].await();
            System.out.println("Fumador " + id + " (" + INGR[id] + ") toma los ingredientes");
            for (int i = 0; i < enmesa.length; ++i)
                enmesa[i] = false;
        } finally {
            agent.signalAll();
        }
    }

    public void liberar(int id) {
        lock.lock();
        try {
            System.out.println("Fumador " + id + " termina de fumar");
            libre = true;
            agent.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

