package exercise2;

public class Reader extends java.lang.Thread {
    private SharedVariable sv;

    public Reader(SharedVariable sv) {
        this.sv = sv;
    }

    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println(sv.getValue());
        }
    }
}
