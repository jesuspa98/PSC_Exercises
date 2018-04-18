import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Nodo extends java.lang.Thread {
    protected List<Integer> list;

    public Nodo(){
        this.list = new ArrayList<>();
    }

    public Nodo(List<Integer> list){
        this.list = list;
    }

    private void anade(List<Integer> lista, int desde, int hasta){
        for(int i = desde; i < hasta; i++){
            this.list.add(lista.get(i));
        }
    }

    private void mezcla(List<Integer> list1, List<Integer> list2){

    }

    public void run(){
        if (list.size() >= 2){
            Nodo hijo1 = new Nodo();
            Nodo hijo2 = new Nodo();
            hijo1.anade(list, 0, list.size()/2 - 1);
            hijo2.anade(list, list.size()/2, list.size());

            hijo1.start();
            hijo2.start();

            try{
                hijo1.join();
                hijo2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mezcla(hijo1.list, hijo2.list);
        }
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        final int NUME = 100;
        final int RANGO = 1000;
        List<Integer> lista = new ArrayList<>();
        for(int i = 0; i < NUME; i++){
            lista.add(rnd.nextInt(RANGO));
        }
        Nodo raiz = new Nodo(lista);
        raiz.start();

        try{
            raiz.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < NUME; i++){
            System.out.println(lista.get(i) +  " ");
        }
        System.out.println("\n");
    }

}
