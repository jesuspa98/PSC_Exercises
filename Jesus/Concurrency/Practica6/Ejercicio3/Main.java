public class Main {
    public static void main(String[] args) {
        try {
            Table table = new Table();
            Agent agent = new Agent(table);
            agent.start();
            Smoker[] smokers = new Smoker[Table.INGREDIENTS_NUMBER];
            for (int i = 0; i < smokers.length; ++i) {
                smokers[i] = new Smoker(table, i);
                smokers[i].start();
            }
            agent.join();
            for (int i = 0; i < smokers.length; ++i)
                smokers[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
