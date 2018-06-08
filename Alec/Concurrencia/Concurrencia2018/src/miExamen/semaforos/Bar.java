package miExamen.semaforos;

import java.util.concurrent.Semaphore;

public class Bar {
  //n�mero de asientos. El programa debe de funcionar correctamente con cualquier n�mero de asientos
  public static final int NUM_ASIENTOS = 10;
  private int clientesDentro = 0;
  private Semaphore mutex = new Semaphore(1, true);
  private Semaphore meEspero = new Semaphore(0, true);

  
  /**
   * Utilizado por el cliente id cuando quiere entrar en el restaurante.
   * Si hay sitio, se sienta. Si est� lleno, debe esperar a que TODO el grupo
   * que ocupa el restaurante se haya marchado antes de sentarse.
   */
  public void pidoMesa(int id) throws InterruptedException{
	  mutex.acquire();
	  while(clientesDentro == NUM_ASIENTOS) {
		  System.out.println("El cliente " + id + " se espera hasta que pueda entrar." + clientesDentro);
		  mutex.release();
		  meEspero.acquire(); // evitamos bloquearnos dentro del mutex para poder seguir con la ejecuci�n
		  mutex.acquire();
	  }
	  clientesDentro++;
	  System.out.println("El cliente " + id + " ha entrado al bar." + clientesDentro);
	  mutex.release();
  }

  /**
   * Utilizado por el cliente id cuando se marcha del restaurante
   */
  public void meVoy(int id) throws InterruptedException{
	  mutex.acquire();
	  clientesDentro --;
	  System.out.println("El cliente " + id + " se va del bar..." + clientesDentro);
	  meEspero.release(); // dejamos pasar a uno de los que est�n esperando en la cola
	  mutex.release();
  }

}
