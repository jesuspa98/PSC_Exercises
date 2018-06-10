import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.CENTER;

public class Panel extends JPanel {
    private int counter1 = 0, counter2 = 0, counter = 0;
    private JLabel etiqueta = new JLabel("¿Longitud de la lista a generar de pares aleatorios?");
    private JTextField number = new JTextField(3);
    private JTextArea listaPares1 = new JTextArea(10, 20);
    private JTextArea listaPares2 = new JTextArea(10, 20);
    private JScrollPane scroll1 = new JScrollPane(listaPares1);
    private JScrollPane scroll2 = new JScrollPane(listaPares2);
    private JLabel etiquetaPares1 = new JLabel("Lista de naturales A");
    private JLabel etiquetaPares2 = new JLabel("Lista de naturales B");
    private JTextArea listaPares = new JTextArea(10, 50);
    private JScrollPane scroll = new JScrollPane(listaPares);
    private JLabel etiquetaPares = new JLabel("Comprobaciones de Ternas pitagóricas");
    private JButton clear = new JButton("Clear");
    private JButton submit = new JButton("Submit");
    private JProgressBar progressBar = new JProgressBar(0);

    public Panel() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        listaPares.setEditable(false);
        listaPares1.setEditable(false);
        listaPares2.setEditable(false);
        scroll.createVerticalScrollBar();
        scroll1.createVerticalScrollBar();
        scroll2.createVerticalScrollBar();
        scroll1.remove(scroll1.getHorizontalScrollBar());
        scroll2.remove(scroll2.getHorizontalScrollBar());
        scroll.remove(scroll.getHorizontalScrollBar());
        progressBar.setStringPainted(true);

        JPanel north = new JPanel();
        JPanel nortth = new JPanel();
        JPanel northh = new JPanel();
        JPanel center = new JPanel();
        JPanel south = new JPanel();
        JPanel east = new JPanel();
        JPanel west = new JPanel();
        JPanel south2 = new JPanel();

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
        south2.setLayout(new BorderLayout());
        south.add(BorderLayout.NORTH, scroll);
        south2.add(BorderLayout.NORTH, etiquetaPares);
        south2.add(BorderLayout.SOUTH, progressBar);
        south.add(BorderLayout.SOUTH, south2);

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

    public void message(String message) {
        listaPares.setText(message);
    }

    public JTextField getNumber() {
        return number;
    }

    public void clear() {
        listaPares1.setText("");
        listaPares2.setText("");
        listaPares.setText("");
        counter1 = counter2 = counter = 0;
        progressBar.setValue(0);
    }

    public int number() {
        progressBar.setMaximum(Integer.parseInt(number.getText()));
        return Integer.parseInt(number.getText());
    }

    public void addList(String string, char caracter) {
        if (caracter == 'A') {
            progressBar.setValue(counter1 + 1);
            counter1++;
            listaPares1.append("(" + counter1 + ": " + string + ")\n");
        } else if (caracter == 'B') {
            counter2++;
            listaPares2.append("(" + counter2 + ": " + string + ")\n");
        } else if (caracter == 'R') {
            counter++;
            listaPares.append(counter + ": " + string + "\n");
        }
    }

}