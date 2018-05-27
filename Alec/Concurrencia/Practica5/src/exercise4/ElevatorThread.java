package exercise4;

public class ElevatorThread extends Thread {
    private Elevator el;

    public ElevatorThread(Elevator el) {
        this.el = el;
    }

    @Override
    public void run() {
        while(true) {
            try {
                el.elevator();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
