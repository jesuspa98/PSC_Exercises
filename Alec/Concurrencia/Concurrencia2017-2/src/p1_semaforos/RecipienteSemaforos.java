package p1_semaforos;

import liliput.Recipiente;

import java.util.concurrent.Semaphore;

public class RecipienteSemaforos implements Recipiente {
	
	private volatile int cantidadSopaEnMililitros; //cantidad de sopa contenida en el recipiente
	private int S; //capacidad maxima del recipiente
	// private volatile int cocinerosEsperando = 0;
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore despiertaGuliver = new Semaphore(0, true);

	public RecipienteSemaforos(int s){
		S = s;
		cantidadSopaEnMililitros = 0;
	}

	@Override
	public void nuevoMililitroDeSopa(int id) throws InterruptedException {
		mutex.acquire();
		/*while(cantidadSopaEnMililitros == 20) {
			cocinerosEsperando++;
			mutex.release();
			gulliverCome.acquire();
			mutex.release();
		}*/
		cantidadSopaEnMililitros++;
		System.out.println("El cocinero " + id + " añade un nuevo mililitro de sopa. Hay "
				+ cantidadSopaEnMililitros + " de sopa");
		if(cantidadSopaEnMililitros == S) {
			despiertaGuliver.release();
		}
		mutex.release();
	}

	@Override
	public void bebeSopa() throws InterruptedException {
		despiertaGuliver.acquire();
		mutex.acquire();
		System.out.println("Gulliver se pega un trago de su sopa.");
		//gulliverCome.release(cocinerosEsperando);
		//cocinerosEsperando = 0;
		cantidadSopaEnMililitros = 0;
		mutex.release();
	}

	//IMPLEMENTAR LOS METODOS nuevoMililitroDeSopa() y bebeSopa() definidos en la interfaz liliput.Recipiente 

}
