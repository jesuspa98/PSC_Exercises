package exercise1;

public class Consumer extends Thread{
    private Buffer buffer;
    private int times;

    public Consumer(Buffer buffer, int times) {
        this.buffer = buffer;
        this.times = times;
    }

    @Override
    public void run() {
        int value;
        for (int i = 0; i < times; i++){
            value = buffer.get();
            System.out.println("I got the " + value + " value from the buffer in the " + i + " position");
        }
    }
}
