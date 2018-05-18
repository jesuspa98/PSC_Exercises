package exercise2;

public class Main {
  public static void main (String [] args) {
    final int tamCinta = 6;
    AssemblyLine c = new AssemblyLine(tamCinta);
    SetterRobot co = new SetterRobot(c);
    co.start();
    PackagerRobot[]empaquetadores = new PackagerRobot[AssemblyLine.NUM_TIPOS];
    for (int i=0; i<empaquetadores.length; i++) {
      empaquetadores[i] = new PackagerRobot(i, c);
      empaquetadores[i].start();
    }
  }
}
