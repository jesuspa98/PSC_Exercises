import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WorkerGenerator extends SwingWorker<Void, Integer> {
    private int number;
    private Panel panel;
    private char aChar;
    private List<Integer> list = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    protected Condition condition = lock.newCondition();

    public WorkerGenerator(int number, Panel panel, char l) {
        this.number = number;
        this.panel = panel;
        this.aChar = l;
    }

    protected Void doInBackground() throws Exception {
        Random rnd = new Random();
        int num;

        for (int i = 0; i < number; i++) {
            num = rnd.nextInt(100) + 1;
            lock.lock();

            try {
                list.add(num);
                condition.signalAll();
            } finally {
                lock.unlock();
            }

            publish(num);
            Thread.sleep(50);
        }
        return null;
    }

    public int getVeces() {
        return number;
    }

    public Integer getElemento(int i) throws InterruptedException {
        lock.lock();

        try {
            while (list.size() <= i) {
                condition.await();
            }

            return list.get(i);
        } finally {
            lock.unlock();
        }

    }

    protected void process(List<Integer> num) {
        for (int number : num) {
            panel.addList(String.valueOf(number), aChar);
        }
    }
}