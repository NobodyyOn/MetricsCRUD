package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
    import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import modelo.Cliente;

public class ClienteVista extends JFrame {
    // Componentes de la barra de búsqueda y acciones
    private final JTextField txtBuscar;
    private final JButton btnLeer;      // Buscar
    private final JButton btnCrear;     // Crear
    private final JButton btnActualizar; // Actualizar (edición)
    private final JButton btnEliminar;   // Eliminar
    private final JButton btnListar;     // Listar todos

    // Tabla y modelo para mostrar clientes
    private final JTable tablaClientes;
    private final DefaultTableModel modeloTabla;

    // Mensaje de estado
    private final JLabel lblMensaje;

    // Diálogo para crear/editar clientes
    private final JDialog dialogo;
    private final JTextField txtNombre;
    private final JTextField txtTelefono;
    private final JTextField txtDireccion;
    private final JButton btnGuardar;
    private final JButton btnCancelar;

    @SuppressWarnings("unused")
    public ClienteVista() {
        super("Administración de Clientes");
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

        // Tabla de clientes
        String[] columnas = {"ID", "Nombre", "Teléfono", "Dirección"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaClientes = new JTable(modeloTabla);
        tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollTabla = new JScrollPane(tablaClientes);

        // Mensaje de estado
        lblMensaje = new JLabel(" ");
        lblMensaje.setForeground(new Color(0, 128, 0));
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);

        // Composición de la ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        add(lblMensaje, BorderLayout.SOUTH);

        // Diálogo de alta/edición
        dialogo = new JDialog(this, "Datos del cliente", true);
        dialogo.setSize(400, 260);
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
        dialogo.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        txtTelefono = new JTextField(20);
        dialogo.add(txtTelefono, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        dialogo.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        txtDireccion = new JTextField(20);
        dialogo.add(txtDireccion, gbc);

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
            txtTelefono.setText("");
            txtDireccion.setText("");
        }
        dialogo.setTitle(edicion ? "Editar Cliente" : "Crear Cliente");
        dialogo.setVisible(true);
    }
    public void cerrarDialogo() {
        dialogo.setVisible(false);
    }

    // Métodos para actualizar la tabla y el mensaje
    public void mostrarClientes(List<Cliente> clientes) {
        modeloTabla.setRowCount(0);
        for (Cliente c : clientes) {
            modeloTabla.addRow(new Object[]{c.getId(), c.getNombre(), c.getTelefono(), c.getDireccion()});
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
        int fila = tablaClientes.getSelectedRow();
        return fila >= 0 ? (int) modeloTabla.getValueAt(fila, 0) : -1;
    }
    public String getNombre() { return txtNombre.getText().trim(); }
    public String getTelefono() { return txtTelefono.getText().trim(); }
    public String getDireccion() { return txtDireccion.getText().trim(); }
    public void setNombre(String nombre) { txtNombre.setText(nombre); }
    public void setTelefono(String telefono) { txtTelefono.setText(telefono); }
    public void setDireccion(String direccion) { txtDireccion.setText(direccion); }

    // Métodos para el controlador original (compatibilidad)
    public void addCrearClienteListener(ActionListener listener) {
        btnCrear.addActionListener(listener);
    }
    public void addLeerClienteListener(ActionListener listener) {
        // Para compatibilidad: este listener se asigna al botón de búsqueda
        btnLeer.addActionListener(listener);
    }
    public void addActualizarClienteListener(ActionListener listener) {
        // Asignamos el listener al botón de actualizar
        btnActualizar.addActionListener(listener);
    }
    public void addEliminarClienteListener(ActionListener listener) {
        btnEliminar.addActionListener(listener);
    }
    public void addListarClientesListener(ActionListener listener) {
        btnListar.addActionListener(listener);
    }

    // Listener específico para el botón Guardar del diálogo
    public void addGuardarClienteListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    // Posibilidad de añadir un escuchador de doble clic en la tabla para edición
    public void addEditarClienteListener(java.awt.event.MouseListener listener) {
        tablaClientes.addMouseListener(listener);
    }
}
