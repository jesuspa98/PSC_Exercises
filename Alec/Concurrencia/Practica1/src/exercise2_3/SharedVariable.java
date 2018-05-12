package exercise2_3;

public class SharedVariable {
    private int value;
    private boolean isTheValueBusy;

    public SharedVariable(){ isTheValueBusy = false; }

    public void setValue(int value) {
        while(isTheValueBusy) { /*polling!*/ }
        isTheValueBusy = true;
        this.value = value;
    }

    public int getValue() {
        while(!isTheValueBusy) { /*polling!*/ }
        isTheValueBusy = false;
        return value;
    }

    public void increment(){ this.value++; }
}
