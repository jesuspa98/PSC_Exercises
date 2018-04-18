package Fibonacci;

public class Fibonacci extends Thread{
    private int index, value;
    Fibonacci fib1, fib2;

    public Fibonacci(int index, Fibonacci fib1, Fibonacci fib2){
        value = -1;
        this.index = index;
        this.fib1 = fib1;
        this.fib2 = fib2;
    }

    public int get(){
        return value;
    }

    public void run(){
        if(index < 2){
            value = index;
        }else{
            try {
                fib1.join();
                fib2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            value = fib1.get() + fib2.get();
        }
    }
}
