import javax.swing.*;
import java.util.List;

public class WorkerSearcher extends SwingWorker<String, String> {
    private int number1, number2;
    private Panel panel;
    private ListenerComprobadores listenerComprobadores;

    public WorkerSearcher(int number1, int number2, Panel panel) {
        this.number1 = number1;
        this.panel = panel;
        this.number2 = number2;
    }

    public WorkerSearcher() {
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    @Override
    protected String doInBackground() {
        double pitValue = Math.round(Math.sqrt(Math.pow(number2, 2) + Math.pow(number1, 2)));
        double notPitValue = Math.sqrt(Math.pow(number1, 2) + Math.pow(number2, 2));
        if (isPytagoric(number1, number2)) {
            publish("TERNA PITAGORICA!!!! " + number1 + "^2 + " + number2 + "^2 == " + pitValue);
            return "TERNA PITAGORICA!!!! " + number1 + "^2 + " + number2 +
                    "^2 == " + pitValue;
        } else {
            publish(number1 + "^2 + " + number2 + "^2 == " + notPitValue);
            return number1 + "^2 + " + number2 +
                    "^2 == " + notPitValue;
        }
    }

    private boolean isPytagoric(int num1, int num2) {
        double pit = Math.sqrt(Math.pow(num1, 2) + Math.pow(num2, 2));
        return pit == Math.round(pit);
    }

    @Override
    protected void process(List<String> chunks) {
        StringBuilder sb = new StringBuilder();

        for (String string : chunks) {
            sb.append(string);
        }

        this.listenerComprobadores.pasarInformacion(sb.toString());
    }

    public void setListener(ListenerComprobadores writeNumbers) {
        this.listenerComprobadores = writeNumbers;
    }

}