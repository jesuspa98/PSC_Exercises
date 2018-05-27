import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private int hayHueco, prod;
    private int[] hay_dato, cnt, buff, cons;
    private Lock lock = new ReentrantLock();
    private Condition esperaCons = lock.newCondition();
    private Condition esperaProd = lock.newCondition();

    public Buffer(int max_elms, int n_cons) {
        cnt = new int[max_elms];
        buff = new int[max_elms];
        for (int i = 0; i < max_elms; ++i) {
            cnt[i] = buff[i] = 0;
        }
        prod = 0;
        cons = new int[n_cons];
        hayHueco = max_elms;
        hay_dato = new int[n_cons];
        for (int i = 0; i < n_cons; ++i)
            hay_dato[i] = 0;
    }

    public void insert(int val) throws InterruptedException {
        lock.lock();
        try {
            while (hayHueco == 0)
                esperaProd.await();
            --hayHueco;
            buff[prod] = val;
            cnt[prod] = cons.length;
            prod = (prod + 1) % buff.length;
            for (int i = 0; i < hay_dato.length; ++i) {
                ++hay_dato[i];
            }
            esperaCons.signal();
        } finally {
            lock.unlock();
        }

    }

    public int extract(int cons_id) throws InterruptedException {
        lock.lock();
        try {
            int val = 0;
            while (hay_dato[cons_id] == 0)
                esperaCons.await();
            --hay_dato[cons_id];
            val = buff[cons[cons_id]];
            --cnt[cons[cons_id]];
            if (cnt[cons[cons_id]] == 0) {
                ++hayHueco;
                esperaProd.signalAll();
            }
            cons[cons_id] = (cons[cons_id] + 1) % buff.length;
            return val;
        } finally {
            lock.unlock();
        }


    }
}

