package vista;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Ventana principal con botones que permiten abrir la interfaz de clientes
 * o la de productos.
 */
public class MenuGeneral extends JFrame {

    public JButton btnClientes, btnProductos;

    public MenuGeneral() {
        super("Menú General");

        // Configurar ventana
        setSize(300, 200);
        setLocationRelativeTo(null); // centrar en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel con diseño de dos filas para colocar los botones
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));

        this.btnClientes = new JButton("Clientes");
        this.btnProductos = new JButton("Productos");

        // Añadir botones al panel
        panel.add(btnClientes);
        panel.add(btnProductos);

        // Añadir panel a la ventana
        getContentPane().add(panel);
    }

    public void addIrClientes(ActionListener listener) {
        btnClientes.addActionListener(listener);
    }

    public void addIrProductos(ActionListener listener) {
        btnProductos.addActionListener(listener);
    }

}
