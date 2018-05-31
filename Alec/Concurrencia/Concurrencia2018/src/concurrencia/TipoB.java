package concurrencia;

import java.util.Random;

public class TipoB extends Thread {
	private static Random r = new Random();
	private TableroTareas c;
	private int id;
	
	public TipoB(TableroTareas c,int id){
		this.c = c;
		this.id = id;
	}
	
	
	public void run(){
		while (true){		
			try {
				c.PonerTareaB();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		// COMPLETAR

		}
	}
}
