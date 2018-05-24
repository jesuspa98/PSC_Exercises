import java.util.Random;

public class main {
    public static void main(String[] args) {
        Bridge bridge = new Bridge();
        Car[] cars = new Car[10];
        Random rnd = new Random();

        for (int i = 0; i < cars.length; i++){
            cars[i] = new Car(i, bridge, rnd.nextInt(10));
            cars[i].start();
        }

        for (int i = 0; i < cars.length; i++){
            try {
                cars[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
