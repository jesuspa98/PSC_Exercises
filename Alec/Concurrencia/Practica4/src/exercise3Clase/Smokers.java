package exercise3Clase;

import java.util.Random;

public class Smokers extends Thread {
    private Table table;
    private Random random = new Random();
    private int id;

    public Smokers(Table ref, int id){
        table = ref;
        this.id = id;
    }

    @Override
    public void run() {
        while(true){
            try {
                table.getIngredient(id);
                Thread.sleep(random.nextInt(200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
