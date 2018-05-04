package exercise2;

public class SharedVariable {
    private int value;
    private boolean isTheValueBusy;

    public SharedVariable(){ isTheValueBusy = false; }

    public void setValue(int value) {
        while(isTheValueBusy){
            Thread.yield();
        }
        isTheValueBusy = true;
        this.value = value;
    }

    public int getValue() {
        while(!isTheValueBusy){
            Thread.yield();
        }
        isTheValueBusy = false;
        return value;
    }

    public void increment(){ this.value++; }
}
