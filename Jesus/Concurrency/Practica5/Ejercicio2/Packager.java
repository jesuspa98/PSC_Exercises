import java.util.Random;

public class Packager extends Thread{
    private Random rnd = new Random();
    private Chain chain;
    private int type;

    public Packager(int t, Chain chain){
        this.chain = chain;
        type = t;
    }


    public void run(){
        while(true){
            try {
                chain.takeProduct(type);
                Thread.sleep(rnd.nextInt(100));
                chain.packaging(type);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
