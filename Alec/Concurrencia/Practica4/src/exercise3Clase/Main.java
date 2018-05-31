package exercise3Clase;

public class Main {
    public static void main(String[] args) {
        Table table = new Table();
        Smokers[] smo = { new Smokers(table, 0), new Smokers(table, 1), new Smokers(table, 2)};
        Agent agent = new Agent(table);
        smo[0].start();
        smo[1].start();
        smo[2].start();
        agent.start();
    }
}
