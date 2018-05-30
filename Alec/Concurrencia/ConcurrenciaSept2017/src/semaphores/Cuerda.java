package semaphores;


import java.util.concurrent.Semaphore;

public class Cuerda {
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore esperaNS = new Semaphore(0, true);
	private Semaphore esperaSN = new Semaphore(0, true);
	private int monosNS = 0;
	private int monosSN = 0;
	private int esperandoNS = 0;
	private int esperandoSN = 0;

	/**
	 * Utilizado por un babuino cuando quiere cruzar el cañón colgándose de la
	 * cuerda en dirección Norte-Sur
	 * Cuando el método termina, el babuino está en la cuerda y deben satisfacerse
	 * las dos condiciones de sincronización
	 * @param id del babuino que entra en la cuerda
	 * @throws InterruptedException
	 */
	public void entraDireccionNS(int id) throws InterruptedException {
		mutex.acquire();
		while(monosSN > 0) {
			mutex.release();
			esperaNS.acquire();
			mutex.acquire();
		}
		while(monosNS == 3) {
			esperandoNS++;
			mutex.release();
			esperaNS.acquire();
			mutex.acquire();
		}
		monosNS++;
		System.out.println("Mono " + id + " entra en NS. NS " + monosNS + " SN " + monosSN);
		mutex.release();
	}

	/**
	 * Utilizado por un babuino cuando quiere cruzar el cañón  colgándose de la
	 * cuerda en dirección Norte-Sur
	 * Cuando el método termina, el babuino está en la cuerda y deben satisfacerse
	 * las dos condiciones de sincronización
	 * @param id del babuino que entra en la cuerda
	 * @throws InterruptedException
	 */
	public void entraDireccionSN(int id) throws InterruptedException {
		mutex.acquire();
		while(monosNS > 0) {
			mutex.release();
			esperaSN.acquire();
			mutex.acquire();
		}
		while(monosSN == 3) {
			esperandoSN++;
			mutex.release();
			esperaSN.acquire();
			mutex.acquire();
		}
		monosSN++;
		System.out.println("Mono " + id + " entra en NS. NS " + monosNS + " SN " + monosSN);
		mutex.release();
	}

	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Norte-Sur
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public void saleDireccionNS(int id) throws InterruptedException {
		mutex.acquire();
		if(monosNS == 0) {
			esperaSN.release();
		}
		monosNS--;
		if(monosNS < 3) {
			esperaNS.release(esperandoNS);
			esperandoNS = 0;
		}
		System.out.println("Mono " + id + " entra en NS. NS " + monosNS + " SN " + monosSN);
		mutex.release();
	}
	
	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Sur-Norte
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public void saleDireccionSN(int id) throws InterruptedException {
		mutex.acquire();
		if(monosSN == 0) {
			esperaNS.release();
		}
		monosSN--;
		if(monosSN < 3) {
			esperaSN.release(esperandoSN);
			esperandoSN = 0;
		}
		System.out.println("Mono " + id + " entra en NS. NS " + monosNS + " SN " + monosSN);
		mutex.release();
	}	
		
}
