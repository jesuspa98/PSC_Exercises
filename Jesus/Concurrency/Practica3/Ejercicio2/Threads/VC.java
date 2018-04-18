package Threads;

public class VC{
    private int value;
    volatile private boolean data;

    public VC(){
        value = -1;
        data = false;
    }

    public  VC(int x){
        data = false;
        value = x;
    }

    public void inc(){
        data = true;
        value++;
    }

    public int getValue(){
        while(!data){//polling
            //Threads.yield();
        }

        data = false;
        return value;
    }

    public void set(int x){
        while (data){//polling
            //Threads.yield();
        }

        value = x;
        data = true;
    }
}
