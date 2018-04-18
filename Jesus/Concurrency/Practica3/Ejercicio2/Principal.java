import Threads.Threads;
import Threads.VC;

public class Principal {
    public static void main(String[] args) {
        VC v = new VC(0);
        Threads h1 = new Threads(100, v);
        Threads h2 = new Threads(100, v);
        h1.start();
        h2.start();
        try{
            h1.join();
            h2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(v.getValue());
    }
}
