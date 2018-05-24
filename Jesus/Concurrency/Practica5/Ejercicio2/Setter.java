import java.util.Random;

public class Setter extends Thread{

    private Random rnd = new Random();
    private Chain chain;
    public Setter(Chain chain){
    this.chain = chain;
    }


    public void run(){
        System.out.println("SETTER");

        while(true){
            try {
                Thread.sleep(rnd.nextInt(100));
                chain.place(rnd.nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
