package exercise2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    int[] buf;
    int prod = 0, numEspacioLibre;
    int[] consumidores;
    int[] contadores;
    int[] numEspaciosOcupados;
    private Lock l = new ReentrantLock();
    // UNA COLA POR CONDICION DE SINCRONIZACION
    private Condition esperaProductores = l.newCondition();
    private Condition esperaConsumidores = l.newCondition();


    public Buffer(int longitud, int ncons) {
        numEspacioLibre = 1;
        buf = new int[longitud];
        consumidores = new int[ncons];
        contadores = new int[longitud];
        numEspaciosOcupados = new int[ncons];
    }

    public void poner(int id, int dato) throws InterruptedException {
        l.lock();
        try {
            while(numEspacioLibre == 0) {
                esperaProductores.await();
            }
            buf[prod] = dato;
            contadores[prod] = 0;
            --numEspacioLibre;
            prod = (prod+1)%buf.length;
            for(int i = 0; i<numEspaciosOcupados.length; ++i) {
                ++numEspaciosOcupados[i];
            }

            System.out.println("El productor " + id + " produce el dato " + dato);

            esperaConsumidores.signalAll();
        } finally {
            l.unlock();
        }
    }

    public int extraer(int id) throws InterruptedException {
        l.lock();
        try {
            while(numEspaciosOcupados[id] == 0) {
                esperaConsumidores.await();
            }
            int indcons = consumidores[id];
            int dato = buf[indcons];
            contadores[indcons]++;
            if(contadores[indcons]==consumidores.length) {
                ++numEspacioLibre;
                esperaProductores.signal(); // despertar�a a la primera cola.
            }
            --numEspaciosOcupados[id];
            consumidores[id] = (consumidores[id]+1)%buf.length;

            System.out.println("El consumidor " + id + " lee el dato " + dato);

            return dato;
        } finally {
            l.unlock();
        }
    }

    public static void main(String[] args) {
        Buffer b = new Buffer(5,4);
        Producer[] p = new Producer[4];
        Consumer[] c = new Consumer[4];
        for(int i = 0; i<p.length; ++i) {
            p[i] = new Producer(b,i, 100);
            c[i] = new Consumer(b,i, 100);
            p[i].start();
            c[i].start();
        }
        try {
            for(int i = 0; i < p.length; i++){
                p[i].join();
                c[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
