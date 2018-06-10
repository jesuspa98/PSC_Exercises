import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ActionListener{

	private final Panel panel;
	private GenWorker workerGenerator1 = null;
	private GenWorker workerGenerator2 = null;
	private FindWorker findWorker = new FindWorker();
	private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("ENTER")) {
			workerGenerator1 = new GenWorker(panel.number(), panel, 1);
			workerGenerator2 = new GenWorker(panel.number(), panel, 2);
            workerGenerator1.setListenerGeneradores(panel::writeNaturalNumbersA);
            workerGenerator2.setListenerGeneradores(panel::writeNaturalNumbersB);
            workerGenerator1.setterListener(this::setNumbersFinder);
            workerGenerator2.setterListener(this::setNumbersFinder);
            findWorker.setListener(panel::writeNumbers);
            workerGenerator1.execute();
			workerGenerator2.execute();
			findWorker.execute();
		}
	}

	public Controller(Panel panel) {
		this.panel = panel;
	}

	public void setNumbersFinder(int num, int id) {
	    if(id == 1) {
	        findWorker.setNum1(num);
        } else {
	        findWorker.setNum2(num);
        }
    }
}
