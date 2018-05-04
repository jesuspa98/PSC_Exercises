package exercise2;

public class Writer extends java.lang.Thread{
    private SharedVariable sv;

    public Writer(SharedVariable sv) {
        this.sv = sv;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            sv.setValue(i);
        }
    }
}
