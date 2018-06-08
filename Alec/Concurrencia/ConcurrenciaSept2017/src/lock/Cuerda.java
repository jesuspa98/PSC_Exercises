package lock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cuerda {
	private Lock mutex = new ReentrantLock();
	private Condition esperaNS = mutex.newCondition();
	private Condition esperaSN = mutex.newCondition();
	private int monosNS = 0;
	private int monosSN = 0;

	/**
	 * Utilizado por un babuino cuando quiere cruzar el cañón colgándose de la
	 * cuerda en dirección Norte-Sur
	 * Cuando el método termina, el babuino está en la cuerda y deben satisfacerse
	 * las dos condiciones de sincronización
	 * @param id del babuino que entra en la cuerda
	 * @throws InterruptedException
	 */
	public void entraDireccionNS(int id) throws InterruptedException {
		mutex.lock();
		try {
			while (monosSN > 0) {
				esperaNS.await();
			}
			while (monosNS == 3) {
				esperaNS.await();
			}
			monosNS++;
			System.out.println("Mono " + id + " entra en NS. NS " + monosNS + " SN " + monosSN);
		} finally {
			mutex.unlock();
		}
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
		mutex.lock();
		try {
			while (monosNS > 0) {
				esperaSN.await();
			}
			while (monosSN == 3) {
				esperaSN.await();
			}
			monosSN++;
			System.out.println("Mono " + id + " entra en NS. NS " + monosNS + " SN " + monosSN);
		} finally {
			mutex.unlock();
		}
	}

	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Norte-Sur
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public void saleDireccionNS(int id) throws InterruptedException {
		mutex.lock();
		try {
			if (monosNS == 0) {
				esperaSN.signalAll();
			}
			monosNS--;
			if (monosNS < 3) {
				esperaNS.signalAll();
			}
			System.out.println("Mono " + id + " entra en NS. NS " + monosNS + " SN " + monosSN);
		} finally {
			mutex.unlock();
		}
	}
	
	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Sur-Norte
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public void saleDireccionSN(int id) throws InterruptedException {
		mutex.lock();
		try {
			if (monosSN == 0) {
				esperaNS.signalAll();
			}
			monosSN--;
			if (monosSN < 3) {
				esperaSN.signalAll();
			}
			System.out.println("Mono " + id + " entra en NS. NS " + monosNS + " SN " + monosSN);
		} finally {
			mutex.unlock();
		}
	}
		
}
