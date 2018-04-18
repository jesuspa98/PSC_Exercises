package Threads;

public class Escritor extends java.lang.Thread {
    private VariableCompartida vc;

    public Escritor(VariableCompartida vc){
        this.vc = vc;
    }

    public void run (){
        for(int i = 0; i < 100; i ++){
            vc.set(i);
        }
    }
}
