package exercise3;

public class Main {
  public static void main(String[] args) {
    Airport a = new Airport();
    Control []control = {new Control(a, 0), new Control(a, 1)};
    control[0].start();
    control[1].start();
    Airplane[]aviones = new Airplane[10];
    for (int i=0; i<aviones.length; i++) {
      aviones[i] = new Airplane(i, control);
      aviones[i].start();
    }
  }
}
