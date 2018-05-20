package sleepybarber;

import java.util.concurrent.Semaphore;

/**
 * The barber only acquires the thread when there are no clientsWaiting.
 * The environment only awakes the barber when it arrives a new client on a empty buffer (clientsWaiting is more than 0)
 *
 */
public class Barbershop {
    private volatile int clientsWaiting = 0;
    private Semaphore sleep = new Semaphore(0, true);
    private Semaphore mutex = new Semaphore(1, true); // ensures that the barber and the barbershop
    // are updated in mutex (exclusion mutua).


    public void nextClient() throws InterruptedException {
        mutex.acquire(); // mutex.
        clientsWaiting--;
        System.out.println("Attending a new client... Clients in queue " + (clientsWaiting));
        // PREV WRONG SOLUTION; It could consume an empty buffer.
        // if (clientsWaiting == 0) sleep.acquire(); // if there are no more clients, barber begin to sleep.
        // This way, the consumer will block if tries to consume an empty buffer.
        if (clientsWaiting == -1){
            System.out.println("I'm sleepy..."); // sout ALWAYS in MUTEX !!!!!!!!
            mutex.release();
            sleep.acquire();
            mutex.acquire();
        }
        mutex.release(); // end of mutex
    }

    public void newClient() throws InterruptedException {
        mutex.acquire();
        clientsWaiting++; //When a new client arrives, the buffer is updated in mutex.
        System.out.println("A new client has arrived... Queue of clients " + clientsWaiting);
        if (clientsWaiting == 1) sleep.release(); // if it is the first client, awakes the barber.
        mutex.release(); // end of mutex
    }

    public void waitFirstClient() throws InterruptedException {
        System.out.println("I'm sleepy...");
        sleep.acquire(); // Barber is sleeping initially
    }
}
