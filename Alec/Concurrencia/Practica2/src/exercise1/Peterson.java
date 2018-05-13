package exercise1;

public class Peterson {
    private volatile boolean f0, f1;
    private volatile int turn;

    public Peterson(){
        f0 = f1 = false;
        turn = 0;
    }

    public void preProtocole0(){
        f0 = true;
        turn = 1;
        while (f1 && turn == 1) {
            Thread.yield();
        }
    }

    public void postProtocole0(){
        f0 = false;
    }

    public void preProtocole1(){
        f1 = true;
        turn = 0;
        while (f0 && turn == 0){
            // While is the turn of the other function, wait...
            Thread.yield();
        }
    }

    public void postProtocole1(){
        f1 = false;
    }
}
