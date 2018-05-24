import java.util.Random;

public class Agent extends Thread {
    private Random rnd = new Random();
    private Table table;

    public Agent(Table table) {
        this.table = table;
    }

    private void selectIngredients(boolean[] ingredients) {
        int ingredient0 = rnd.nextInt(Table.INGREDIENTS_NUMBER);
        int ingredient1 = (ingredient0 + 1) % Table.INGREDIENTS_NUMBER;
        int ingredient2 = (ingredient0 + 2) % Table.INGREDIENTS_NUMBER;
        ingredients[ingredient0] = false;
        ingredients[ingredient1] = ingredients[ingredient2] = true;
    }

    public void run() {
        boolean[] ingredients = {false, false, false};
        try {
            while (true) {
                selectIngredients(ingredients);
                table.putIngredient(ingredients);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
