package exerciseb;

import java.util.Random;

public class Reponedor extends Thread {
	private Bandeja b;
	private Random r = new Random();

	public Reponedor(Bandeja b){
		this.b = b;
	}

	public void run(){
		while (true){
			try {
				b.reponer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
