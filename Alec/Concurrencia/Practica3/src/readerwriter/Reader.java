package readerwriter;

public class Reader extends Thread {
    private int id;
    private DataBaseUnfair db;

    public Reader(int id, DataBaseUnfair db) {
        this.id = id;
        this.db = db;
    }

    @Override
    public void run() {
        while (true){
            try {
                db.readerEntering(id);
                db.readerLeaving(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
