package exercise3;

import java.util.Random;

public class Smoker extends Thread {
    private int ingredient;
    private Table table;
    private static Random rnd = new Random();

    public Smoker(Table table, int ingredient){
        this.table = table;
        this.ingredient = ingredient;
    }

    @Override
    public void run() {
        while(true){
            try {
                table.getIngredient(ingredient);
                Thread.sleep(rnd.nextInt(1000));
                table.stopSmoking(ingredient);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
