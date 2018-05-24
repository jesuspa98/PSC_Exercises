import java.util.concurrent.Semaphore;

public class Bridge {
    final static int EMPTY_BRIDGE = -1;
    private int[] count = {0, 0};
    private int actualDirection = EMPTY_BRIDGE;
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore[] waiter = {new Semaphore(0, true), new Semaphore(1, true)};


    public void getIn(int direction, int carNumber) throws InterruptedException {
        boolean block;
        mutex.acquire();
        ++count[direction];

        if ((actualDirection == EMPTY_BRIDGE) || (actualDirection == direction)) {
            actualDirection = direction;
            block = false;
        } else {
            block = true;
        }

        mutex.release();

        if (block) {
            System.out.println("Car " + carNumber + " wait in direction " + direction);
            waiter[direction].acquire();
        }
    }

    public void getOut(int direction) throws InterruptedException {
        int otherDirection = (direction + 1) % 2;
        mutex.acquire();
        --count[direction];

        if (count[direction] == 0) {
            if (count[otherDirection] == 0) {
                actualDirection = EMPTY_BRIDGE;
            } else {
                actualDirection = otherDirection;
                waiter[otherDirection].release(count[otherDirection]);
            }
        }
        mutex.release();
    }
}
