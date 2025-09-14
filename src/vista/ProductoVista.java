
package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ProductoVista extends JFrame {

    public JButton btnCrearProducto, btnLeerProducto, btnListarProductos, btnBorrarProducto, btnActualizarProducto;

    public ProductoVista() {
        super("Producto");

        // Configurar ventana
        setSize(600, 300);
        setLocationRelativeTo(null); // centrar en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel con diseño de dos filas para colocar los botones
        JPanel panel = new JPanel(new GridLayout(1, 4, 10, 450));

        this.btnCrearProducto = new JButton("Crear P");
        this.btnLeerProducto = new JButton("Leer P");
        this.btnActualizarProducto = new JButton("Actualizar P");
        this.btnBorrarProducto = new JButton("Borrar P");
        this.btnListarProductos = new JButton("Listar P");

        // Asignar acciones a los botones
        btnCrearProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Crear Producto");
            }
        });
        // Asignar acciones a los botones
        btnLeerProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Leer Producto");
            }
        });
        // Asignar acciones a los botones
        btnActualizarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Actualizar Producto");
            }
        });
        // Asignar acciones a los botones
        btnBorrarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Borrar Producto");
            }
        });

        // Añadir botones al panel
        panel.add(btnCrearProducto);
        panel.add(btnLeerProducto);
        panel.add(btnActualizarProducto);
        panel.add(btnBorrarProducto);
        panel.add(btnListarProductos);

        // Añadir panel a la ventana
        getContentPane().add(panel);
    }

    public void addCrearProductoListener(ActionListener listener) {
        btnCrearProducto.addActionListener(listener);
    }

    public void addLeerProductoListener(ActionListener listener) {
        btnLeerProducto.addActionListener(listener);
    }

    public void addActualizarProductoListener(ActionListener listener) {
        btnActualizarProducto.addActionListener(listener);
    }

    public void addEliminarProductoListener(ActionListener listener) {
        btnBorrarProducto.addActionListener(listener);
    }

    public void addListarProductosListener(ActionListener listener) {
        btnListarProductos.addActionListener(listener);
    }
}
