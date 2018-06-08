package exercise3Clase;

import java.util.Arrays;

public class Table {
    enum Ingredients {PAPER, TABBACCO, CERILLAS};
    public final static Ingredients[] INGREDIENTS = Ingredients.values();
    boolean[] ingredientsOn = {false, false, false};

    public synchronized void setIngredient(int ingredient) throws InterruptedException {
        while(ingredientsOn[0] || ingredientsOn[1] || ingredientsOn[2]){
           wait();
        }
        int ingredientNeeded1 = (ingredient + 1) % INGREDIENTS.length;
        int ingredientNeeded2 = (ingredient + ingredientNeeded1) % INGREDIENTS.length;
        ingredientsOn[ingredientNeeded1] = true;
        ingredientsOn[ingredientNeeded2] = true;
        System.out.println("Agente pone en la mesa " + INGREDIENTS[ingredient]);
        notifyAll();
    }

    public void onTable(){
        System.out.println("Encima de la mesa: " + Arrays.toString(ingredientsOn));
    }

    public synchronized void getIngredient(int ingredient) throws InterruptedException {
        int ingredientNeeded1 = (ingredient + 1) % INGREDIENTS.length;
        int ingredientNeeded2 = (ingredient + ingredientNeeded1) % INGREDIENTS.length;
        while (!(ingredientsOn[ingredientNeeded2] && ingredientsOn[ingredientNeeded1])){
            wait();
        }
        System.out.println("El fumador con" + INGREDIENTS[ingredient] + " se lia un porramen y empieza a fumar");
        ingredientsOn[ingredient] = true;
    }

    public synchronized void finishedSmoking(){
        ingredientsOn[0] = ingredientsOn[1] = ingredientsOn[2] = false;
        System.out.println("El fumador ha dejado de fumar");
        notifyAll();
    }
}
