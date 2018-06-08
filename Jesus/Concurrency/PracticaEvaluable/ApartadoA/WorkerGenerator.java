import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkerGenerator extends SwingWorker<List<Integer>, Integer> {
    private Random rnd = new Random();
    private int number;
    private Panel panel;

    public WorkerGenerator(int number, Panel panel) {
        this.number = number;
        this.panel = panel;
    }

    @Override
    protected List<Integer> doInBackground() throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        int i = 0;

        try {
            while (i < number && !isCancelled()) {
                int nextInt = rnd.nextInt(100) + 1;
                list.add(nextInt);
                i++;
                Thread.sleep(50);
            }
        } catch (InterruptedException | ClassCastException e) {
            panel.mensaje("TAREA CANCELADA");
        }

        return list;
    }
}
