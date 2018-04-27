package ConsumerProducer;

public class Producer extends Thread{
    private Buffer buffer;
    private int times;

    public Producer(Buffer buffer, int times){
        this.buffer = buffer;
        this.times = times;
    }

    public void run(){
        for(int i = 0; i < times; i++){
            buffer.place(i);
            System.out.println("I produced: " + i + " in the buffer.");
        }
    }
}
