import java.util.concurrent.Semaphore;

public class Airport {
    private Semaphore landingTrack = new Semaphore(1);

    public void askLandingTrack() throws InterruptedException {
        landingTrack.acquire();
    }

    public void giveBackLandingTrack() {
        landingTrack.release();
    }
}
