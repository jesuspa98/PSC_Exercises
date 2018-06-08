package exercisea;

import java.util.concurrent.*;
public class Bandeja{
	private static final int CAPACIDAD = 8;
	private volatile int pastelesNormales;
	private volatile int pastelesPremium;
	private volatile int clientesEsperando = 0;
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore despiertaReponedor = new Semaphore(0, true);
	private Semaphore estaReponiendo = new Semaphore(1, true);

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
		despiertaReponedor.acquire();
		mutex.acquire();
		if(pastelesNormales == 0) {
			pastelesNormales = CAPACIDAD / 2;
			System.out.println("Se ha repuesto la bandeja con magdalenas normales");
		}
		if(pastelesPremium == 0) {
			pastelesPremium = CAPACIDAD / 2;
			System.out.println("Se ha repuesto la bandeja con magdalenas premium");
		}
		estaReponiendo.release(clientesEsperando);
		clientesEsperando = 0;
		mutex.release();
	}
	

	/**
	 * el cliente premium quiere su pastel
	 * 
	 * @throws InterruptedException
	 */

	public void qPremium(int id) throws InterruptedException{
		mutex.acquire();
		while(pastelesPremium == 0) {
			clientesEsperando++;
			mutex.release();
			estaReponiendo.acquire();
			mutex.acquire();
		}
		pastelesPremium--;
		System.out.println("El cliente PREMIUM " + id + " coge una magdalenita. Quedan " + pastelesPremium);
		if ((pastelesPremium == 0)) {
			despiertaReponedor.release();
		}
		mutex.release();
	}
	
	/**
	 * el cliente normal quiere su pastel
	 * 
	 * @throws InterruptedException
	 */
	
	public void qNormal(int id) throws InterruptedException{
		mutex.acquire();
		while( pastelesNormales == 0) {
			clientesEsperando++;
			mutex.release();
			estaReponiendo.acquire();
			mutex.acquire();
		}
		pastelesNormales--;
		System.out.println("El cliente " + id + " coge una magdalenita. Quedan " + pastelesNormales);
		if((pastelesNormales == 0)) {
			despiertaReponedor.release();
		}
		mutex.release();
	}

}
