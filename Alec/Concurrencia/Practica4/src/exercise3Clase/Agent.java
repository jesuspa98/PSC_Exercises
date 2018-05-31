package exercise3Clase;

import java.util.Random;

public class Agent extends Thread {
    private Table table;
    private Random random = new Random();

    public Agent(Table ref){
        table = ref;
    }

    @Override
    public void run() {
        while(true){
            try {
                table.setIngredient(random.nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
