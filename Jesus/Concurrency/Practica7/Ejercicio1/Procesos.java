import java.util.Random;

public class Procesos extends Thread {
    private int id, tipo;
    private Room room;
    private Random rnd = new Random();

    public Procesos(Room room, int id, int tipo) {
        this.room = room;
        this.id = id;
        this.tipo = tipo;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(rnd.nextInt(200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                room.entrar(id, tipo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
