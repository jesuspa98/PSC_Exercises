import java.awt.event.*;
import java.util.List;

import javax.swing.*;
public class Controller implements ActionListener{

	private final Panel panel;
	private GenWorker workerGenerator1 = null;
	private GenWorker workerGenerator2 = null;
	private FindWorker findWorker = new FindWorker();

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("ENTER")) {
			workerGenerator1 = new GenWorker(panel.number(), panel);
			workerGenerator2 = new GenWorker(panel.number(), panel);
			workerGenerator1.execute();
			workerGenerator2.execute();
			findWorker.execute();
			try {
				List<Integer> list1 = workerGenerator1.get();
				List<Integer> list2 = workerGenerator2.get();
				panel.writeNaturalNumbersA(list1);
				panel.writeNaturalNumbersB(list2);

				if (list1.size() == list2.size()) {
					for (int i = 0; i < list1.size(); i++) {
						findWorker.setNum1(list1.get(i));
						findWorker.setNum2(list2.get(i));
						String mensaje = findWorker.doInBackground();
						panel.writeNumbers(mensaje, i);
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public Controller(Panel panel) {
		this.panel = panel;
	}
	
}
