public class Main {
    final static int BUFS = 5;
    final static int NC = 3;
    final static int NP = 3;
    final static int NVP = 10;

    public static void main(String[] args) {
        try {
            Buffer buffer = new Buffer(BUFS, NC);
            Productor[] prod = new Productor[NP];
            for (int i = 0; i < prod.length; ++i) {
                prod[i] = new Productor(i, NVP, buffer);
                prod[i].start();
            }
            Consumidor[] cons = new Consumidor[NC];
            for (int i = 0; i < cons.length; ++i) {
                cons[i] = new Consumidor(i, NVP * NP, buffer);
                cons[i].start();
            }
            for (int i = 0; i < prod.length; ++i)
                prod[i].join();
            for (int i = 0; i < cons.length; ++i)
                cons[i].join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
