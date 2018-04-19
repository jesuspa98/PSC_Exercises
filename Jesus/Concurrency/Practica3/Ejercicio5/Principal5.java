import Fibonacci.Fibonacci;

public class Principal5{
    public static void main(String[] args) {
        final int NUM = 10;
        Fibonacci[] fs = new Fibonacci[NUM + 1];
        Fibonacci fib1 = null, fib2 = null;

        for(int i = 0; i <= NUM; i++){
            fs[i] = new Fibonacci(i, fib1, fib2);
            fib2 = fib1;
            fib1 = fs[i];
            fs[i].start();
        }

        try{
           fs[NUM].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Fibonacci (" + NUM + ") = " + fs[NUM].get());

    }
}
