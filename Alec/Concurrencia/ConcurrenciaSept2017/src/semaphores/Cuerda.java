package semaphores;


import java.util.concurrent.Semaphore;

public class Cuerda {
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore noMasDe3Monos = new Semaphore(1, true);
	private Semaphore monosEnElOtroSentido = new Semaphore(1, true);


	/**
	 * Utilizado por un babuino cuando quiere cruzar el cañón colgándose de la
	 * cuerda en dirección Norte-Sur
	 * Cuando el método termina, el babuino está en la cuerda y deben satisfacerse
	 * las dos condiciones de sincronización
	 * @param id del babuino que entra en la cuerda
	 * @throws InterruptedException
	 */
	public void entraDireccionNS(int id) throws InterruptedException {

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

	}

	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Norte-Sur
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public void saleDireccionNS(int id) throws InterruptedException {

	}
	
	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Sur-Norte
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public void saleDireccionSN(int id) throws InterruptedException {

	}	
		
}
