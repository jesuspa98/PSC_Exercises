package exercise1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Room {
    private static final String[] PROCESS_NAME = {"A", "B"};
    private int[] processesIn = {0, 0};
    private int[] havePassed = {0, 0};
    private static final int[] NEEDED = {2, 1};
    // The problem says that the process A needs 2 b processes to visit before exiting the room.

    private Lock lock = new ReentrantLock();
    private Condition[] wait = {lock.newCondition(), lock.newCondition()};

    public void getIn(int id, int type) throws InterruptedException {
        lock.lock();
        int otherType = (type + 1) % 2;
        try {
            System.out.println("Process " + id + " with type " + PROCESS_NAME[type] + " entered the room");
            processesIn[type]++;
            havePassed[type]++;
            int iSaw = processesIn[otherType];
            int initial = iSaw;
            int initiallyHavePassed = havePassed[otherType];
            while (iSaw < NEEDED[type]){
                wait[type].await();
                System.out.println("Process " + id + " with type " + PROCESS_NAME[type] +
                        " is visited by " + PROCESS_NAME[otherType]);
                iSaw = initial + (havePassed[otherType] - initiallyHavePassed);
            }
            processesIn[type]--;
            System.out.println("Process " + id + " with type " + PROCESS_NAME[type] + " exits the room");
            wait[type].signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        final int NA = 10;
        final int NB = 10;
        Room h = new Room();
        Process[]a = new Process[NA];
        Process[]b = new Process[NB];
        for(int i = 0; i < NA; i++) {
            a[i] = new Process(0, i, h);
            a[i].start();
        }
        for(int i = 0; i < NA; i++) {
            b[i] = new Process(1, i, h);
            b[i].start();
        }
        try {
            for(int i = 0; i < NA; i++) {
                a[i].join();
            }
            for(int i = 0; i < NA; i++) {
                b[i].join();
            }
        }catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
