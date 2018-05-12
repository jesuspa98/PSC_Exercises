package exercise1;

public class CharWriter extends java.lang.Thread {
    private char c;

    public CharWriter(char c){
        this.c = c;
    }

    public void run(){
        for(int i = 0; i < 1000; i++){
            System.out.println(c);
        }
    }

    public static void main(String[] args) {
        CharWriter a = new CharWriter('a');
        CharWriter b = new CharWriter('b');
        CharWriter c = new CharWriter('c');
        a.start();
        b.start();
        c.start();
        try {
            a.join();
            b.join();
            c.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
