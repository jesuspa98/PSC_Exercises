package readerwriter;

public class FairMain {
    public static void main(String[] args) {
        DataBaseUnfair db = new DataBase();
        Reader r1 = new Reader(1, db);
        Reader r2 = new Reader(2, db);
        Reader r3 = new Reader(3, db);
        Writer w1 = new Writer(1, db);
        Writer w2 = new Writer(2, db);
        Writer w3 = new Writer(3, db);
        r1.start();r2.start();r3.start();
        w1.start();w2.start();w3.start();
        try{
            r1.join();r2.join();r3.join();
            w1.join();w2.join();w3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
