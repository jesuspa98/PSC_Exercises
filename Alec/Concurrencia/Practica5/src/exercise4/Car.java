package exercise4;

public class Car extends Thread {
    private int id, type;
    private Elevator elevator;

    public Car(int id, int type, Elevator elevator) {
        this.id = id;
        this.type = type;
        this.elevator = elevator;
    }

    @Override
    public void run() {
        try {
            while (true) {
                elevator.getIn(id, type);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
