package p2_locks;

import liliput.Recipiente;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RecipienteLocks implements Recipiente {
	
	private volatile int cantidadSopaEnMililitros; //cantidad de sopa contenida en el recipiente
	private int S; //capacidad maxima del recipiente
	private Lock mutex = new ReentrantLock();
	private Condition esperarParaComer = mutex.newCondition();

	
	public RecipienteLocks(int s){
		S = s;
		cantidadSopaEnMililitros = 0;
	}

	@Override
	public synchronized void nuevoMililitroDeSopa(int id) throws InterruptedException {
		mutex.lock();
		try {
			while (cantidadSopaEnMililitros == S) {
				esperarParaComer.await();
			}
			cantidadSopaEnMililitros++;
			System.out.println("El cocinero " + id + " añade un nuevo mililitro de sopa. Hay "
					+ cantidadSopaEnMililitros + " de sopa");
			if (cantidadSopaEnMililitros == S) {
				esperarParaComer.signal();
			}
		} finally {
			mutex.unlock();
		}
	}

	@Override
	public synchronized void bebeSopa() throws InterruptedException {
		mutex.lock();
		try {
			while (cantidadSopaEnMililitros < S) {
				esperarParaComer.await();
			}
			System.out.println("Gulliver se pega un trago de su sopa.");
			cantidadSopaEnMililitros = 0;
			esperarParaComer.signalAll();
		} finally {
			mutex.unlock();
		}
	}

	//IMPLEMENTAR LOS METODOS nuevoMililitroDeSopa() y bebeSopa() definidos en la interfaz liliput.Recipiente

}

