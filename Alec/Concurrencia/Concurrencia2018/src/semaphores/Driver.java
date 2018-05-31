package semaphores;

// Let's represent the directions as 0 and 1
public class Driver {
    public static void main(String[] args) throws InterruptedException {
        Railway r = new RailwayLock();// Here you create the Railway of the type you want to develop:
                    // RailwaySemaphore, RailwayLock or RailwayMonitor.
        // Here you have to create the trains
        // t1 starts at station One
        // t2 starts at station Two
        //
        Train t1 = new Train("Pacotren", r, 0);
        Train t2 = new Train("Toin", r, 1);
        // Here we start the trains as threads
        t1.start();
        t2.start();
        // Here you have to sleep for 10 seconds and interrupt
        //
        Thread.sleep(10000);
        t1.interrupt();
        t2.interrupt();
    }
}
