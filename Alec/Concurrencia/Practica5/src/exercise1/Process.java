package exercise1;

public class Process extends Thread {
    private int type, id;
    private Room room;

    public Process(int type, int id, Room room) {
        this.type = type;
        this.id = id;
        this.room = room;
    }

    @Override
    public void run() {
        while(true){
            try {
                room.getIn(id, type);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
