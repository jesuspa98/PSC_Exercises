import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private final Panel panel;
    private WorkerGenerator workerGenerator1;
    private WorkerGenerator workerGenerator2;
    private WorkerSearcher workerSearcher;

    public Controller (Panel panel) {
        this.panel= panel;
    }
    public void actionPerformed (ActionEvent e) {
        if(e.getActionCommand().equals("ENTER")) {
            if(workerGenerator1 !=null) {
                workerGenerator1.cancel(true);
                workerGenerator2.cancel(true);
                workerSearcher.cancel(true);
            }

            panel.clear();
            workerGenerator1 = new WorkerGenerator(panel.number(),panel,'A');
            workerGenerator2 = new WorkerGenerator(panel.number(),panel,'B');
            workerSearcher = new WorkerSearcher(workerGenerator1, workerGenerator2, panel,'R');
            workerGenerator1.execute();
            workerGenerator2.execute();
            workerSearcher.execute();
        } else if (e.getActionCommand().equals("CLEAR")) {
            panel.clear();
            panel.getNumber().setText("");
        }
    }
}