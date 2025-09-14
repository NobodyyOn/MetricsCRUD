
package vista;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClienteVista extends JFrame{


    public ClienteVista() {
        super("Cliente");

        // Configurar ventana
        setSize(600, 300);
        setLocationRelativeTo(null); // centrar en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel con diseño de dos filas para colocar los botones
        JPanel panel = new JPanel(new GridLayout(1,4,10,450));

        JButton btnCrearUsuario = new JButton("Crear U");
        JButton btnLeerUsuario = new JButton("leer U");
        JButton btnActualizarUsuario = new JButton("Actualizar U");
        JButton btnBorrarUsuario = new JButton("Borrar U");




        // Añadir botones al panel
        panel.add( btnCrearUsuario);
        panel.add( btnLeerUsuario);
        panel.add( btnActualizarUsuario);
        panel.add( btnBorrarUsuario );

        // Añadir panel a la ventana
        getContentPane().add(panel);
    }
}
