
package vista;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;

public class ProductoVista extends JFrame {
    // Componentes de la barra de búsqueda y acciones
    private final JTextField txtBuscar;
    private final JButton btnLeer;      // Buscar
    private final JButton btnCrear;     // Crear
    private final JButton btnActualizar; // Actualizar (edición)
    private final JButton btnEliminar;   // Eliminar
    private final JButton btnListar;     // Listar todos

    // Tabla y modelo para mostrar productos
    private final JTable tablaProductos;
    private final DefaultTableModel modeloTabla;

    // Mensaje de estado
    private final JLabel lblMensaje;

    // Diálogo para crear/editar productos
    private final JDialog dialogo;
    private final JTextField txtNombre;
    private final JTextField txtPrecio;
    private final JTextField txtStock;
    private final JButton btnGuardar;
    private final JButton btnCancelar;

    public ProductoVista() {
        super("Administración de Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel superior: búsqueda y botones CRUD
        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));
        txtBuscar = new JTextField();
        txtBuscar.setToolTipText("Ingrese ID para buscar");
        btnLeer = new JButton("Buscar");
        btnCrear = new JButton("Crear");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");

        // Subpanel para búsqueda
        JPanel panelBusqueda = new JPanel(new BorderLayout(5, 5));
        panelBusqueda.add(new JLabel("ID:"), BorderLayout.WEST);
        panelBusqueda.add(txtBuscar, BorderLayout.CENTER);
        panelBusqueda.add(btnLeer, BorderLayout.EAST);

        // Subpanel para los botones CRUD
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        panelAcciones.add(btnCrear);
        panelAcciones.add(btnActualizar);
        panelAcciones.add(btnEliminar);
        panelAcciones.add(btnListar);

        panelSuperior.add(panelBusqueda, BorderLayout.CENTER);
        panelSuperior.add(panelAcciones, BorderLayout.EAST);

        // Tabla de productos
        String[] columnas = {"ID", "Nombre", "Precio", "Stock"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollTabla = new JScrollPane(tablaProductos);

        // Mensaje de estado
        lblMensaje = new JLabel(" ");
        lblMensaje.setForeground(new Color(0, 128, 0));
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);

        // Composición de la ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        add(lblMensaje, BorderLayout.SOUTH);

        // Diálogo de alta/edición
        dialogo = new JDialog(this, "Datos del producto", true);
        dialogo.setSize(400, 300);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos del diálogo
        gbc.gridx = 0; gbc.gridy = 0;
        dialogo.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        dialogo.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        dialogo.add(new JLabel("Precio ($):"), gbc);
        gbc.gridx = 1;
        txtPrecio = new JTextField(20);
        dialogo.add(txtPrecio, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        dialogo.add(new JLabel("Stock:"), gbc);
        gbc.gridx = 1;
        txtStock = new JTextField(20);
        dialogo.add(txtStock, gbc);

        // Botones del diálogo
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        JPanel panelBotonesDialogo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        panelBotonesDialogo.add(btnGuardar);
        panelBotonesDialogo.add(btnCancelar);
        dialogo.add(panelBotonesDialogo, gbc);

        // Acción de cancelar
        btnCancelar.addActionListener(e -> dialogo.setVisible(false));
    }

    public void mostrarPopup(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Métodos de interfaz del diálogo
    public void mostrarDialogo(boolean edicion) {
        if (!edicion) {
            txtNombre.setText("");
            txtPrecio.setText("");
            txtStock.setText("");
        }
        dialogo.setTitle(edicion ? "Editar Producto" : "Crear Producto");
        dialogo.setVisible(true);
    }
    
    public void cerrarDialogo() {
        dialogo.setVisible(false);
    }

    // Métodos para actualizar la tabla y el mensaje
    public void mostrarProductos(List<Producto> productos) {
        modeloTabla.setRowCount(0);
        for (Producto p : productos) {
            modeloTabla.addRow(new Object[]{p.getId(), p.getNombre(), 
                String.format("$%.2f", p.getPrecio()), p.getStock()});
        }
    }
    
    public void setMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }

    // Métodos para recuperar datos introducidos por el usuario
    public int getIdBusqueda() {
        String id = txtBuscar.getText().trim();
        if (id.isEmpty()) return -1;
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public int getIdSeleccionado() {
        int fila = tablaProductos.getSelectedRow();
        return fila >= 0 ? (int) modeloTabla.getValueAt(fila, 0) : -1;
    }
    
    public String getNombre() { 
        return txtNombre.getText().trim(); 
    }
    
    public double getPrecio() {
        try {
            return Double.parseDouble(txtPrecio.getText().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public int getStock() {
        try {
            return Integer.parseInt(txtStock.getText().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public void setNombre(String nombre) { 
        txtNombre.setText(nombre); 
    }
    
    public void setPrecio(double precio) { 
        txtPrecio.setText(String.valueOf(precio)); 
    }
    
    public void setStock(int stock) { 
        txtStock.setText(String.valueOf(stock)); 
    }

    // Métodos para el controlador
    public void addCrearProductoListener(ActionListener listener) {
        btnCrear.addActionListener(listener);
    }
    
    public void addLeerProductoListener(ActionListener listener) {
        btnLeer.addActionListener(listener);
    }
    
    public void addActualizarProductoListener(ActionListener listener) {
        btnActualizar.addActionListener(listener);
    }
    
    public void addEliminarProductoListener(ActionListener listener) {
        btnEliminar.addActionListener(listener);
    }
    
    public void addListarProductosListener(ActionListener listener) {
        btnListar.addActionListener(listener);
    }

    // Listener específico para el botón Guardar del diálogo
    public void addGuardarProductoListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    // Posibilidad de añadir un escuchador de doble clic en la tabla para edición
    public void addEditarProductoListener(java.awt.event.MouseListener listener) {
        tablaProductos.addMouseListener(listener);
    }
}
