package sleepybarber;

public class Main {
    public static void main(String[] args) {
        Barbershop barbershop = new Barbershop();
        SleepyBarber barber = new SleepyBarber(barbershop);
        Client client = new Client(barbershop);
        barber.start();
        client.start();
        try {
            barber.join();
            client.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
