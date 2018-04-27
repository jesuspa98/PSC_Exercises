import DamRiversPeterson.Dam;
import DamRiversPeterson.Lake;
import DamRiversPeterson.River;

public class Principal2 {
    public static void main(String[] args) {
        Lake lake = new Lake();
        River river1 = new River(lake, 0);
        River river2 = new River(lake, 1);
        Dam dam1 = new Dam(lake, 0);
        Dam dam2 = new Dam(lake, 1);

        river1.start();
        river2.start();
        dam1.start();
        dam2.start();

        try {
            river1.join();
            river2.join();
            dam1.join();
            dam2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The water level is: " + lake.getLevel());
    }
}
