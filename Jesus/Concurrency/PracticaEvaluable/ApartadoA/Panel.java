import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import static java.awt.BorderLayout.CENTER;

public class Panel extends JPanel {
    private JLabel etiqueta = new JLabel("¿Longitud de la lista a generar de pares aleatorios?");
    private JTextField number = new JTextField(3);
    private JTextArea listaPares1 = new JTextArea(10, 20);
    private JScrollPane scroll1 = new JScrollPane(listaPares1);
    private JTextArea listaPares2 = new JTextArea(10, 20);
    private JScrollPane scroll2 = new JScrollPane(listaPares2);
    private JLabel etiquetaPares1 = new JLabel("Lista de naturales A");
    private JLabel etiquetaPares2 = new JLabel("Lista de naturales B");
    private JTextArea listaPares = new JTextArea(10, 50);
    private JScrollPane scroll = new JScrollPane(listaPares);
    private JLabel etiquetaPares = new JLabel("Comprobaciones de Ternas pitagóricas");
    private JButton clear = new JButton("Clear");
    private JButton submit = new JButton("Submit");

    public Panel() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        listaPares.setEditable(false);
        listaPares1.setEditable(false);
        listaPares2.setEditable(false);
        scroll.createVerticalScrollBar();
        scroll1.createVerticalScrollBar();
        scroll2.createVerticalScrollBar();
        scroll.setPreferredSize(new Dimension(400, 170));

        JPanel north = new JPanel();
        JPanel nortth = new JPanel();
        JPanel northh = new JPanel();
        JPanel center = new JPanel();
        JPanel south = new JPanel();
        JPanel east = new JPanel();
        JPanel west = new JPanel();

        //North
        north.setLayout(new BorderLayout());
        northh.setLayout(new BorderLayout());
        nortth.setLayout(new BorderLayout());

        nortth.add(etiqueta);
        nortth.add(BorderLayout.EAST, clear);
        north.add(BorderLayout.NORTH, nortth);
        northh.add(number);
        northh.add(BorderLayout.EAST, submit);
        north.add(BorderLayout.SOUTH, northh);
        northh.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        north.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        //Center
        //East
        east.setLayout(new BorderLayout());
        east.add(BorderLayout.NORTH, scroll2);
        east.add(BorderLayout.SOUTH, etiquetaPares2);

        //West
        west.setLayout(new BorderLayout());
        west.add(BorderLayout.NORTH, scroll1);
        west.add(BorderLayout.SOUTH, etiquetaPares1);

        center.setLayout(new BorderLayout());
        center.add(BorderLayout.EAST, east);
        center.add(BorderLayout.WEST, west);
        center.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        //South
        south.setLayout(new BorderLayout());
        south.add(BorderLayout.NORTH, scroll);
        south.add(BorderLayout.SOUTH, etiquetaPares);

        this.add(BorderLayout.NORTH, north);
        this.add(CENTER, center);
        this.add(BorderLayout.SOUTH, south);
    }

    public void controller(ActionListener controller) {
        number.addActionListener(controller);
        number.setActionCommand("ENTER");
        clear.addActionListener(controller);
        clear.setActionCommand("CLEAR");
        submit.addActionListener(controller);
        submit.setActionCommand("ENTER");
    }

    public int number() {
        return Integer.parseInt(number.getText());
    }

    public void writeNumbersPairs1(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int number = list.get(i);
            listaPares1.append("(" + i + ": " + number + ")" + "\n");
        }
    }

    public void writeNumbersPairs2(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int number = list.get(i);
            listaPares2.append("(" + i + ": " + number + ")" + "\n");
        }
    }

    public void writeNumbers(String mensaje, int number) {
        listaPares.append(number + ": " + mensaje + "\n");
    }


    public void mensaje(String mensaje) {
        listaPares.setText(mensaje);
    }

    public void clear() {
        listaPares.setText("");
        listaPares1.setText("");
        listaPares2.setText("");
    }
}
