package exercise2;

import java.util.Random;

public class SetterRobot extends Thread{

  private Random r = new Random();
  private AssemblyLine c;
  public SetterRobot(AssemblyLine c){
    this.c = c;
  }
  
  
  public void run(){
	  System.out.println("COLOCADOR");
    while(true){
      try {
          Thread.sleep(r.nextInt(100));
          c.colocar(r.nextInt(3));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
