package exercise1;

public abstract class Sensor extends Thread{
    protected long period;
    protected long next;

    public Sensor(long period) {
        this.period = period;
        next = System.currentTimeMillis();
    }

    private long waitTime() throws InterruptedException {
        long current = System.currentTimeMillis();
        while (current < next) {
            Thread.sleep(next - current);
            current = System.currentTimeMillis();
        }
        return current;
    }

    @Override
    public void run() {
        try{
            while(true){
                long current = waitTime();
                task();
                next += period;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected abstract void task();
}
