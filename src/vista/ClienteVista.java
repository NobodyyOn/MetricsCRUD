
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

        // Asignar acciones a los botones
        btnCrearUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Crear Usuario");
            }
        });
        // Asignar acciones a los botones
        btnLeerUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Leer Usuario");
            }
        });
        // Asignar acciones a los botones
        btnActualizarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Actualizar Usuario");
            }
        });
        // Asignar acciones a los botones
        btnBorrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Borrar Usuario");
            }
        });


        // Añadir botones al panel
        panel.add( btnCrearUsuario);
        panel.add( btnLeerUsuario);
        panel.add( btnActualizarUsuario);
        panel.add( btnBorrarUsuario );

        // Añadir panel a la ventana
        getContentPane().add(panel);
    }
}
