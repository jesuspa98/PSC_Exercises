package buffer;

public class Buffer {
    private int[] buffer;
    private volatile int i = 0, j = 0;
    private volatile int elements;

    public Buffer(int elements){
        buffer = new int[elements];
        for(int i = 0; i < elements; i++){
            buffer[i] = -1;
        }
    }

    public synchronized int get() throws InterruptedException{
        while (elements == 0)
            // syncrhonizing condition -> consumer must not read if the buffer is empty
            wait();
        int aux = i;
        i = (i + 1) % buffer.length;
        elements--;
        notifyAll();
        return buffer[aux];
    }

    public synchronized void put(int data) throws InterruptedException {
        while (elements == buffer.length)
            // synchronizing condition -> producer must not write if the buffer is full
            wait();
        buffer[j] = data;
        j = (j + 1) % buffer.length;
        elements++;
        notifyAll(); // notifies all the waiting threads and one of them will be awaken.
        // You don't know what thread will be awaken, you only know that one of them
        // will be awaken
    }

    public static void main(String[] args) {
        Buffer buff = new Buffer(10);
        Reader[] readers = new Reader[10];
        Writer[] writers = new Writer[10];
        for (int i = 0; i < 10; i++){
            readers[i] = new Reader(buff, i);
        }
        for (int i = 0; i < 10; i++){
            writers[i] = new Writer(buff, i);
        }
        for (int i = 0; i < 10; i++){
            writers[i].start();
        }
        for (int i = 0; i < 10; i++){
            readers[i].start();
        }
        try {
            for (int i = 0; i < 10; i++) {
                writers[i].join();
            }
            for (int i = 0; i < 10; i++) {
                readers[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
