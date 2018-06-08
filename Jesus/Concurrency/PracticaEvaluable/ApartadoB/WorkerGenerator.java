import javax.swing.*;
import java.util.List;
import java.util.Random;

public class WorkerGenerator extends SwingWorker<Integer, Integer> {
    private Random rnd = new Random();
    private int number;
    private Panel panel;

    public WorkerGenerator(int number, Panel panel) {
        this.number = number;
        this.panel = panel;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        int nextInt = 0, i = 0;

        try {
            while (i < panel.number() && !isCancelled()) {
                nextInt = rnd.nextInt(100) + 1;
                //this.publish(nextInt);
                i++;
                Thread.sleep(50);
            }
        } catch (InterruptedException | ClassCastException e) {
            panel.mensaje("TAREA CANCELADA");
        }
        return nextInt;
    }

    /*@Override
    protected void process(List<Integer> list) {
        try {
            int number1 = this.doInBackground();
            int number2 = this.doInBackground();
            panel.writeNumbersPairs1(number1);
            panel.writeNumbersPairs2(number2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
