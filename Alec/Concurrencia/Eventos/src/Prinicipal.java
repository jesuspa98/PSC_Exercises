import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

public class Prinicipal {

	public static void crearGUI(JFrame ventana) {
		Panel panel = new Panel();
		Controller controller = new Controller(panel);
		panel.setController(controller);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setSize(575, 475);
	}

	public static void main(String[] args) {
		final JFrame ventana = new JFrame("Ternas Pintagórricas");
		SwingUtilities.invokeLater(() -> crearGUI(ventana));
	}
	
	

}
