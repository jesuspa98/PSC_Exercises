package exercise2;

import java.util.Random;

public class PackagerRobot extends Thread{
  private Random r = new Random();
  private AssemblyLine c;
  private int tipo;
  public PackagerRobot(int t, AssemblyLine c){
    this.c = c;
    tipo = t;
  }
  
  
  public void run(){
    while(true){
      try {
    	c.cogerProducto(tipo);
        Thread.sleep(r.nextInt(100));
        c.empaquetado(tipo);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
