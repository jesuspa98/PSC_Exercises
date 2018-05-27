package exerciseb;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bandeja{
	private static final int CAPACIDAD = 8;
	private volatile int pastelesNormales;
	private volatile int pastelesPremium;
	private Lock mutex = new ReentrantLock(true);
	private Condition estaReponiendo = mutex.newCondition();
	private Condition despiertaReponedor = mutex.newCondition();


	public Bandeja() {
		pastelesPremium = CAPACIDAD / 2;
		pastelesNormales = CAPACIDAD / 2;
	}

	/**
	 * el reponedor espera hasta que se termina de un tipo de pasteles. Rellena pastelitos en la bandeja  y 	 *los pone todos de una vez
	 * 
	 * @throws InterruptedException
	 */
	public void reponer() throws InterruptedException{
		mutex.lock();
		try {
			despiertaReponedor.await();
			System.out.println("Se ha repuesto la bandeja con magdalenas premium y normales");
			pastelesNormales = CAPACIDAD / 2;
			pastelesPremium = CAPACIDAD / 2;
			estaReponiendo.signalAll();
		} finally {
			mutex.unlock();
		}
	}
	

	/**
	 * el cliente premium quiere su pastel
	 * 
	 * @throws InterruptedException
	 */

	public void qPremium(int id) throws InterruptedException{
		mutex.lock();
		try {
			while (pastelesPremium == 0) {
				estaReponiendo.await();
			}
			pastelesPremium--;
			System.out.println("El cliente PREMIUM " + id + " coge una magdalenita. Quedan " + pastelesPremium);
			if ((pastelesNormales == 0) && (pastelesPremium == 0)) {
				despiertaReponedor.signal();
			}
		} finally {
			mutex.unlock();
		}
	}
	
	/**
	 * el cliente normal quiere su pastel
	 * 
	 * @throws InterruptedException
	 */
	
	public void qNormal(int id) throws InterruptedException{
		mutex.lock();
		try {
			while (pastelesNormales == 0) {
				estaReponiendo.await();
			}
			pastelesNormales--;
			System.out.println("El cliente " + id + " coge una magdalenita. Quedan " + pastelesNormales);
			if ((pastelesNormales == 0) && (pastelesPremium == 0)) {
				despiertaReponedor.signal();
			}
		} finally {
			mutex.unlock();
		}
	}

}
