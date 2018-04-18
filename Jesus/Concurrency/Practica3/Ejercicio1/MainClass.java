import Print.Print;

public class MainClass{
    public static void main(String[] args) {
        Print thread1 = new Print(10, 'a');
        Print thread2 = new Print(5, 'E');
        Print thread3 = new Print(10, 'x');

        thread1.start();
        thread2.start();
        thread3.start();

        try{
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished");
    }
}
