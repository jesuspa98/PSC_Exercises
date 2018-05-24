import java.util.Random;

public class Smoker extends Thread {
    private Random rnd = new Random();
    private Table table;
    private int id;

    public Smoker(Table table, int id) {
        this.table = table;
        this.id = id;
    }

    public void run() {
        try {
            while (true) {
                table.takeIngredients(id);
                System.out.println("Smoker " + id + " is smoking");
                Thread.sleep(rnd.nextInt(100));
                table.setFree(id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
