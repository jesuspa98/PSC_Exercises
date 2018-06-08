import javax.swing.*;
import java.awt.*;

public class Principal {
    private static void crearGUI(JFrame ventana) {
        Panel panel = new Panel();
        Controller controller = new Controller(panel);
        panel.controller(controller);
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.setContentPane(panel);
        ventana.pack();
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setSize(580, 470);
    }

    public static void main(String[] args) {
        final JFrame ventana = new JFrame("Comprobar ternas pitagóricas");
        SwingUtilities.invokeLater(() -> crearGUI(ventana));
    }
}
