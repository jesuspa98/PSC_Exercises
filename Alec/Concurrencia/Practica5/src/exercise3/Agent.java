package exercise3;

import java.util.Random;

public class Agent extends Thread{
    private Table table;
    private static Random rnd = new Random();

    public Agent(Table table){
        this.table = table;
    }

    @Override
    public void run() {
        while(true){
            try {
                table.putIngredient(rnd.nextInt(3));
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}