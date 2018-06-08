package miExamen.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Habitacion {
  //El programa debe funcionar correctamente para cualquier valor de este l�mite
  public static final int LIMITE_ESTUDIANTES = 5;
  private boolean decanoDentro = false;
  private int estudiantesDentro = 0;
  private Lock lock = new ReentrantLock();
  private Condition decano = lock.newCondition();
  private Condition estudiantes = lock.newCondition();

  public void entraEstudiante(int id) throws InterruptedException{
	  lock.lock();
	  try {
		  while(decanoDentro) {
			  estudiantes.await();
		  }
		  estudiantesDentro++;
		  System.out.println("El estudiante " + id + " entra en la habitaci�n. Hay " + estudiantesDentro);
		  if(estudiantesDentro >= LIMITE_ESTUDIANTES) {
			  decano.signal(); // AVISA AL DECANO SI HAY M�S ESTUDIANTES QUE EL LIMITE
		  }
	  } finally {
		  lock.unlock();
	  }
  }

    public void saleEstudiante(int id)  throws InterruptedException{
    	lock.lock();
    	try {
    		estudiantesDentro--;
    		System.out.println("El estudiante " + id + " sale de la habtaci�n. Hay " + estudiantesDentro);
    		if(estudiantesDentro == 0) {
    			decano.signal(); // AVISA AL DECANO PARA PODER SALIR DE LA HABITACION
    		}
    	} finally {
    		lock.unlock();
    	}
  }
    
    
    public void entraDecano() throws InterruptedException{
    	lock.lock();
    	try {
    		while(estudiantesDentro != 0 && estudiantesDentro < LIMITE_ESTUDIANTES) {
    			decano.await(); // No puedo entrar hasta que, o est� vac�o, o el haya m�s estudiantes que el l�mite
    		}
    		decanoDentro = true;
    		System.out.println("Ha entrado el decano a la habitaci�n! Hay " + estudiantesDentro);
    	} finally {
    		lock.unlock();
    	}
  }
 
    public void saleDecano() throws InterruptedException{
    	lock.lock();
    	try {
    		while(estudiantesDentro != 0) {
    			decano.await(); // no puedo salir hasta que no haya estudiantes dentro
    		}
    		System.out.println("El decano sale de la habitaci�n. Quedan " + estudiantesDentro);
    		decanoDentro = false; 
    		estudiantes.signalAll();
    	} finally {
    		lock.unlock();
    	}
  }
}
