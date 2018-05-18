package exercise1;

import java.util.Random;

public class Worker extends Thread{
  private Random r = new Random();

  private Measurements m;
  public Worker(Measurements m){
    this.m = m;
  }
  
  
  public void run(){
    while(true){
      try {
        m.leerMediciones();
        Thread.sleep(r.nextInt(100));
        m.finTareas(); 
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

}
