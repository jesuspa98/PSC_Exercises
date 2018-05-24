import java.util.concurrent.Semaphore;

public class Chain {
    public static final int NUM_TYPES = 3;
    private int numPackages;
    private int[] tape;
    private Semaphore thereIsSpace;
    private Semaphore processed;
    private Semaphore[] thereIsProduct;


    public Chain(int length) {
        numPackages = 0;
        tape = new int[length];
        for(int i = 0; i < tape.length; i++){
            tape[i] = -1;
        }
        thereIsSpace = new Semaphore(1);
        processed = new Semaphore(1);
        thereIsProduct = new Semaphore[NUM_TYPES];
        for (int i = 0; i < thereIsProduct.length; i++){
            thereIsProduct[i] = new Semaphore(0);
        }
    }

    public void place(int type) throws InterruptedException {
        thereIsSpace.acquire();
        int i = 0;

        while(i < tape.length && tape[i] != -1){
            i++;
        }

        tape[i] = type;
        System.out.println("Setter place the product of type: " + type + " in the position " + i);
        thereIsProduct[type].release();
    }

    public void takeProduct(int type) throws InterruptedException {
        thereIsProduct[type].acquire();
        int i = 0;

        while(i < tape.length && tape[i] != -1){
            i++;
        }

        tape[i] = -1;
        System.out.println("Packager take the product of type: " + type + " of position: " + i);
    }

    public void packaging(int type) throws InterruptedException {
        processed.acquire();
        numPackages++;
        System.out.println("Finished packaging product. Total packaged: " + numPackages);
        processed.release();
    }
}
