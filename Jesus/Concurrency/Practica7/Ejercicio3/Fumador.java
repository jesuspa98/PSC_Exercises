import java.util.Random;

public class Fumador extends Thread {
  private Random rnd = new Random();
  private Mesa mesa;
  private int id;
  public Fumador(Mesa m, int id) {
    this.mesa = m;
    this.id = id;
  }
  public void run() {
    try {
      while (true) {
        mesa.tomar_ingredientes(id);
        System.out.println("Fumador "+id+" fumando");
        Thread.sleep(rnd.nextInt(100));
        mesa.liberar(id);
      }      
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
