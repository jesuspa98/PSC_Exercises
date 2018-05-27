public class Coches extends Thread {
    private int id, tipo;
    private Montacargas montacargas;

    public Coches(Montacargas montacargas, int id, int tipo) {
        this.montacargas = montacargas;
        this.id = id;
        this.tipo = tipo;
    }

    public void run() {
        while (true) {
            try {
                montacargas.entrar(id, tipo);
                Thread.sleep(1000);
                montacargas.salir(id, tipo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
