import javax.swing.*;

public class FindWorker extends SwingWorker<String, String> {
    private int num1, num2;

    public FindWorker(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public FindWorker() {}

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    @Override
    protected String doInBackground() {
        double pitValue = Math.round(Math.sqrt(Math.pow(num2, 2) + Math.pow(num1, 2)));
        double notPitValue = Math.sqrt(Math.pow(num1, 2) + Math.pow(num2, 2));
        if (isPytagoric(num1, num2)) {
            return "TERNA PITAGORICA!!!! " + num1 + "^2 + " + num2 +
                    "^2 == " + pitValue;
        } else {
            return num1 + "^2 + " + num2 +
                    "^2 == " + notPitValue;
        }
    }

    private boolean isPytagoric(int num1, int num2) {
        double isPit = Math.sqrt(Math.pow(num1, 2) + Math.pow(num2, 2));
        return isPit == Math.round(isPit);
    }
}
