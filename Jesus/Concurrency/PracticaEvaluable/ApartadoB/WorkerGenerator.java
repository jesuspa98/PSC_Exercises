import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkerGenerator extends SwingWorker<List<Integer>,Integer>{
    private Random rnd = new Random();
    private int number;
    private Panel panel;
    private ListenerGeneradores listenerGeneradores;
    private SetterListener uwu;
    private int generatedNum;
    private int id;
    private boolean isFirst;

    public WorkerGenerator(int number, Panel panel, int id) {
        this.number = number;
        this.panel = panel;
        this.id = id;
    }

    @Override
    protected List<Integer> doInBackground() throws Exception {
        panel.clear();
        List<Integer> list = new ArrayList<>();
        int i = 0;
        try {
            while(i < number && !isCancelled()) {
                isFirst = i == 0;
                generatedNum = rnd.nextInt(100) + 1;
                list.add(generatedNum);
                publish(generatedNum);
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
        this.listenerGeneradores.pasarInformacion(chunks);
        this.uwu.pasarNumeros(chunks.get(0), id);
    }

    public void setListenerGeneradores(ListenerGeneradores listenerGeneradores) {
        this.listenerGeneradores = listenerGeneradores;
    }

    public void setterListener(SetterListener uwu) {
        this.uwu = uwu;
    }
}