package exercise1;

public class Main {
  public static void main (String [] args) {
    Measurements m = new Measurements();
    Worker t = new Worker(m);
    t.start();
    Sensor []sensores = new Sensor[Measurements.NUM_SENSORES];
    for (int i=0; i<sensores.length; i++) {
      sensores[i] = new Sensor(i, m);
      sensores[i].start();
    }
  }
}
