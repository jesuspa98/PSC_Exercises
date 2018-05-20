package philosopherproblem;

import java.util.concurrent.Semaphore;

public class Fork {
    private Semaphore sem = new Semaphore(1);

    public void use() throws InterruptedException {
        sem.acquire();
    }

    public void giveBack(){
        sem.release();
    }
}
