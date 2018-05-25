package databaseproblem;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DBController {
    private int numberOfReaders = 0;
    private int numberOfWriters = 0;
    private boolean writing = false;
    private Lock lock = new ReentrantLock(true);
    private Condition okReading = lock.newCondition();
    private Condition okWriting = lock.newCondition();

    public void stopReading(int id) {
        lock.lock();
        try {
            numberOfReaders--;
            if (numberOfReaders == 0) okWriting.signal();
        } finally {
            lock.unlock();
        }
    }

    public void beginReading(int id) throws InterruptedException {
        lock.lock();
        try {
            while (writing || numberOfWriters > 0)
                okReading.await();
            numberOfReaders++;
        } finally {
            lock.unlock();
        }
    }

    public void stopWriting(int id) {
        lock.lock();
        try {
            numberOfWriters--;
            writing = false;
            if (numberOfWriters > 0) okWriting.signal();
            else okReading.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void beginWriting(int id) throws InterruptedException {
        lock.lock();
        try {
            numberOfWriters++;
            while (writing || numberOfReaders > 0)
                okWriting.await();
            writing = true;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        DBController dbController = new DBController();
        Writer[] writers = new Writer[5];
        Reader[] readers = new Reader[5];
        for (int i = 0; i < 5; i++){
            writers[i] = new Writer(i, dbController);
        }
        for (int i = 0; i < 5; i++){
            readers[i] = new Reader(i, dbController);
        }
        for (int i = 0; i < 5; i++){
            writers[i].start();
        }for (int i = 0; i < 5; i++){
            readers[i].start();
        }
        try {

            for (int i = 0; i < 5; i++){
                writers[i].join();
            }for (int i = 0; i < 5; i++){
                readers[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
