import Threads.Escritor;
import Threads.Lector;
import Threads.VariableCompartida;

public class Main {
    public static void main(String[] args) {
        VariableCompartida vc = new VariableCompartida();
        Lector l = new Lector(vc);
        Escritor e = new Escritor(vc);
        l.start();
        e.start();
        try{
            e.join();
            l.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
