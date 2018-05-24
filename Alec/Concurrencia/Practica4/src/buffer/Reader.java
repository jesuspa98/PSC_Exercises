package buffer;

public class Reader extends Thread {
    private int id;
    private Buffer buffer;

    public Reader(Buffer buffer, int id){
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(500);
                System.out.println("Reader " + id + " reads from buffer the value " + buffer.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
