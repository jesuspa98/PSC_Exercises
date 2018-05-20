package philosopherproblem;

public class Main {
    public static void main(String[] args) {
        Chair[] chairs = new Chair[5];
        Fork[] forks = new Fork[5];
        Philosopher[] philosophers = new Philosopher[5];
        for(int i = 0; i < 5; i++){
            forks[i] = new Fork();
            chairs[i] = new Chair();
        }
        for(int i = 0; i < 5; i++){
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % 5], chairs[i]);
        }
        for (int i = 0; i < 5; i++){
            philosophers[i].start();
        }
        try {
            for (int i = 0; i < 5; i++){
                philosophers[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
