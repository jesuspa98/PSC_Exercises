import java.util.concurrent.Semaphore;

public class Control extends Thread {
    private Airport airport;
    private Semaphore airplaneWaiting = new Semaphore(0, true);
    private Semaphore controlWaiting = new Semaphore(0, true);
    private Semaphore airplaneLanding = new Semaphore(0, true);

    public Control(Airport airport) {
        this.airport = airport;
    }

    public void run() {
        try {
            while (true) {
                waitRequest();
                attendRequest();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitRequest() throws InterruptedException {
        controlWaiting.acquire();
    }

    public void attendRequest() throws InterruptedException {
        airport.askLandingTrack();
        airplaneWaiting.release();
        airplaneLanding.acquire();
        airport.giveBackLandingTrack();
    }

    public void requestPermission() throws InterruptedException {
        controlWaiting.release();
        airplaneWaiting.release();
    }

    public void endOfLanding() {
        airplaneLanding.release();
    }
}
