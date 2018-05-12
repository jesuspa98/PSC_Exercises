package exercise2_3;

public class Writer extends java.lang.Thread{
    private SharedVariable sv;

    public Writer(SharedVariable sv) {
        this.sv = sv;
    }

    public void run() {
        for(int i = 0; i < 100; i++){
            sv.setValue(i);
        }
    }
}
