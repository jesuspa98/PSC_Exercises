import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();
        Bridge Bridge = new Bridge();
        Car[]car = new Car[10];

        for (int i = 0; i < car.length; i++){
            car[i] = new Car(Bridge, rnd.nextInt(2), 5, i);
        }
        for (int i = 0; i < car.length; i++){
            car[i].start();
        }

        for (int i = 0; i < car.length;i++){
            try {
                car[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
