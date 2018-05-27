import java.util.Random;

public class Agente extends Thread {
  private Random rnd = new Random();
  private Mesa mesa;
  public Agente(Mesa m) {
    mesa = m;
  }
  private void seleccionar_ingredientes(boolean[] ingr) {
    int i0 = rnd.nextInt(Mesa.NINGR);
    int i1 = (i0 + 1) % Mesa.NINGR;
    int i2 = (i0 + 2) % Mesa.NINGR;
    ingr[i0] = false;
    ingr[i1] = ingr[i2] = true;
  }
  public void run() {
    boolean[] ingr = { false, false, false };
    try {
      while (true) {
        seleccionar_ingredientes(ingr);
        mesa.poner_ingredientes(ingr);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
