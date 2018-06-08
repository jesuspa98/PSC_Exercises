package miExamen.semaforos;

public class Principal {

  public static void main(String[] args){
    //n�mero de clientes. El programa debe de funcionar correctamente con cualquier n�mero de clientes
	
    final int NUM_CLIENTES = 30;
    
    // lol 13:04 done!
  
    //crear recurso compartido y hebras y lanzar hebras
    Bar bar = new Bar();
    Cliente[] clientes = new Cliente[NUM_CLIENTES];
    for(int i = 0; i < NUM_CLIENTES; i++) {
    	clientes[i] = new Cliente(i, bar);
    }
    // lanzadas las hebras para el problema
    for(int i = 0; i < NUM_CLIENTES; i++) {
    	clientes[i].start();
    }
  }

}
