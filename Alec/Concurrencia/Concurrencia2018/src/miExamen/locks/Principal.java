package miExamen.locks;



public class Principal {

  public static void main(String[] args) {
    //n�mero de estudiantes. El programa debe de funcionar correctamente con cualquier n�mero de estudiantes
	  // lol 13:22 done!
    final int NUM_ESTUDIANTES = 30;
    //crear recurso compartido y hebras y lanzar hebras
    Habitacion h = new Habitacion();
    Decano d = new Decano(h);
    Estudiante[] estudiantes = new Estudiante[NUM_ESTUDIANTES];
    for(int i = 0; i < NUM_ESTUDIANTES; i++) {
    	estudiantes[i] = new Estudiante(i, h);
    }
    d.start();
    for(int i = 0; i < NUM_ESTUDIANTES; i++) {
    	estudiantes[i].start();
    }
   
    
  }

}
