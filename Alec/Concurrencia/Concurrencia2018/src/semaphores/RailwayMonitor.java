package semaphores;

public class RailwayMonitor implements Railway {
    private boolean[] thereIsTrain = { false, false };

    @Override
    public synchronized void takeTrack(int trackNumber, String id) throws InterruptedException {
        while(thereIsTrain[trackNumber]) {
            wait();
        }
        System.out.println("El tren " + id + "está pasando por la pista " + trackNumber);
        thereIsTrain[trackNumber] = true;
    }

    @Override
    public synchronized void releaseTrack(int trackNumber, String id) {
        thereIsTrain[trackNumber] = false;
        System.out.println("El tren " + id + "ha abandonado la pista " + trackNumber);
        notifyAll();
    }
}
