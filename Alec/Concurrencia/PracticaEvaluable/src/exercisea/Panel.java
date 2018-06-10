package exercisea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class Panel extends JPanel {
    private Controller controller;
    private JTextArea listaPares = new JTextArea(10, 50);
    private JScrollPane scroll = new JScrollPane(listaPares);
    private JScrollPane scroll1 = new JScrollPane();
    private JScrollPane scroll2 = new JScrollPane();

    private JLabel etiqueta = new JLabel("cu�ntos amigos quieres?");
    private JLabel mensaje = new JLabel("GUI Creada");
    private JTextField numero = new JTextField(3);

    private JTextArea listaAmigos = new JTextArea(10,40);
    private JScrollPane scroll3 = new JScrollPane(listaAmigos);
    private JButton fin = new JButton("Cancelar");


    public Panel() {
        this.setLayout(new GridLayout());
        this.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 5));
        scroll.createVerticalScrollBar();
        scroll1.createVerticalScrollBar();
        scroll2.createVerticalScrollBar();
        scroll.setPreferredSize(new Dimension(400, 170));
        // codigo amigos
        this.setLayout(new BorderLayout());
        JPanel norte = new JPanel();
        norte.add(etiqueta);norte.add(numero);
        norte.add(fin);
        this.add(BorderLayout.NORTH, norte);
        this.add(BorderLayout.CENTER,  scroll);
        this.add(BorderLayout.SOUTH,  mensaje);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void controlador(ActionListener ctr){

    }
    public int numero() {
        return 0;
    }

    public void limpiaArea(){

    }
    public void mensaje(String str){

    }

}
