package exercise4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node extends Thread {
    private List<Integer> list;

    public Node(List<Integer> list){
        this.list = list;
    }

    public Node(){
        this.list = new ArrayList<>();
    }

    private void add(List<Integer> list, int from, int to){
        this.list.addAll(list.subList(from, to));
    }

    private void mix(List<Integer> list1, List<Integer> list2){
        list.addAll(list1);
        list.addAll(list2);

    }

    @Override
    public void run() {
        if (list.size() > 1) {
            Node son1 = new Node();
            Node son2 = new Node();
            son1.add(list, 0, list.size() / 2 - 1);
            son2.add(list, list.size() / 2, list.size());
            son1.run();
            son2.run();
            try {
                son1.join();
                son2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.sort(Integer::compareTo);
    }

    public static void main(String[] args) {
        Random r = new Random();
        final int NUM = 100;
        final int RANGE = 1500;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < NUM; i++){
            list.add(r.nextInt(RANGE));
        }
        Node root = new Node(list);
        root.start();

        try {
            root.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < NUM; i++){
            System.out.println(list.get(i) + " ");
        }
        System.out.println();
    }
}
