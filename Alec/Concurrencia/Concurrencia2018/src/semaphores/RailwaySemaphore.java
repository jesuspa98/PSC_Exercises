package semaphores;

import java.util.concurrent.Semaphore;

class RailwaySemaphore implements Railway {
    private Semaphore[] trackTaken = {
            new Semaphore(1),
            new Semaphore(1)
    };


    public RailwaySemaphore() {

    }

    @Override
    public void takeTrack(int trackNumber, String id) throws InterruptedException {
        trackTaken[trackNumber].acquire();
        System.out.println("El tren " + id + "está pasando por la pista " + trackNumber);
    }

    @Override
    public void releaseTrack(int trackNumber, String id) {
        trackTaken[trackNumber].release();
        System.out.println("El tren " + id + "ha abandonado la pista " + trackNumber);
    }
}
