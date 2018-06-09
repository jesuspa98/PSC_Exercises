import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenWorker extends SwingWorker<List<Integer>,Integer>{
	private Random rnd = new Random();
	private int number;
	private Panel panel;
	private Listener listener;

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
				publish(nextInt);
				i++;
				Thread.sleep(50);
			}
		} catch(InterruptedException | ClassCastException e){
		    panel.message("Tarea cancelada!!!");
        }
		return list;
	}

    @Override
    protected void process(List<Integer> chunks) {
        for(int number : chunks) {

        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
