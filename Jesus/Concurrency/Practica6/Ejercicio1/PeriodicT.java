abstract class PeriodicT extends Thread {
    protected long period;
    protected long next;

    public PeriodicT(long period) {
        this.period = period;
        next = System.currentTimeMillis();
    }

    private long waitTimer() throws InterruptedException {
        long current = System.currentTimeMillis();
        while (current < next) {
            Thread.sleep(next - current);
            current = System.currentTimeMillis();
        }
        return current;
    }

    public void run() {
        try {
            while (true) {
                long current = waitTimer();
                task();
                next += period;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected abstract void task();
}
