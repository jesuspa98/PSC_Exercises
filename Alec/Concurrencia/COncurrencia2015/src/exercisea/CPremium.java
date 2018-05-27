package exercisea;

import java.util.*;

public class CPremium extends Thread{
	private int id;
	private Bandeja b;
	private Random r = new Random();

	public CPremium(int id,Bandeja b){
		this.id = id;
		this.b = b;
	}

	public void run(){
		while (true){
			try {
				b.qPremium(id);
				Thread.sleep(r.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


		}
	}
}
