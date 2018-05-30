package p2_monitores;

import liliput.Recipiente;

public class RecipienteMonitores implements Recipiente {
	
	private volatile int cantidadSopaEnMililitros; //cantidad de sopa contenida en el recipiente
	private int S; //capacidad maxima del recipiente

	
	public RecipienteMonitores(int s){
		S = s;
		cantidadSopaEnMililitros = 0;
	}

	@Override
	public synchronized void nuevoMililitroDeSopa(int id) throws InterruptedException {
		while(cantidadSopaEnMililitros == S) {
			wait();
		}
		cantidadSopaEnMililitros++;
		System.out.println("El cocinero " + id + " añade un nuevo mililitro de sopa. Hay "
				+ cantidadSopaEnMililitros + " de sopa");
		if(cantidadSopaEnMililitros == S) {
			notify();
		}
	}

	@Override
	public synchronized void bebeSopa() throws InterruptedException {
		while(cantidadSopaEnMililitros < S) {
			wait();
		}
		System.out.println("Gulliver se pega un trago de su sopa.");
		cantidadSopaEnMililitros = 0;
		notifyAll();
	}

	//IMPLEMENTAR LOS METODOS nuevoMililitroDeSopa() y bebeSopa() definidos en la interfaz liliput.Recipiente

}

