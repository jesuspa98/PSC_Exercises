package exercise2_3;

public class Main {
    public static void main(String[] args) {
        SharedVariable sv = new SharedVariable();
        Reader reader = new Reader(sv);
        Writer writer = new Writer(sv);
        reader.start();
        writer.start();
        try {
            reader.join();
            writer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
