package readerwriter;

import java.util.concurrent.Semaphore;

public class DataBaseUnfair {
    Semaphore writing = new Semaphore(1, true);
    volatile int numberOfReaders = 0;
    Semaphore mutex1 = new Semaphore(1, true);

    public void writerEntering(int id) throws InterruptedException {
        writing.acquire(); // if there is any writer writing in DB, then waits until the writer finishes.
        System.out.println("Writer with id " + id + " writing in DataBaseUnfair");
    }

    public void writerLeaving(int id) throws InterruptedException {
        writing.release(); // let other writers or readers enter the data base.
        System.out.println("Writer with id " + id + " leaving DataBaseUnfair");
    }

    public void readerEntering(int id) throws InterruptedException {
        mutex1.acquire();
        numberOfReaders++;
        System.out.println("Reader with id " + id + " reading database. Current readers in db " + numberOfReaders);
        if (numberOfReaders == 1) writing.acquire(); // makes the writers wait until all the readers have finished
        mutex1.release();
    }

    public void readerLeaving(int id) throws InterruptedException {
        mutex1.acquire();
        numberOfReaders--;
        System.out.println("Reader with id " + id + " leaving database. Current readers in db " + numberOfReaders);
        if (numberOfReaders == 0) writing.release(); // releases the writers
        mutex1.release();
    }
}
