package exercise3;

import java.util.concurrent.Semaphore;

public class Airport {
  private Semaphore pista = new Semaphore(1, true);
  public void pedirPista() throws InterruptedException {
    pista.acquire();
  }
  public void devolverPista() {
    pista.release();
  }
}
