
package vista;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClienteVista extends JFrame {

    public JButton btnCrearUsuario, btnLeerUsuario, btnBorrarUsuario, btnActualizarUsuario;

    public ClienteVista() {
        super("Cliente");

        // Configurar ventana
        setSize(600, 300);
        setLocationRelativeTo(null); // centrar en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel con diseño de dos filas para colocar los botones
        JPanel panel = new JPanel(new GridLayout(1, 4, 10, 450));

        this.btnCrearUsuario = new JButton("Crear U");
        this.btnLeerUsuario = new JButton("leer U");
        this.btnActualizarUsuario = new JButton("Actualizar U");
        this.btnBorrarUsuario = new JButton("Borrar U");
        // Añadir botones al panel
        panel.add(btnCrearUsuario);
        panel.add(btnLeerUsuario);
        panel.add(btnActualizarUsuario);
        panel.add(btnBorrarUsuario);

        // Añadir panel a la ventana
        getContentPane().add(panel);
    }

    public void addCrearClienteListener(ActionListener listener) {
        btnCrearUsuario.addActionListener(listener);
    }

    public void addLeerClienteListener(ActionListener listener) {
        btnLeerUsuario.addActionListener(listener);
    }

    public void addActualizarClienteListener(ActionListener listener) {
        btnActualizarUsuario.addActionListener(listener);
    }

    public void addEliminarClienteListener(ActionListener listener) {
        btnBorrarUsuario.addActionListener(listener);
    }

    public void addListarClientesListener(ActionListener listener) {
        btnBorrarUsuario.addActionListener(listener);
    }
}
