public class Buffer {
    private int thereIsAGap, production;
    private int[] thereIsData, cnt, buffer, cons;

    public Buffer(int max_elms, int n_cons) {
        cnt = new int[max_elms];
        buffer = new int[max_elms];

        for (int i = 0; i < max_elms; ++i) {
            cnt[i] = buffer[i] = 0;
        }

        production = 0;
        cons = new int[n_cons];
        thereIsAGap = max_elms;
        thereIsData = new int[n_cons];

        for (int i = 0; i < n_cons; ++i) {
            thereIsData[i] = 0;
        }
    }

    public synchronized void insert(int val) throws InterruptedException {
        while (thereIsAGap == 0) {
            wait();
        }

        --thereIsAGap;
        buffer[production] = val;
        cnt[production] = cons.length;
        production = (production + 1) % buffer.length;

        for (int i = 0; i < thereIsData.length; ++i) {
            ++thereIsData[i];
        }

        notifyAll();
    }

    public synchronized int extract(int cons_id) throws InterruptedException {
        int val = 0;

        while (thereIsData[cons_id] == 0) {
            wait();
        }

        --thereIsData[cons_id];
        val = buffer[cons[cons_id]];
        --cnt[cons[cons_id]];

        if (cnt[cons[cons_id]] == 0) {
            ++thereIsAGap;
            notifyAll();
        }

        cons[cons_id] = (cons[cons_id] + 1) % buffer.length;

        return val;
    }
}
