package semaphores;

import java.util.Random;

class Train extends Thread {
    private static final Random r = new Random();
    private String id;
    private Railway rail;
    private int track;

    public Train(String id, Railway rail, int track) {
        this.id = id;
        this.track = track;
        this.rail = rail;
    }

    @Override
    public void run() {
        try {
            while(true) {
                rail.takeTrack(track % 2, id); // from 0 to middle
                rail.releaseTrack(track % 2, id);
                Thread.sleep(r.nextInt(1000 - 500 + 1) + 500);
                track++;
                rail.takeTrack(track % 2, id); // from middle to 1
                rail.releaseTrack(track % 2, id);
                Thread.sleep(r.nextInt(1000 - 500 + 1) + 500);
                rail.takeTrack(track % 2, id); // from 1 to middle, same track as before
                rail.releaseTrack(track % 2, id);
                track++;
                Thread.sleep(r.nextInt(501) + 500);
                rail.takeTrack(track % 2, id);
                rail.releaseTrack(track % 2, id);
                /*
                Here is where you have to simulate that a
                train goes from station One to station Two
                and the other way around.
                */
            }
        } catch (Exception x) { x.printStackTrace();
        }
    }
}
