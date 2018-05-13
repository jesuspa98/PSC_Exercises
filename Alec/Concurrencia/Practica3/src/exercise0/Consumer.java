package exercise0;

public class Consumer extends Thread {
    private Buffer buffer;

    public Consumer(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++){
            try {
                System.out.println("consumed " + buffer.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
