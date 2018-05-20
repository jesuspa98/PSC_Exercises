package philosopherproblem;

import java.util.concurrent.Semaphore;

public class Chair {
    private Semaphore chairs = new Semaphore(4, true);

    public void use() throws InterruptedException {
        chairs.acquire();
    }

    public void giveBack(){
        chairs.release();
    }
}
