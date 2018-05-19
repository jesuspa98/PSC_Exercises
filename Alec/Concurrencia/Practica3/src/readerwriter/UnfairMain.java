package readerwriter;

public class UnfairMain {
    public static void main(String[] args) {
        DataBaseUnfair dbu = new DataBaseUnfair();
        Reader r1 = new Reader(1, dbu);
        Reader r2 = new Reader(2, dbu);
        Reader r3 = new Reader(3, dbu);
        Writer w1 = new Writer(1, dbu);
        Writer w2 = new Writer(2, dbu);
        Writer w3 = new Writer(3, dbu);
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
