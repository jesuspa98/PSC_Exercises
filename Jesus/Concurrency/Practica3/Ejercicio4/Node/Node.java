package Node;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Node extends Thread {
    protected List<Integer> list;

    public Node(){
        list = new ArrayList<>();
    }

    public Node(List<Integer>list){
        this();
        this.list = list;
    }

    public void run(){
        if(list.size() >= 2){
            Node child1 = new Node();
            Node child2 = new Node();

            child1.add(list, 0, list.size()/2 -1);
            child2.add(list, list.size()/2, list.size());

            child1.start();
            child2.start();

            try{
                child1.join();
                child2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            shuffle(child1.list, child2.list);
        }
    }

    public void add(List<Integer> list, int from, int until){
        if(until < list.size()){
            for(int i = from; i <= until; i++){
                this.list.add(list.get(i));
            }
        }
    }

    private void shuffle(List<Integer> list1, List<Integer> list2){
        list.sort(Integer::compareTo);
    }
}
