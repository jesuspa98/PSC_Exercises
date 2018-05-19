package sleepybarber;

public class Client extends Thread {
    private Barbershop barbershop;
    public Client(Barbershop b) {
        this.barbershop = b;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            try {
                barbershop.newClient();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
