package concurrencia;

import java.util.Random;


public class TipoA extends Thread{
	
	private TableroTareas c;
	private int id;
	
	public TipoA(TableroTareas c,int id){
		this.c = c;
		this.id = id;
	}
	
	
	public void run(){
		while (true){		
			try {
				c.PonerTareaA();
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
		}
	}

}
