public class Table {
    private enum IngredientsNumber {TOBACCO, PAPER, MATCH}

    private static final IngredientsNumber[] INGREDIENTS = IngredientsNumber.values();
    public static final int INGREDIENTS_NUMBER = INGREDIENTS.length;

    private boolean free = true;
    private boolean[] inTable = {false, false, false};

    public synchronized void putIngredient(boolean[] ingredients) throws InterruptedException {
        while (!free) {
            wait();
        }

        free = false;

        System.out.println("Agent put ingredients: ");

        for (int i = 0; i < ingredients.length; i++) {
            inTable[i] = ingredients[i];

            if (ingredients[i]) {
                System.out.println(INGREDIENTS[i]);
            }
        }

        System.out.println();
        notifyAll();
    }

    public synchronized void takeIngredients(int id) throws InterruptedException {
        int ingredient1 = (id + 1) % INGREDIENTS_NUMBER;
        int ingredient2 = (id + 2) % INGREDIENTS_NUMBER;

        while (!(inTable[ingredient1] && inTable[ingredient2])) {
            wait();
        }

        System.out.println("Smoker " + id + " (" + INGREDIENTS[id] + ") take the ingredients");

        for (int i = 0; i < inTable.length; i++){
            inTable[i] = false;
        }
    }

    public synchronized void setFree(int id) {
        System.out.println("Smoker " + id + " end of smoking");
        free = true;
        notifyAll();
    }
}
