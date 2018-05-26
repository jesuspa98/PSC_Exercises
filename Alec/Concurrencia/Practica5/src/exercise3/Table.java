package exercise3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Table {
    enum EnumINGR {PAPER, MARIJUANA, MATCHES};
    public final static EnumINGR[] INGREDIENTS  = EnumINGR.values();
    boolean[] onTable = {false, false, false};
    private Lock lock = new ReentrantLock();
    private Condition agent = lock.newCondition();
    private Condition[] smokersWait = {lock.newCondition(), lock.newCondition(), lock.newCondition()};

    // no paper on table, no marijuana o ntable, no matches on table.

    public void stopSmoking(int ingredient) {
        lock.lock();
        try {
            onTable[0] = onTable[1] = onTable[2] = false;
            System.out.println("The smoker with ingredient " + INGREDIENTS[ingredient] + " finished smoking...");
            agent.signal();
        } finally {
            lock.unlock();
        }
    }

    public void getIngredient(int ingredient) throws InterruptedException {
        lock.lock();
        try {
            int firstIngredient = (ingredient + 1) % INGREDIENTS.length;
            int secondIngredient = (firstIngredient + 1) % INGREDIENTS.length;
            while (!(onTable[firstIngredient] && onTable[secondIngredient]))
                smokersWait[ingredient].await();
            System.out.println("Smoker with ingredient " + INGREDIENTS[ingredient] + " begin to smoke...");
        } finally {
            lock.unlock();
        }
    }

    public synchronized void putIngredient(int ingredientWontPut) throws InterruptedException{
        lock.lock();
        try {
            while (onTable[0] || onTable[1] || onTable[2])
                agent.await();
            int firstIngredient = (ingredientWontPut + 1) % INGREDIENTS.length;
            int secondIngredient = (firstIngredient + 1) % INGREDIENTS.length;
            onTable[firstIngredient] = true;
            onTable[secondIngredient] = true;
            System.out.println("The agent puts on the table the ingredients " + INGREDIENTS[firstIngredient] + " and "
                    + INGREDIENTS[secondIngredient]);
            smokersWait[ingredientWontPut].signal();
        } finally {
            lock.unlock();
        }
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
