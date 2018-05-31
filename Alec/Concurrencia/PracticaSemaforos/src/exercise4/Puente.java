package exercise4;

import java.util.concurrent.Semaphore;

public class Puente {
    private final static int EMPTY_BRIDGE = -1;
    private int[] carsWaitingInEachSide = {0, 0};
    private int currentDirection = EMPTY_BRIDGE;
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore[] wait = {
            new Semaphore(0, true),
            new Semaphore(1, true)
    };
    
    public void getIn(int sentido, int carNumber) throws InterruptedException {
        boolean carWillWait = false;
        mutex.acquire();
        carsWaitingInEachSide[sentido]++;
        System.out.println("El coche " + carNumber + " quiere entrar en el sentido " + sentido);
        if (currentDirection != sentido){
            carWillWait = true;
        } else {
            currentDirection = sentido;
        }
        mutex.release();
        if (carWillWait){
            wait[sentido].acquire();
            System.out.println("El coche " + carNumber + " se queda esperando en el sentido " + sentido);
        }
        System.out.println("El coche " + carNumber + " consigue entrar en el sentido " + sentido);
    }
    
    public void getOut(int sentido, int carNumber) throws InterruptedException {
        int otherSide = (sentido + 1) % 2;
        mutex.acquire();
        carsWaitingInEachSide[sentido]--;
        System.out.println("El coche " + carNumber + " salir en el sentido " + sentido);
        if (carsWaitingInEachSide[sentido] == 0){
            if (carsWaitingInEachSide[otherSide] == 0){
                currentDirection = EMPTY_BRIDGE;
            } else {
                currentDirection = otherSide;
                wait[otherSide].release(carsWaitingInEachSide[otherSide]);
            }
        }
        mutex.release();
        
    }
}
