import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private final Panel panel;
    private WorkerGenerator workerGenerator1 = null;
    private WorkerGenerator workerGenerator2 = null;
    private WorkerSearcher workerSearcher = new WorkerSearcher();

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("ENTER")) {
            workerGenerator1 = new WorkerGenerator(panel.number(), panel, 1);
            workerGenerator2 = new WorkerGenerator(panel.number(), panel, 2);
            workerGenerator1.setListenerGeneradores(panel::writeNaturalNumbersA);
            workerGenerator2.setListenerGeneradores(panel::writeNaturalNumbersB);
            workerGenerator1.setterListener(this::setNumbersFinder);
            workerGenerator2.setterListener(this::setNumbersFinder);
            workerSearcher.setListener(panel::writeNumbers);
            workerGenerator1.execute();
            workerGenerator2.execute();
            workerSearcher.execute();
        } else if (event.getActionCommand().equals("CLEAR")) {
            panel.clear();
        }
    }

    public Controller(Panel panel) {
        this.panel = panel;
    }

    public void setNumbersFinder(int num, int id) {
        if (id == 1) {
            workerSearcher.setNumber1(num);
        } else {
            workerSearcher.setNumber2(num);
        }
    }
}