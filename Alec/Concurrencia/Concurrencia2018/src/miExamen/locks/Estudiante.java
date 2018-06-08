package miExamen.locks;

import java.util.Random;



public class Estudiante extends Thread {
  private static Random r = new Random();
  private int id;
  private Habitacion h;

  public  Estudiante(int id,Habitacion h){
    this.id = id;
    this.h = h;
  }


  public void run(){
    while (true){
      try {
        Thread.sleep(r.nextInt(500));
        h.entraEstudiante(id);
        Thread.sleep(r.nextInt(200));
        h.saleEstudiante(id);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
  }
}
