package Threads;

public class Thread extends java.lang.Thread {
    private int n;
    private VariableCompartida vc;

    public Thread(int num, VariableCompartida v){
        n = num;
        vc = v;
    }

    public void run(){
        for(int i = 0; i < n; i++){
            vc.inc();
        }
    }
}
