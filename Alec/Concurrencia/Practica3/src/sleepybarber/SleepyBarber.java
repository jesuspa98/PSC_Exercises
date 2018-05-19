package sleepybarber;


public class SleepyBarber extends Thread{
    private Barbershop barbershop;

    public SleepyBarber(Barbershop b) {
        this.barbershop = b;
    }

    @Override
    public void run() {
        try {
            barbershop.waitFirstClient();
            while(true){
                barbershop.nextClient();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
