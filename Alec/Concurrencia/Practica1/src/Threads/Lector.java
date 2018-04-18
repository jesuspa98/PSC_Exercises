package Threads;

public class Lector extends java.lang.Thread {
    private VariableCompartida vc;

    public Lector(VariableCompartida vc){
        this.vc = vc;
    }

    public void run(){
        for(int i = 0; i < 100; i ++){
            System.out.println("Escribo el valor: " + vc.getV());
        }
    }
}
