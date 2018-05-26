package exercise2;

public class Buffer {
    private int freeSpace, produced;
    private int[] buffer, notFreeSpace, counters, consumers;

    public Buffer(int max_elements, int n_cons){
        this.counters = new int[max_elements];
        this.buffer = new int[max_elements];
        produced = 0;
        consumers = new int[n_cons];
        freeSpace = max_elements;
        notFreeSpace = new int[n_cons];
    }

    public synchronized int extract(int id) throws InterruptedException {
        while(notFreeSpace[id] == 0)
            wait();
        int indConsumers = consumers[id];
        int date = buffer[indConsumers];
        counters[indConsumers]++;
        if(counters[indConsumers] == consumers.length){
            freeSpace++;
            notify();
        }
        notFreeSpace[id]--;
        consumers[id] = (consumers[id] + 1) % buffer.length;
        System.out.println("El consumidor " + id + " lee el dato " + date);
        return date;
    }

    public synchronized void insert(int id, int date) throws InterruptedException {
        while(freeSpace == 0)
            wait();
        buffer[produced] = date;
        counters[produced] = 0;
        freeSpace--;
        produced = (produced + 1) % buffer.length;
        for(int i = 0; i < notFreeSpace.length; i++){
            notFreeSpace[i]++;
        }
        System.out.println("El productor " + id + " produce el dato " + date);
        notifyAll();
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
