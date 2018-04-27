package Peterson2;

public class Peterson2{
    private volatile boolean f0, f1;
    private volatile int turn;

    public Peterson2(){
        f0 = f1 = false;
        turn = 0;
    }

    public void preProtocol0(){
        f0 = true;
        turn = 1;
        while(f1 && turn == 1){
            Thread.yield();
        }
    }

    public void postProtocol0(){
        f0 = false;
    }

    public void preProtocol1(){
        f1 = true;
        turn = 0;
        while(f0 && turn == 0){
            Thread.yield();
        }
    }

    public void postProtocol1(){
        f1 = false;
    }
}
