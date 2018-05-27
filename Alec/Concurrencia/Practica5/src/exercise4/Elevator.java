package exercise4;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Elevator {
    private enum ENCars {SMALL, BIG};
    private static final ENCars[] CARS = ENCars.values();
    private volatile int smallCarsIn;
    private volatile int bigCarsIn;
    private boolean carsMovingOut = false;

    private Lock lock = new ReentrantLock();
    private Condition[] wait = { lock.newCondition(), lock.newCondition() };
    private Condition elevatorMoveWait = lock.newCondition();
    private Condition carsGettingOut = lock.newCondition();

    public void getOut(int id, int type) throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Car " + CARS[type] + " " + id + " waiting to get out from the elevator...");
            carsGettingOut.await();
            if (type == 0) {
                smallCarsIn--;
            } else {
                bigCarsIn--;
            }
            if (bigCarsIn == 0 && smallCarsIn == 0){
                carsMovingOut = false;
                wait[0].signalAll();
                wait[1].signalAll();
            }
            System.out.println("Car get out from the elevator.");
        } finally {
            lock.unlock();
        }

    }

    public void getIn(int id, int type) throws InterruptedException {
        lock.lock();
        try {
            if (type == 1) { // BIG CAR
                while (bigCarsIn == 1 || smallCarsIn > 2 || carsMovingOut) {
                    System.out.println("A " + CARS[type] + " Car " + id + " waiting to enter the elevator...");
                    wait[type].await();
                }
                bigCarsIn++;
            } else {
                while (((bigCarsIn == 1 && smallCarsIn == 2) || (smallCarsIn == 4 && bigCarsIn == 0)) || carsMovingOut) {
                    System.out.println("Car " + id + " waiting to enter the elevator...");
                    wait[type].await();
                }
                smallCarsIn++;
            }
            System.out.println("A " + CARS[type] + " car with id " + id + " has entered the elevator... Big ones: " + bigCarsIn + " small ones: " + smallCarsIn);
            if (2*bigCarsIn + smallCarsIn == 4) {
                System.out.println("Señalizado!");
                elevatorMoveWait.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void elevator() throws InterruptedException {
        lock.lock();
        try {
            while(2*bigCarsIn + smallCarsIn != 4 || carsMovingOut) {
                elevatorMoveWait.await();
            }
            System.out.println(" !! Elevator is moving...");
            Thread.sleep(1000);
            System.out.println(" !! Elevator is letting the cars go out...");
            carsMovingOut = true;
            carsGettingOut.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Elevator el = new Elevator();
        ElevatorThread elT = new ElevatorThread(el);
        Car[] cars = new Car[20];
        Random random = new Random();
        for(int i = 0; i < 20; i++) {
            cars[i] = new Car(i, random.nextInt(2), el);
        }
        for(int i = 0; i < 20; i++) {
            cars[i].start();
        }
        elT.start();
        try {
            for(int i = 0; i < 20; i++) {
                cars[i].join();
            }
            elT.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
