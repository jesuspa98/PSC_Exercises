package exercise3;

public class Table {
    enum EnumINGR {PAPER, MARIJUANA, MATCHES};
    public final static EnumINGR[] INGREDIENTS  = EnumINGR.values();
    boolean[] onTable = {false, false, false};
    // no paper on table, no marijuana o ntable, no matches on table.

    public synchronized void stopSmoking(int ingredient) {
        onTable[0] = onTable[1] = onTable[2] = false;
        System.out.println("The smoker with ingredient " + INGREDIENTS[ingredient] + " finished smoking...");
        notifyAll();
    }

    public synchronized void getIngredient(int ingredient) throws InterruptedException {
        int firstIngredient = (ingredient + 1) % INGREDIENTS.length;
        int secondIngredient = (firstIngredient + 1) % INGREDIENTS.length;
        while (!(onTable[firstIngredient] && onTable[secondIngredient]))
            wait();
        System.out.println("Smoker with ingredient " + INGREDIENTS[ingredient] + " begin to smoke...");
    }

    public synchronized void putIngredient(int ingredientWontPut) throws InterruptedException{
        while(onTable[0] || onTable[1] || onTable[2])
            wait();
        int firstIngredient = (ingredientWontPut + 1) % INGREDIENTS.length;
        int secondIngredient = (firstIngredient + 1) % INGREDIENTS.length;
        onTable[firstIngredient] = true;
        onTable[secondIngredient] = true;
        System.out.println("The agent puts on the table the ingredients " + INGREDIENTS[firstIngredient] + " and "
         + INGREDIENTS[secondIngredient]);
        notifyAll();
    }


    public static void main(String[] args) {
        Table m = new Table();
        Smoker[] f = {new Smoker(m,0), new Smoker(m,1), new Smoker(m,2)};
        Agent a = new Agent(m);
        f[0].start(); f[1].start(); f[2].start(); a.start();
        try {
            f[0].join();f[1].join(); f[2].join(); a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
