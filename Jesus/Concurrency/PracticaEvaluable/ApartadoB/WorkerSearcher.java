import javax.swing.*;

public class WorkerSearcher extends SwingWorker<String, String> {
    private int number1, number2;

    public WorkerSearcher(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public WorkerSearcher() {}

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    @Override
    protected String doInBackground() throws Exception {
        String mensaje = null;
        if (isPytagoric(number1, number2)) {
            mensaje =  "TERNA PITAGORICA!!!! " + number1 + "^2 + " + number2 +
                    "^2 == " + Math.round(Math.sqrt(Math.pow(number1, 2) + Math.pow(number2, 2)));
            //publish(mensaje);
            return mensaje;
        } else {
            mensaje =  number1 + "^2 + " + number2 +
                    "^2 == " + Math.sqrt(Math.pow(number1, 2) + Math.pow(number2, 2));
            publish(mensaje);
            return mensaje;
        }
    }

    private boolean isPytagoric(int number1, int number2) {
        double pit = Math.sqrt(Math.pow(number1, 2) + Math.pow(number2, 2));
        return pit == Math.round(pit);
    }
}
