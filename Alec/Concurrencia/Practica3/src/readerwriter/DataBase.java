package readerwriter;

import java.util.concurrent.Semaphore;

public class DataBase extends DataBaseUnfair {
    private volatile int numberOfWriters = 0;
    private Semaphore mutexWriters = new Semaphore(1, true);
    private Semaphore mutex3 = new Semaphore(1, true);
    private Semaphore reading = new Semaphore(1, true);

    public void writerEntering(int id) throws InterruptedException {
        mutexWriters.acquire();
        numberOfWriters++;
        System.out.println("Writer with id " + id + " waiting for write into DB. Current readers in DB " + numberOfReaders
                + " Current writers in DB " + numberOfWriters);
        if (numberOfWriters == 1) reading.acquire();
        mutexWriters.release();
        super.writing.acquire();
        System.out.println("Writer with id " + id + " writing in database");
    }

    public void writerLeaving(int id) throws InterruptedException {
        mutexWriters.acquire();
        numberOfWriters--;
        System.out.println("Writer with id " + id + " leaving DB. Current readers in DB " + numberOfReaders
                + " Current writers in DB " + numberOfWriters);
        if (numberOfWriters == 0) reading.release();
        mutexWriters.release();
        super.writing.release();
    }

    public void readerEntering(int id) throws InterruptedException {
        mutex3.acquire();
        reading.acquire();
        mutex1.acquire();
        numberOfReaders++;
        System.out.println("Reader with id " + id + " in queue to read the DB. Current readers in DB " + numberOfReaders
                + " Current writers in DB " + numberOfWriters);
        if (numberOfReaders == 1) writing.acquire();
        System.out.println("Reader with id " + id + " start reading database");
        mutex1.release();
        reading.release();
        mutex3.release();
    }

    public void readerLeaving(int id) throws InterruptedException {
        mutex1.acquire();
        numberOfReaders--;
        System.out.println("Reader with id " + id + " leaving the DB. Current readers in DB " + numberOfReaders
                + " Current writers in DB " + numberOfWriters);
        if (numberOfReaders == 0) writing.release();
        mutex1.release();
    }
}
