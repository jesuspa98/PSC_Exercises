public class Main {
    public static void main(String[] args) {
        Airport airport = new Airport();
        Control[] control = {new Control(airport), new Control(airport)};
        control[0].start();
        control[1].start();
        Airplane[] airplanes = new Airplane[10];
        for (int i = 0; i < airplanes.length; i++) {
            airplanes[i] = new Airplane(i, control);
            airplanes[i].start();
        }
    }
}
