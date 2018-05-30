package liliput;

public class Gulliver extends Thread{
	
	private Recipiente recipiente;
	public Gulliver(Recipiente recipiente){
		this.recipiente = recipiente;
	}
	
	public void run(){
		while(true){
			try {
				recipiente.bebeSopa();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
