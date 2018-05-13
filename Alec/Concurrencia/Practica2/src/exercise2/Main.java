package exercise2;

public class Main {
    public static void main(String[] args) {
        System.out.println("AAAAAA!");
        Lake l = new Lake(0);
        River r1 = new River(l, 0);
        River r2 = new River(l, 1);
        Dam p1 = new Dam(l, 0);
        Dam p2 = new Dam(l, 1);
        r1.start();
        r2.start();
        p1.start();
        p2.start();
        try {
            r1.join();
            r2.join();
            p1.join();
            p2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El nivel del lago es " + l.getWaterLevel());
    }
}
