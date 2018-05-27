public class Main {
    public static void main(String[] args) {
        final int NA = 10;
        final int NB = 10;
        Montacargas habitacion = new Montacargas();
        Coches[] a = new Coches[NA];
        Coches[] b = new Coches[NB];
        for (int i = 0; i < NA; i++){
            a[i] = new Coches(habitacion, i, 1);
            a[i].start();
        }
        for (int i = 0; i < NB; i++){
            b[i] = new Coches(habitacion, i, 1);
            b[i].start();
        }
    }
}
