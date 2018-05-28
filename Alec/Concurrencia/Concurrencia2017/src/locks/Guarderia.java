package locks;


import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Guarderia {
	private volatile int numeroBebes = 0, numeroAdultos = 0;
	private Lock mutex = new ReentrantLock(true);
	private Condition bebesEsperan = mutex.newCondition();
	private Condition adultosEsperan = mutex.newCondition();

	/**
	 * Un bebe que quiere entrar en la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuado entre haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void entraBebe(int id) throws InterruptedException{
		mutex.lock();
		try {
			while (numeroAdultos * 3 < (numeroBebes + 1)) {
				System.out.println("El bebé " + id + " espera para entrar en la guardería");
				bebesEsperan.await();
			}
			numeroBebes++;
			System.out.println("El bebé " + id + " ha entrado en la guardería. " + "Hay " + numeroAdultos + " adultos y "
					+ numeroBebes + " bebés");
		} finally {
			mutex.unlock();
		}
	}
	/**
	 * Un bebe que quiere irse de la guarderia llama a este metodo * 
	 */
	public void saleBebe(int id) throws InterruptedException{
		mutex.lock();
		try {
			numeroBebes--;
			System.out.println("Se ha ido el bebé " + id + ". Hay " + numeroAdultos + " adultos y "
					+ numeroBebes + " bebés");
			if ((numeroAdultos - 1) * 3 == numeroBebes) adultosEsperan.notify();
		} finally {
			mutex.unlock();
		}
	}
	/**
	 * Un adulto que quiere entrar en la guarderia llama a este metodo * 
	 */
	public void entraAdulto(int id) throws InterruptedException{
		mutex.lock();
		try {
			numeroAdultos++;
			System.out.println("Entra el padre " + id + " en la guardería." + " Hay " + numeroAdultos + " adultos y "
					+ numeroBebes + " bebés");
			if ((numeroAdultos - 1) * 3 == numeroBebes) bebesEsperan.notify();
		} finally {
			mutex.unlock();
		}
	}
	
	/**
	 * Un adulto que quiere irse  de la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void saleAdulto(int id) throws InterruptedException{
		mutex.lock();
		try {
			while ((numeroAdultos - 1) * 3 > numeroBebes) {
				adultosEsperan.await();
			}
			numeroAdultos--;
			System.out.println("Se ha ido el adulto " + id + ". Hay " + numeroAdultos + " adultos y "
					+ numeroBebes + " bebés");
		} finally {
			mutex.unlock();
		}
	}

}
