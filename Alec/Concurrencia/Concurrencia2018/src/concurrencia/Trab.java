package concurrencia;

public class Trab extends Thread {

	private TableroTareas c;
	private int id;

	
	public Trab(TableroTareas c,int id){
		this.c = c;
		this.id = id;
	}
	
	
	public void run(){
		while (true){		
			try {
				c.RecogerTarea(id);;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				c.Trabajar(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				c.cafe(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
