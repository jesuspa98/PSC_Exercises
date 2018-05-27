package exercise1fair;

import java.util.concurrent.Semaphore;

public class Aseos {
	private volatile int clientes = 0, clientesEsperando = 0;
	private volatile boolean entrarALimpiar = false;
	private Semaphore equipoLimpieza = new Semaphore(1, true);
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore equipoLimpiezaEsperando = new Semaphore(1, true);

	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza est� trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza est� trabajando o
	 * est� esperando para poder limpiar los aseos
	 *
	 */
	public void entroAseo(int id) throws InterruptedException {
		mutex.acquire();
		if (entrarALimpiar) {
			clientesEsperando++;
			mutex.release();
			equipoLimpiezaEsperando.acquire();
			mutex.acquire();
		}
		clientes++;
		if (clientes == 1) {
			mutex.release();
			equipoLimpieza.acquire();
			mutex.acquire();
		}
		System.out.println("El cliente con el id " + id + " ha entrado");
		mutex.release();
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 *
	 */
	public void salgoAseo(int id) throws InterruptedException {
		mutex.acquire();
		clientes--;
		if(clientes == 0) equipoLimpieza.release();
		System.out.println("El cliente con id " + id + " ha salido");
		mutex.release();
	}

	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos
	 * CS: El equipo de trabajo est� solo en los aseos, es decir, espera hasta que no
	 * haya ning�n cliente.
	 *
	 */
	public void entraEquipoLimpieza() throws InterruptedException {
		mutex.acquire();
		entrarALimpiar = true;
		mutex.release();
		equipoLimpieza.acquire();
		System.out.println("El equipo de limpieza ha entrado..." + clientes);
	}

	/**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos
	 *
	 *
	 */
	public void saleEquipoLimpieza() throws InterruptedException {
		mutex.acquire();
		System.out.println("El equipo de limpieza ha salido..." + clientes);
		equipoLimpieza.release();
		equipoLimpiezaEsperando.release(clientesEsperando);
		clientesEsperando = 0;
		entrarALimpiar = false;
		mutex.release();
	}
}