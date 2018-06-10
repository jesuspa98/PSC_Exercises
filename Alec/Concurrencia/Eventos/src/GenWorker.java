import javax.swing.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CancellationException;
public class GenWorker extends SwingWorker<java.util.List<Integer>,Integer>{
	private Random rnd = new Random();
	private int number;
	private Panel panel;

	public GenWorker(int number, Panel panel) {
		this.number = number;
		this.panel = panel;
	}

	@Override
	protected List<Integer> doInBackground() throws Exception {
		List<Integer> list = new ArrayList<>();
		int i = 0;
		try {
			while(i < number && !isCancelled()) {
				int nextInt = rnd.nextInt(100) + 1;
				list.add(nextInt);
				i++;
				Thread.sleep(50);
			}
		} catch(InterruptedException | ClassCastException e){
		    panel.message("Tarea cancelada!!!");
        }
		return list;
	}
}
