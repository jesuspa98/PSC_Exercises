import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class Panel extends JPanel{
	private int counter2;
	private int anotherCounter;
	private JLabel askingLabel = new JLabel("¿Longitud de la lista a generar de pares aleatorios?");
	private JTextField number = new JTextField(3);
	private JTextArea naturalList1 = new JTextArea(10, 20);
	private JTextArea naturalList2 = new JTextArea(10, 20);
	private JScrollPane scroll1 = new JScrollPane(naturalList1);
	private JScrollPane scroll2 = new JScrollPane(naturalList2);
	private JLabel naturalLabelA = new JLabel("Lista de naturales A");
	private JLabel naturalLabelB = new JLabel("Lista de naturales B");
	private JTextArea pitagoricTrio = new JTextArea(10, 50);
	private JScrollPane scroll = new JScrollPane(pitagoricTrio);
	private JLabel pitagoricLabel = new JLabel("Comprobaciones de Ternas pitagóricas");
    private int counter1;

    public Panel() {
		counter1 = 0;
		counter2 = 0;
		anotherCounter = 0;
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		pitagoricTrio.setEditable(false);
		naturalList1.setEditable(false);
		naturalList2.setEditable(false);
		scroll.createVerticalScrollBar();
		scroll1.createVerticalScrollBar();
		scroll2.createVerticalScrollBar();
		scroll.setPreferredSize(new Dimension(400, 170));
		// todos los paneles de cristo
		JPanel north = new JPanel();
		JPanel northLeft = new JPanel();
		JPanel northRight = new JPanel();
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		JPanel east = new JPanel();
		JPanel west = new JPanel();
		//Lo que es la parte de arriba. siempre arriba
		north.setLayout(new BorderLayout());
		northRight.setLayout(new BorderLayout());
		northLeft.setLayout(new BorderLayout());
		//Lo que es la parte de arriba 2
		northLeft.add(askingLabel);
		north.add(BorderLayout.NORTH, northLeft);
		northRight.add(number);
		north.add(BorderLayout.SOUTH, northRight);
		northRight.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		north.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		//derecha
		east.setLayout(new BorderLayout());
		east.add(BorderLayout.NORTH, scroll2);
		east.add(BorderLayout.SOUTH, naturalLabelB);
		//Izquierda siempre
		west.setLayout(new BorderLayout());
		west.add(BorderLayout.NORTH, scroll1);
		west.add(BorderLayout.SOUTH, naturalLabelA);
		center.setLayout(new BorderLayout());
		center.add(BorderLayout.EAST, east);
		center.add(BorderLayout.WEST, west);
		center.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		//Abao
		south.setLayout(new BorderLayout());
		south.add(BorderLayout.NORTH, scroll);
		south.add(BorderLayout.SOUTH, pitagoricLabel);
		// to compactado no pongo todo bien escrito porque pasa esto.
		this.add(BorderLayout.NORTH, north);
		this.add(BorderLayout.CENTER, center);
		this.add(BorderLayout.SOUTH, south);
	}

	public void writeNumbers(String message) {
		pitagoricTrio.setText(pitagoricTrio.getText() + anotherCounter + ": " + message + "\n");
		anotherCounter++;
	}

	public void message(String message) {
		pitagoricTrio.setText(message);
	}

	public void setController(ActionListener controller) {
		number.addActionListener(controller);
		number.setActionCommand("ENTER");
	}

	public int number() {
		return Integer.parseInt(number.getText());
	}

	public void writeNaturalNumbersA(List<Integer> list) {
		for(int number : list) {
			counter1++;
			naturalList1.setText(naturalList1.getText() + "(" + counter1 + ": " + number + ")" + "\n");
			//naturalList1.append("(" + counter + ": " + number + ")" + "\n");
		}
	}

	public void writeNaturalNumbersB(List<Integer> list) {
		for(int number : list) {
			counter2++;
			naturalList2.setText(naturalList2.getText() + "(" + counter2 + ": " + number + ")" + "\n");
			//naturalList2.append("(" + counter + ": " + number + ")" + "\n");
		}
	}

}
