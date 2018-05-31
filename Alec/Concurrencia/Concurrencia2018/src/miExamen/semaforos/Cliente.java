package miExamen.semaforos;

import java.util.Random;

public class Cliente extends Thread{
  private static Random r = new Random();
  private int id;
  private Bar b;

  public  Cliente(int id,Bar b){
    this.id = id;
    this.b = b;
  }


  public void run(){
    while (true){
      try {
        //El programa debe funcionar correctamente con cualquier entrelazado
        Thread.sleep(r.nextInt(500));
        b.pidoMesa(id);
        Thread.sleep(r.nextInt(200));
        b.meVoy(id);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }
}
