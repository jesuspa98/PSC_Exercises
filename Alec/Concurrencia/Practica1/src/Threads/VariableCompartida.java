package Threads;

public class VariableCompartida{
    private int v;
    volatile private boolean isThereAnyDataOutThere; /* volatile: cada vez que se lee y se escribe de esa variable
        se lee directamente de memoria además mantiene la coherencia entre los distintos procesadores.
            https://www.youtube.com/watch?v=rZDgNYoo5zY private boolean;
        */

    public VariableCompartida(){
        v = -1;
        isThereAnyDataOutThere = false;
    }

    public  VariableCompartida(int x){
        v = x;
        isThereAnyDataOutThere = false;
    }

    public void inc(){
        v++;
    }

    public int getV(){
        while(!isThereAnyDataOutThere) { /*polling!*/ }
        /*
        *   while(!isThereAnyDataOutThere){ Thread.yield(); }
        * */
        isThereAnyDataOutThere = false;
        return v;
    }

    public void set(int x){
        while(isThereAnyDataOutThere) { /*polling!*/ }
        /*
        *   while(isThereAnyDataOutThere){ Thread.yield(); }
        * */
        isThereAnyDataOutThere = true;
        v = x;
    }
}
