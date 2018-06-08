package semaforos;


import java.util.concurrent.Semaphore;

public class Guarderia {
	private volatile int numeroBebes = 0, numeroAdultos = 0;
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore adultosEsperan = new Semaphore(0, true);
	private Semaphore bebesEsperan = new Semaphore(0, true);
	
	/**
	 * Un bebe que quiere entrar en la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuado entre haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void entraBebe(int id) throws InterruptedException{
		mutex.acquire();
		while(numeroAdultos*3 < (numeroBebes + 1)) {
			System.out.println("El bebé " + id + " espera para entrar en la guardería");
			mutex.release();
			bebesEsperan.acquire();
			mutex.acquire();
		}
		numeroBebes++;
		System.out.println("El bebé " + id + " ha entrado en la guardería. " +  "Hay " + numeroAdultos + " adultos y "
				+ numeroBebes + " bebés");
		mutex.release();
	}
	/**
	 * Un bebe que quiere irse de la guarderia llama a este metodo * 
	 */
	public void saleBebe(int id) throws InterruptedException{
		mutex.acquire();
		numeroBebes--;
		System.out.println("Se ha ido el bebé "+ id +  ". Hay " + numeroAdultos + " adultos y "
				+ numeroBebes + " bebés");
		if((numeroAdultos - 1) * 3 == numeroBebes) adultosEsperan.release();
		mutex.release();
	}
	/**
	 * Un adulto que quiere entrar en la guarderia llama a este metodo * 
	 */
	public void entraAdulto(int id) throws InterruptedException{
		mutex.acquire();
		numeroAdultos++;
		System.out.println("Entra el padre " + id + " en la guardería." +  " Hay " + numeroAdultos + " adultos y "
				+ numeroBebes + " bebés");
		if((numeroAdultos - 1) * 3 == numeroBebes) bebesEsperan.release();
		mutex.release();
	}
	
	/**
	 * Un adulto que quiere irse  de la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void saleAdulto(int id) throws InterruptedException{
		mutex.acquire();
		while((numeroAdultos - 1)*3 > numeroBebes) {
			mutex.release();
			adultosEsperan.acquire();
			mutex.acquire();
		}
		numeroAdultos--;
		System.out.println("Se ha ido el adulto "+ id +  ". Hay " + numeroAdultos + " adultos y "
				+ numeroBebes + " bebés");
		mutex.release();
	}

}
