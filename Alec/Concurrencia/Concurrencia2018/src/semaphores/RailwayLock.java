package semaphores;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RailwayLock implements Railway {
    private Lock a = new ReentrantLock();
    private boolean[] thereIsTrain = { false, false };
    private Condition estaOcupadaLaVia = a.newCondition();

    public RailwayLock() {
    }

    @Override
    public void takeTrack(int trackNumber, String id) throws InterruptedException {
        a.lock();
        try {
            while(thereIsTrain[trackNumber]) {
                estaOcupadaLaVia.await();
            }
            thereIsTrain[trackNumber] = true;
            System.out.println("El tren " + id + "está pasando por la pista " + trackNumber);
        } finally {
            a.unlock();
        }
    }
    @Override
    public void releaseTrack(int trackNumber, String id) {
        a.lock();
        try {
            thereIsTrain[trackNumber] = false;
            System.out.println("El tren " + id + "ha abandonado la pista " + trackNumber);
        } finally {
            a.unlock();
        }
    }
}
