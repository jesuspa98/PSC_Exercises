import java.util.Random;

public class Task extends Thread {
    private Random rnd = new Random();
    private int id, mxrec, times;
    private Control control;

    public Task(int id, int mxrec, Control control, int times) {
        this.id = id;
        this.mxrec = mxrec;
        this.control = control;
        this.times = times;
    }

    public void run() {
        try {
            for (int i = 0; i < times; ++i) {
                int number = 1 + rnd.nextInt(mxrec);
                control.takeResources(id, number);
                Thread.sleep(rnd.nextInt(100));
                control.resourcesFree(id, number);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
