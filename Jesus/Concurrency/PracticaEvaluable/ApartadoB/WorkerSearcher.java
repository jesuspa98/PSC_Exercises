import javax.swing.*;
import java.util.List;

public class WorkerSearcher extends SwingWorker<Void, String> {
    private WorkerGenerator workerGenerator1;
    private WorkerGenerator workerGenerator2;
    private Panel panel;
    private char aChar;
    private int leidos;

    public WorkerSearcher(WorkerGenerator workerGenerator1, WorkerGenerator workerGenerator2, Panel panel, char aChar) {
        this.workerGenerator1 = workerGenerator1;
        this.workerGenerator2 = workerGenerator2;
        this.panel = panel;
        this.aChar = aChar;
        leidos = 0;

    }

    protected Void doInBackground() throws Exception {

        int number1, number2, veces = workerGenerator1.getVeces();
        double pit;
        while (leidos < veces) {
            number1 = workerGenerator1.getElemento(leidos);
            number2 = workerGenerator2.getElemento(leidos);
            pit = Math.pow(number1, 2) + Math.pow(number2, 2);
            pit = Math.sqrt(pit);

            if ((pit % 1) == 0) {
                publish("TERNA PITAGORICA!!! " + number1 + "^2 + " + number2 + "^2 == " + pit);
            } else {
                publish(number1 + "^2 + " + number2 + "^2 == " + pit);
            }

            leidos++;
        }

        return null;

    }

    public void process(List<String> p) {
        for (String string : p) {
            panel.addList(string, aChar);
        }
    }
}