package Ej3;

public class Reader extends Thread {

    private VC vc;
    public Reader(VC v){
        vc = v;
    }

    public void run(){
        for(int i = 0; i < 100; i++){
            System.out.println("Value: " + vc.getValue());
        }
    }
}
