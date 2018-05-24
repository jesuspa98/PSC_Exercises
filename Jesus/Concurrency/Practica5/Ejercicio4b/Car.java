import java.util.Random;

public class Car extends Thread{
    private Bridge bridge;
    private int sentido;
    private int numVueltas;
    private int id;
    private Random rnd = new Random();

    public Car(Bridge b, int s, int n, int id){
        sentido = s;
        bridge = b;
        numVueltas = n;
        this.id = id;
    }

    public void run(){
        for (int i = 0; i < numVueltas; i++){
            try{
                Thread.sleep(rnd.nextInt(200));
                bridge.getIn(sentido, i);
                Thread.sleep(rnd.nextInt(100));
                bridge.getOut(sentido, i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
