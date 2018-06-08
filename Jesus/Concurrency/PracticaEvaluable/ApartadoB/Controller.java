import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ActionListener{
    private Panel panel;
    private WorkerGenerator workerGenerator1 = null;
    private WorkerGenerator workerGenerator2 = null;
    private WorkerSearcher workerSearcher = new WorkerSearcher();

    public Controller(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ENTER")) {
            panel.clear();
            workerGenerator1 = new WorkerGenerator(panel.number(), panel);
            workerGenerator2 = new WorkerGenerator(panel.number(), panel);
            workerGenerator1.execute();
            workerGenerator2.execute();
            workerSearcher.execute();
            try {
                List<Integer> list1 = new ArrayList<>();
                List<Integer> list2 = new ArrayList<>();

                for (int i = 0; i < panel.number(); i++){
                    int number1 = workerGenerator1.doInBackground();
                    int number2 = workerGenerator2.doInBackground();
                    list1.add(number1);
                    list2.add(number2);
                    panel.writeNumbersPairs1(number1);
                    panel.writeNumbersPairs2(number2);
                }

                if (list1.size() == list2.size()) {
                    for (int i = 0; i < list1.size(); i++) {
                        workerSearcher.setNumber1(list1.get(i));
                        workerSearcher.setNumber2(list2.get(i));
                        String mensaje = workerSearcher.doInBackground();
                        panel.writeNumbers(mensaje, i);
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (e.getActionCommand().equals("CLEAR")) {
            panel.clear();
        } else {
            if (workerGenerator1 != null) {
                workerGenerator1.cancel(true);
            }
        }
    }
}
