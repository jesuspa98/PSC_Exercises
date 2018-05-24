import java.util.concurrent.Semaphore;

public class Bridge {
    private final static int EMPTY_BRIDGE = -1;
    private final static int LIMIT = 3;
    private int []esperando = {0,0};
    private int en_puente = 0;
    private int han_pasado = 0;
    private int [] count = {0, 0};
    private int actualDirection = EMPTY_BRIDGE;
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore []espera = {new Semaphore(0, true), new Semaphore(1, true)};


    public void getIn(int sentido, int numCoche) throws InterruptedException {
        boolean wait = false;
        int otroSentido = (sentido+1)%2;
        mutex.acquire();
        System.out.println("Coche " + numCoche + " quiere entrar en sentido " + sentido);
        if(actualDirection != sentido || (actualDirection == sentido &&
                esperando[otroSentido] > 0 && han_pasado >= LIMIT)){
            wait = true;
            esperando[sentido]++;
        }else{
            actualDirection = sentido;
            en_puente++;
            han_pasado++;
        }
        count[sentido]++;
        if(actualDirection != sentido){
            wait = true;
        }else{
            actualDirection = sentido;
        }

        mutex.release();

        if(wait){
            espera[sentido].acquire();
            System.out.println("Coche " + numCoche + " se queda esperando en sentido " + sentido);
        }
        System.out.println("Coche " + numCoche + " consigue entrar en sentido " + sentido);

    }

    public void getOut(int sentido, int numCoche) throws InterruptedException {
        int otroSentido = (sentido+1)%2;
        mutex.acquire();
        en_puente--;
        if(en_puente == 0){
            if(esperando[otroSentido] > 0 && (han_pasado >= LIMIT)){
                actualDirection = otroSentido;
                en_puente = esperando[otroSentido];
                han_pasado = esperando[otroSentido];
                esperando[otroSentido] = 0;
                espera[otroSentido].release(en_puente);
            }else if (esperando[otroSentido] == 0){
                actualDirection = EMPTY_BRIDGE;
            }
        }
        count[sentido]--;
        System.out.println("Coche " + numCoche + " sale en sentido " + sentido);


        if(count[sentido] == 0){
            if(count[otroSentido] == 0){
                actualDirection = EMPTY_BRIDGE;
            }else{
                actualDirection = otroSentido;
                espera[sentido].release(count[otroSentido]);
            }
        }

        mutex.release();
    }
}
