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

    private Lock lock = new ReentrantLock(true);
    private Condition[] wait = { lock.newCondition(), lock.newCondition() };
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
                System.out.println("A " + CARS[type] + " car with id " + id + " has entered the elevator... Big ones: " + bigCarsIn + " small ones: " + smallCarsIn);
                waitGettingOut();
                bigCarsIn--;
                System.out.println("!! A " + CARS[type] + " car with id " + id + " has left the elevator... Big ones: " + bigCarsIn + " small ones: " + smallCarsIn);
                System.out.println("Car has left...");
            } else {
                while (((bigCarsIn == 1 && smallCarsIn == 2) || (smallCarsIn == 4 && bigCarsIn == 0)) || carsMovingOut) {
                    System.out.println("Car " + id + " waiting to enter the elevator...");
                    wait[type].await();
                }
                smallCarsIn++;
                System.out.println("A " + CARS[type] + " car with id " + id + " has entered the elevator... Big ones: " + bigCarsIn + " small ones: " + smallCarsIn);
                waitGettingOut();
                smallCarsIn--;
                System.out.println("!! A " + CARS[type] + " car with id " + id + " has left the elevator... Big ones: " + bigCarsIn + " small ones: " + smallCarsIn);
            }
            carsGettingOut(type);
        } finally {
            lock.unlock();
        }
    }

    private void waitGettingOut() throws InterruptedException {
        if((bigCarsIn == 0 && smallCarsIn == 4) || (bigCarsIn == 1 && smallCarsIn == 2)) {
            Thread.sleep(1000);
            carsMovingOut = true;
            carsGettingOut.signalAll();
        } else {
            carsGettingOut.await();
        }
    }

    private void carsGettingOut(int type) throws InterruptedException {
        if(bigCarsIn == 0 && smallCarsIn == 0) {
            System.out.println();
            carsMovingOut = false;
            wait[0].signalAll();
            wait[1].signalAll();
        } else {
            wait[type].await();
        }
    }

    public static void main(String[] args) {
        Elevator el = new Elevator();
        Car[] cars = new Car[20];
        for(int i = 0; i < 20; i++) {
            cars[i] = new Car(i, i % 2, el);
        }
        for(int i = 0; i < 20; i++) {
            cars[i].start();
        }
        try {
            for(int i = 0; i < 20; i++) {
                cars[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
