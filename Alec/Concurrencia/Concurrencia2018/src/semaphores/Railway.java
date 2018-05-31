package semaphores;

// Tracks are numbered as 1 and 2
public interface Railway {
    void takeTrack(int trackNumber, String id) throws InterruptedException;
    void releaseTrack(int trackNumber, String id);
}
