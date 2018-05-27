package exercise2;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Aseos {
	private volatile int clientes = 0;
	private volatile boolean limpiando = false;
	private Lock mutex = new ReentrantLock(true);
	private Condition equipoLimpieza = mutex.newCondition();
	private Condition estanLimpiando = mutex.newCondition();
	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza est� trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza est� trabajando o
	 * est� esperando para poder limpiar los aseos
	 *
	 */
	public void entroAseo(int id) throws InterruptedException {
		mutex.lock();
		try {
			while(limpiando) {
				estanLimpiando.await();
			}
			clientes++;
			System.out.println("El cliente con el id " + id + " ha entrado");
		} finally {
			mutex.unlock();
		}
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 *
	 */
	public void salgoAseo(int id) throws InterruptedException {
		mutex.lock();
		try {
			clientes--;
			if (clientes == 0) equipoLimpieza.signal();
			System.out.println("El cliente con id " + id + " ha salido");
		} finally {
			mutex.unlock();
		}
	}

	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos
	 * CS: El equipo de trabajo est� solo en los aseos, es decir, espera hasta que no
	 * haya ning�n cliente.
	 *
	 */
	public void entraEquipoLimpieza() throws InterruptedException {
		mutex.lock();
		try {
			equipoLimpieza.await();
			limpiando = true;
			System.out.println("El equipo de limpieza ha entrado..." + clientes);
		} finally {
			mutex.unlock();
		}
	}

	/**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos
	 *
	 *
	 */
	public void saleEquipoLimpieza() throws InterruptedException {
		mutex.lock();
		try {
			System.out.println("El equipo de limpieza ha salido..." + clientes);
			estanLimpiando.signal();
			limpiando = false;
		} finally {
			mutex.unlock();
		}
	}
}
