package exercise2;

import java.util.concurrent.Semaphore;

public class AssemblyLine {
    public static final int NUM_TIPOS = 3;

    //Cantidad total de paquetes procesados
    private volatile int numPaquetes = 0;
    private int[] cinta;
    /*hacen falta sem�foros para las condiciones de sincronizaci�n y exclusi�n mutua*/
    private Semaphore packagers[];
    private Semaphore setter;
    private Semaphore mutex;


    public AssemblyLine(int longitud) {
        cinta = new int[longitud];
        for (int i = 0; i < longitud; i++)
        cinta[i] = -1;
    //dar valores iniciales a sem�foros
        packagers = new Semaphore[NUM_TIPOS];
        for(int i = 0; i < NUM_TIPOS; i++){
            packagers[i] = new Semaphore(0, false);
        }
        setter = new Semaphore(NUM_TIPOS, false);
        mutex = new Semaphore(1, true);
    }

    public void colocar(int tipo) throws InterruptedException {
        //el productor debe esperar si no hay sitio, colocar el elemento en la cinta en exlcusi�n mutua,
        // y avisar al consumidor del tipo correspondiente
        setter.acquire();
        mutex.acquire();
        //c�digo de ejemplo para colocar el producto en la cinta
        int i = 0;
        while (i < cinta.length && cinta[i] != -1) i++;
        cinta[i] = tipo;
        System.out.println("El robot colocador coloca un producto de tipo " + tipo + " en la cinta");
        mutex.release();
        packagers[tipo].release();
    }

    public void cogerProducto(int tipo) throws InterruptedException {
        // el consumidor debe esperarse si no hay nuevos productos de su tipo, en exclusi�n mutua acceder a la cinta,
        // y avisar al productor de que hay un espacio libre
        packagers[tipo].acquire();
        mutex.acquire();
        //c�digo de ejemplo para sacar el producto de la cinta
        int i = 0;
        while (i < cinta.length && cinta[i] != tipo) i++;
        cinta[i] = -1;
        System.out.println("El robot empaquetador " + tipo + " empaqueta y quita un producto de la lista");
        mutex.release();
        setter.release();
    }

    public void empaquetado(int tipo) throws InterruptedException {
        //el consumidor debe incrementar numPaquetes en exclusi�n mutua con otros consumidores
        mutex.acquire();
        //c�digo de ejemplo incrementar la variable
        numPaquetes++;
        System.out.println("Número de paquetes incrementado a " + numPaquetes);
        mutex.release();
    }

}
