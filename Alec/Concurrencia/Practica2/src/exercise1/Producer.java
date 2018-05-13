package exercise1;

public class Producer extends Thread {
    private Buffer buffer;
    private int times;

    public Producer(Buffer buffer, int times) {
        this.buffer = buffer;
        this.times = times;
    }

    @Override
    public void run() {
        for(int i = 0; i < times; i++){
            buffer.set(i);
            System.out.println("I set the value " + i + " into the buffer!");
        }
    }
}
