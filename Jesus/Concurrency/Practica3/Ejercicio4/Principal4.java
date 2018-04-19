import Node.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Principal4 {
    public static void main(String[] args) {
        Random rnd = new Random();
        final int NUM = 100;
        final int RANK = 1000;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < NUM ; i++){
            list.add(rnd.nextInt(RANK));
        }
        Node raiz = new Node(list);
        raiz.start();
        try{
            raiz.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < NUM; i++){
            System.out.println("Element " + i + ": " + list.get(i));
        }
    }
}
