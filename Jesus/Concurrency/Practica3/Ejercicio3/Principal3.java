import Ej3.*;

public class Principal3 {
    public static void main(String[] args) {
        VC vc = new VC();

        Reader l = new Reader(vc);
        Writers e = new Writers(vc);

        l.start();
        e.start();

        try{
            e.join();
            l.join();
        }catch (InterruptedException e1){
            e1.printStackTrace();
        }
    }
}
