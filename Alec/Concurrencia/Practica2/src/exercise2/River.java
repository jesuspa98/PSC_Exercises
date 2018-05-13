package exercise2;

public class River extends Thread {
    private Lake lake;
    private int id;

    public River(Lake lake, int id) {
        this.lake = lake;
        this.id = id;
    }

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++){
            lake.increaseLevel(id);
        }
    }
}
