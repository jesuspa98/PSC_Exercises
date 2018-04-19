package Ej3;

public class Writers extends Thread {
    private VC vc;

    public Writers(VC v){
        vc = v;
    }

    public void run(){
        for(int i = 0; i < 100; i++){
            vc.set(i);
        }
    }
}
