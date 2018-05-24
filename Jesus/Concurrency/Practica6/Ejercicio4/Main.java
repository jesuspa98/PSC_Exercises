public class Main {
    public static void main(String[] args) {
        try {
            Control control = new Control(10);
            Thread[] tasks = new Thread[4];
            for (int i = 0; i < tasks.length; ++i) {
                tasks[i] = new Task(i, 5, control, 5);
                tasks[i].start();
            }
            for (int i = 0; i < tasks.length; ++i) {
                tasks[i].join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
