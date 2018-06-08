package liliput;

public class Cocinero extends Thread{
	private Recipiente recipiente;
	private int id;
	
	public Cocinero(Recipiente recipiente,int id){
		this.recipiente = recipiente;
		this.id = id;
	}
	
	public void run(){
		while (true){
			try {
				Thread.sleep(500); //prepara sopa
				recipiente.nuevoMililitroDeSopa(id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
