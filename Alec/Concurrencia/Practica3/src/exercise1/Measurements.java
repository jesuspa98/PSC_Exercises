package exercise1;

import java.util.concurrent.Semaphore;

public class Measurements {
    public static final int NUM_SENSORES = 3;

      //Cada sensor tiene un identificador (0, 1, o 2), y deja el valor que mide en la posici�n correspondiente de este array
    private int[] valores = {-1, -1, -1};
      /*hacen falta sem�foros para las condiciones de sincronizaci�n*/
    private Semaphore sensors[];
    private Semaphore worker;

    public Measurements(){
        sensors = new Semaphore[NUM_SENSORES];
        for (int i = 0; i < NUM_SENSORES; i++){
            sensors[i] = new Semaphore(0, false);
        }
        worker = new Semaphore(NUM_SENSORES, false);
    }

      //Cada sensor llama a comenzarMedicion() para asegurarse de que puede empezar su proceso de medici�n
    public void comenzarMedicion(int id) throws InterruptedException {
        worker.acquire();
        //el sensor con identificador id debe esperarse a que el trabajador haya terminado (haya llamado a finTareas()), aunque la primera vez debe de poder entrar sin problemas
    }

      //Cada sensor llama a nuevaMedicion() para notificar al trabajador de que ya tiene disponible su medici�n
    public void nuevaMedicion(int id, int value) throws InterruptedException{
        //el sensor con identificador id debe dejar su medici�n (el segundo de este m�todo) para que pueda leerla el trabajador, y avisar al trabajador
        valores[id] = value;
        sensors[id].release();
    }

      //el trabajador llama a leerMediciones() para esperar a que los tres sensores hayan generado mediciones, antes de leerlas
    public void leerMediciones() throws InterruptedException{
        //el trabajador debe esperar aqu� a que los tres sensores hayan llamado a nuevaMedicion, despu�s puede acceder a los valores de las mediciones (para mostrarlas por pantalla, por ejemplo)
        for (Semaphore s: sensors) {
            s.acquire();
        }
        //c�digo de ejemplo para mostrar los valores de las mediciones
        System.out.println("El trabajador tiene las mediciones: ");
        for (int i=0; i<valores.length; i++) {
            System.out.println("   "+i+": "+valores[i]);
        }
    }

      //el trabajador llama a finTareas() para notificar a los sensores que ya pueden empezar a tomar nuevas mediciones

    public void finTareas(){
        //el trabajador debe notificar a los sensores de que pueden comenzar una nueva medici�n
        worker.release(NUM_SENSORES);
    }

}
