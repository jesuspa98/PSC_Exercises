package concurrencia;
import java.util.concurrent.Semaphore;

public class TableroTareas {
	private int cap;
	private int tareasDeTipoA = 0, tareasDeTipoB = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore tableroLleno = new Semaphore(cap);
	private Semaphore noHayTareas = new Semaphore(cap);

	
	
	public TableroTareas(int Cap){
		this.cap = Cap;
	}
	

	public void PonerTareaA() throws InterruptedException{
		tableroLleno.acquire();
		mutex.acquire();
		System.out.println("Se añade una nueva tarea de tipo A. Quedan " + tareasDeTipoA + " A y " + tareasDeTipoB + " B.");
		tareasDeTipoA++;
		noHayTareas.release();
		mutex.release();
	}
	

	public void PonerTareaB() throws InterruptedException{
		tableroLleno.acquire();
		mutex.acquire();
		System.out.println("Se añade una nueva tarea de tipo B. Quedan " + tareasDeTipoA + " A y " + tareasDeTipoB + " B.");
		tareasDeTipoB++;
		noHayTareas.release();
		mutex.release();
	}
	
	public void RecogerTarea (int id) throws InterruptedException {
		noHayTareas.acquire();
		mutex.acquire();
		System.out.println("El trabajador " + id + " empieza a realizar una tarea." + tareasDeTipoA + " " + tareasDeTipoB);
		if(tareasDeTipoA != 0) {
			tareasDeTipoA--;
		} else {
			tareasDeTipoB--;
		}
		if((tareasDeTipoA + tareasDeTipoB) <= cap) {
			tableroLleno.release();
		}
		mutex.acquire();
	}
	
	public void Trabajar(int id) throws InterruptedException { 
		System.out.println("Trabajando" + id);
		Thread.sleep(1000);
	}
	
	
	public void cafe(int id) throws InterruptedException {
	System.out.println("cafe" + id);
	Thread.sleep(500);
	}

	
}
