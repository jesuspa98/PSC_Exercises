package exercise3;

import java.util.Random;

public class Airplane extends Thread {
  private Random rnd = new Random();
  private int id;
  private Control[] control;

  public Airplane(int id, Control[] c) {
      this.id = id;
    this.control = c;
  }

  public void run() {
    try {
      while (true) {
        vuelo();
        aterrizar();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void vuelo() throws InterruptedException {
    Thread.sleep(rnd.nextInt(1000));
  }

  public void aterrizar() throws InterruptedException {
    int c = rnd.nextInt(control.length);
    control[c].solicitar_permiso();
    System.out.println("Comienzo del aterrizaje " + id + " en el control " + c);
    Thread.sleep(rnd.nextInt(1000));
    System.out.println("Fin del aterrizaje " + id);
    control[c].fin_aterrizaje();
  }
}
