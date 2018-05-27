public class Main {
    public static void main(String[] args) {
        final int NA = 10;
        final int NB = 10;
        Room room = new Room();
        Procesos[] a = new Procesos[NA];
        Procesos[] b = new Procesos[NB];
        for (int i = 0; i < NA; i++){
            a[i] = new Procesos(room, i, 1);
            a[i].start();
        }
        for (int i = 0; i < NB; i++){
            b[i] = new Procesos(room, i, 1);
            b[i].start();
        }
    }
}
