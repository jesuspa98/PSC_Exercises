package exercise2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private int freeSpaces, produced;
    private int[] buffer, thereIsData, consumers, counter;
    private Lock lock = new ReentrantLock();
    private Condition waitConsumer = lock.newCondition();
    private Condition waitProducer = lock.newCondition();

    public Buffer(int longitud, int nconsumers) {
        freeSpaces = 1;
        buffer = new int[longitud];
        counter = new int[longitud];
        thereIsData = new int[nconsumers];
        consumers = new int[nconsumers];
    }

    public void extraer(int id) throws InterruptedException {
        lock.lock();
        try {
            while (thereIsData[id] == 0) {
                waitConsumer.await();
            }
            int indexConsumer = consumers[id];
            int dato = buffer[indexConsumer];
            counter[indexConsumer]++;
            if(counter[indexConsumer] == consumers.length) {
                waitProducer.signal();
            }
            thereIsData[id]--;
            consumers[id] = (consumers[id] + 1) % buffer.length;
            System.out.println("Consumer " + id + " reades the data " + dato);
        } finally {
            lock.unlock();
        }
    }

    public void poner(int id, int dato) throws InterruptedException {
        lock.lock();
        try {
            while(freeSpaces == 0) {
                waitProducer.await();
            }
            buffer[produced] = dato;
            produced = (produced + 1) % buffer.length;
            for(int i = 0; i < thereIsData.length; i++) {
                thereIsData[i]++;
            }
            System.out.println("The producer  " + id + " puts into the buffer " + dato);
            waitConsumer.signalAll();
        } finally {
            lock.unlock();
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
