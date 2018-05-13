package exercise0;

public class Producer extends Thread{
    private Buffer buffer;

    public Producer(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++){
            try {
                System.out.println("Produced " + i);
                buffer.set(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
