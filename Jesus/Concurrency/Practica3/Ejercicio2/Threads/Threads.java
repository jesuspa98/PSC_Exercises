package Threads;

public class Threads extends Thread {
    private int number;
    private VC vc;

    public Threads(int num, VC v){
        number = num;
        vc = v;
    }

    public void run(){
        for(int i = 0; i < number; i++){
            vc.inc();
        }
    }
}
