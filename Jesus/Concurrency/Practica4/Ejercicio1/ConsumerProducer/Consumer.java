package ConsumerProducer;

public class Consumer extends Thread{
    private Buffer buffer;
    private int times;

    public Consumer(Buffer buffer, int times){
        this.buffer = buffer;
        this.times = times;
    }

    public void run(){
        for(int i = 0; i < times; i++){
            buffer.extract();
            System.out.println("I consume: " + i + " from the buffer.");
        }
    }
}
