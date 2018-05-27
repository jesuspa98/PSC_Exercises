package exerciseb;

import java.util.Random;

public class CNormal extends Thread{
	private int id;
	private Bandeja b;
	private Random r = new Random();

	public CNormal(int id, Bandeja b){
		this.id = id;
		this.b = b;
	}
	public void run(){
		while (true){
			try {
				b.qNormal(id);
				Thread.sleep(r.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}

