package readerwriter;

public class Writer extends Thread{
    private int id;
    private DataBaseUnfair db;

    public Writer(int id, DataBaseUnfair db) {
        this.id = id;
        this.db = db;
    }

    @Override
    public void run() {
        while (true){
            try {
                db.writerEntering(id);
                // writer with id Writing in database.
                db.writerLeaving(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
