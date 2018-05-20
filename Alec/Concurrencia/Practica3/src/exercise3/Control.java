package exercise3;

import java.util.concurrent.Semaphore;

//esta clase representa una torre de control (hay dos). Es inusual en que es tanto una hebra
//como la que implementa las condiciones de sincronizaci�n
public class Control extends Thread {
  //Puesto que hay una �nica pista, �sta se modela mediante el recurso compartido "airport"
  private Airport airport;
  private Semaphore controlSemaphore = new Semaphore(0);
  private Semaphore acceptSemaphore = new Semaphore(0);
  private Semaphore landedSemaphore = new Semaphore(0);

  /*hacen falta sem�foros para las condiciones de sincronizaci�n entre la torre de control
   * y los aviones que quieran aterrizar en ella. Recu�rdese que antes de cada aterrizaje,
   * cada avi�n elije una direcci�n, y puede escoger una distinta cada vez.*/
  
  public Control(Airport a) {
    airport = a;
  }
  
  public void run() {
    try {
      //cada torre de control continuamente espera y luego atiende una solicitud
      while (true) {
        esperar_solicitud();
        atender_solicitud();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  public void esperar_solicitud() throws InterruptedException {
	//la torre de control debe esperar a que un avi�n le notifique de que quiere aterrizar
      controlSemaphore.acquire();
  }
  public void atender_solicitud() throws InterruptedException {
    airport.pedirPista();
    acceptSemaphore.release();
    //mientras tiene la pista en exclusi�n mutua, la torre de control debe de dar permiso al avi�n
    //para aterrizar, y luego esperar a que el avi�n haya aterrizado
    landedSemaphore.acquire();
    airport.devolverPista();
  }
  
  public void solicitar_permiso() throws InterruptedException {
	//el avi�n debe notificar a la torre de que quiere aterrizar, y luego esperar a que el torre
	//le d� permiso
      controlSemaphore.release();
      acceptSemaphore.acquire();
  }
  public void fin_aterrizaje() {
	//el avi�n debe notificar a la torre de control de que ha aterrizado
      landedSemaphore.release();
  }
}
