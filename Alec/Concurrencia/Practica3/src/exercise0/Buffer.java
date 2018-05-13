package exercise0;

import java.util.concurrent.Semaphore;

public class Buffer {
    private int[] elems;
    private int producedIndex;
    private int consumedIndex;
    private Semaphore thereAreElements;
    private Semaphore thereAreSpace;
    private Semaphore mutex;

    public Buffer(int size){
        elems = new int[size];
        thereAreSpace = new Semaphore(size);
        thereAreElements = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    public void set(int i) throws InterruptedException {
        thereAreSpace.acquire();
        mutex.acquire();
        elems[producedIndex] = i;
        producedIndex = (producedIndex + 1) % elems.length;
        mutex.release();
        thereAreElements.release();
    }

    public int get() throws InterruptedException {
        int value = 0;
        thereAreElements.acquire();
        mutex.acquire();
        value = elems[consumedIndex];
        consumedIndex = (consumedIndex + 1) % elems.length;
        mutex.release();
        thereAreSpace.release();
        return value;
    }
}
