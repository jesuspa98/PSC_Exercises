public class Fibonacci extends Thread {
    private int indice, valor;
    Fibonacci latestFib, penultimateFib;

    public Fibonacci(int ind, Fibonacci ind_1, Fibonacci ind_2){
        valor = -1;
        indice = ind;
        latestFib = ind_1;
        penultimateFib = ind_2;
    }

    public int get(){
        return valor;
    }

    public void run(){
        if (indice < 2){
            valor = indice;
        } else {
            try{
                latestFib.join();
                latestFib.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            valor = latestFib.get() + penultimateFib.get();
        }
    }

    public static void main(String[] args) {
        final int N = 25;
        Fibonacci [] f = new Fibonacci[N+1];
        Fibonacci f_1 = null, f_2 = null;
        for(int i = 0; i < N + 1; i++){
            f[i] = new Fibonacci(i, f_1, f_2);
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
            System.out.println(f[i].get() + " ");
        }
    }
}
