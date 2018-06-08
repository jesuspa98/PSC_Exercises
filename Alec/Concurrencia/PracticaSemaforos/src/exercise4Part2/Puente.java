package exercise4Part2;

import java.util.concurrent.Semaphore;

public class Puente {
    private final static int EMPTY_BRIDGE = -1;
    private final static int MAX_PASS_LIMIT = 3;
    private int[] carsWaitingInEachSide = {0, 0};
    private int currentDirection = EMPTY_BRIDGE;
    private int carsThatPassed = 0;
    private int onBridge = 0;
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore[] wait = {
            new Semaphore(0, true),
            new Semaphore(1, true)
    };
    
    public void getIn(int sentido, int carNumber) throws InterruptedException {
        boolean carWillWait = false;
        int otherSide = (sentido + 1) % 2;
        mutex.acquire();
        if(currentDirection != sentido || (currentDirection == sentido && carsWaitingInEachSide[otherSide] > 0
            && carsThatPassed >= MAX_PASS_LIMIT)){
            carWillWait = true;
            carsWaitingInEachSide[sentido]++;
        } else {
            currentDirection = sentido;
            onBridge++;
            carsThatPassed++;
        }
        mutex.release();
        if (carWillWait){
            wait[sentido].acquire();
            System.out.println("El coche " + carNumber + " se queda esperando en el sentido " + sentido);
        }
        System.out.println("El coche " + carNumber + " consigue entrar en el sentido " + sentido);
    }
    
    public void getOut(int sentido, int carNumber) throws InterruptedException {
        mutex.acquire();
        int otherSide = (sentido + 1) % 2;
        System.out.println("El coche " + carNumber + " salir en el sentido " + sentido);
        onBridge--;
        if (onBridge == 0){
            if (carsWaitingInEachSide[otherSide] > 0 && carsThatPassed >= MAX_PASS_LIMIT){
                currentDirection = otherSide;
                int carsThatCanPass = carsWaitingInEachSide[otherSide];
                onBridge = carsThatCanPass;
                carsThatPassed = carsThatCanPass;
                carsWaitingInEachSide[otherSide] = 0;
                wait[otherSide].release(carsThatCanPass);
            } else if (carsWaitingInEachSide[otherSide] == 0){
                currentDirection = EMPTY_BRIDGE;
            }
        }
        carsWaitingInEachSide[sentido]--;
        mutex.release();
    }
}
