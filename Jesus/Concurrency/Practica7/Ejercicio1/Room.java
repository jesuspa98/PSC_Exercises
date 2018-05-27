import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Room {
    private String[] nom = {"A", "B"};
    private int[] dentro = {0, 0};
    private int[] hanPasado = {0, 0};
    private Lock lock = new ReentrantLock();
    //A tipo == 0
    //B tipo == 1
    private static final int[] NECESITO = {2, 1};
    private Condition[] espera = {lock.newCondition(), lock.newCondition()};

    public void entrar(int id, int tipo) throws InterruptedException {
        int otroTipo = (tipo + 1) % 2;
        lock.lock();

        try {
            System.out.println("Proceso + " + id + " de tipo " + nom[tipo] + "ENTRA");
            dentro[tipo]++;
            hanPasado[tipo]++;
            int heVisto = dentro[otroTipo];
            int heVistoInicialmente = heVisto;
            int hanPasado_inicialmente = hanPasado[otroTipo];
            while (heVisto < NECESITO[tipo]) {
                espera[tipo].await();
                heVisto = heVistoInicialmente + (hanPasado[otroTipo] - hanPasado_inicialmente);

            }

            dentro[tipo]--;
            espera[otroTipo].signalAll();
        } finally {
            lock.unlock();
        }
    }
}
