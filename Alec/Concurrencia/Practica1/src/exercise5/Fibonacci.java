package exercise5;

public class Fibonacci extends Thread {
    private int currentValue, index;
    Fibonacci last_fib, penultimate_fib;

    public Fibonacci(Fibonacci fib1, Fibonacci fib2, int index){
        currentValue = -1;
        this.index = index;
        last_fib = fib1;
        penultimate_fib = fib2;
    }

    public int getValue(){
        return this.currentValue;
    }

    public void run() {
        if (index < 2){
             currentValue = 1;
        } else {
            try {
                last_fib.join();
                penultimate_fib.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.currentValue = last_fib.getValue() + penultimate_fib.getValue();
        }
    }

    public static void main(String[] args) {
        final int N = 45;
        Fibonacci [] f = new Fibonacci[N+1];
        Fibonacci f_1 = null, f_2 = null;
        for(int i = 0; i < N + 1; i++){
            f[i] = new Fibonacci(f_1, f_2, i);
            f_2 = f_1;
            f_1 = f[i];
            f[i].start();
        }
        try{
            f[N].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < N + 1; i++){
            System.out.println(f[i].getValue() + " ");
        }
    }

}
