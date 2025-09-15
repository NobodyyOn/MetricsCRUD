package controlador;

import DAO.ClienteDAO;
import modelo.Cliente;
import vista.ClienteVista;

import java.util.Random;
import java.util.List;

public class ClienteControlador {
    private boolean modoEdicion = false;
    private int idEdicion = -1;

    private ClienteVista vista;
    private ClienteDAO clienteDaoImpl;
    private Cliente cliente;

    public ClienteControlador(ClienteVista vista, ClienteDAO clienteDaoImpl) {
        this.vista = vista;
        this.clienteDaoImpl = clienteDaoImpl;

        inicializarListeners();
    }

    @SuppressWarnings("unused")
	private void inicializarListeners() {
        vista.addCrearClienteListener(e -> mostrarFormularioCrear());
        vista.addActualizarClienteListener(e -> mostrarFormularioActualizar());
        vista.addGuardarClienteListener(e -> guardarCliente());
        vista.addLeerClienteListener(e -> buscarCliente(vista.getIdBusqueda()));
        vista.addEliminarClienteListener(e -> eliminarClienteDesdeVista());
        vista.addListarClientesListener(e -> listarClientes());
    }

    private void mostrarFormularioCrear() {
        modoEdicion = false;
        idEdicion = -1;
        vista.setNombre("");
        vista.setTelefono("");
        vista.setDireccion("");
        vista.mostrarDialogo(false); // Mostrar formulario para crear
    }

    private void mostrarFormularioActualizar() {
        int id = vista.getIdSeleccionado();
        if (id == -1) {
            id = vista.getIdBusqueda();
            if (id == -1) {
                vista.mostrarPopup("Debe seleccionar un cliente en la tabla o ingresar un ID válido");
                return;
            }
        }
        try {
            Cliente c = clienteDaoImpl.buscar(id);
            modoEdicion = true;
            idEdicion = id;
            vista.setNombre(c.getNombre());
            vista.setTelefono(c.getTelefono());
            vista.setDireccion(c.getDireccion());
            vista.mostrarDialogo(true); // Mostrar formulario para actualizar
        } catch (IllegalArgumentException ex) {
            vista.mostrarPopup("Cliente no encontrado");
        }
    }

    private void guardarCliente() {
        String nombre = vista.getNombre();
        String telefono = vista.getTelefono();
        String direccion = vista.getDireccion();
        if (nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            vista.mostrarPopup("Todos los campos son obligatorios");
            return;
        }
        if (modoEdicion && idEdicion != -1) {
            actualizarCliente(idEdicion, nombre, telefono, direccion);
        } else {
            crearCliente(nombre, telefono, direccion);
        }
        vista.cerrarDialogo();
    }

    private void eliminarClienteDesdeVista() {
        int id = vista.getIdSeleccionado();
        if (id == -1) {
            id = vista.getIdBusqueda();
            if (id == -1) {
                vista.mostrarPopup("Debe seleccionar un cliente en la tabla o ingresar un ID válido");
                return;
            }
        }
        eliminarCliente(id);
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    public void buscarCliente(int idCliente) {
        try {
            cliente = clienteDaoImpl.buscar(idCliente);
            vista.mostrarClientes(List.of(cliente));
        } catch (IllegalArgumentException e) {
            vista.mostrarPopup("Cliente no encontrado");
        }
    }

    public void crearCliente(String nombre, String telefono, String direccion) {
        if (nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            vista.mostrarPopup("Nombre, teléfono y dirección no pueden estar vacíos");
            return;
        }
        Random random = new Random();
        int id = random.nextInt(1000) + 1;
        try {
            clienteDaoImpl.insertar(new Cliente(id, nombre, telefono, direccion));
            this.listarClientes();
            vista.mostrarPopup("Cliente creado con ID: " + id);
        } catch (IllegalArgumentException ex) {
            vista.mostrarPopup(ex.getMessage());
        }
    }

    public void actualizarCliente(int idCliente, String nombre, String telefono, String direccion) {
        if (idCliente == -1 || idCliente <= 0) {
            vista.mostrarPopup("El ID del cliente debe ser mayor que cero");
            return;
        }
        try {
            clienteDaoImpl.actualizar(new Cliente(idCliente, nombre, telefono, direccion));
            //cliente = clienteDaoImpl.buscar(idCliente);
            this.buscarCliente(idCliente);
            vista.mostrarPopup("Cliente actualizado");
        } catch (IllegalArgumentException ex) {
            vista.mostrarPopup(ex.getMessage());
        }
    }

    public void eliminarCliente(int idCliente) {
        if (idCliente <= 0) {
            vista.mostrarPopup("El ID del cliente debe ser mayor que cero");
            return;
        }
        try {
            clienteDaoImpl.eliminar(idCliente);
            vista.mostrarPopup("Cliente eliminado");
            this.listarClientes();
        } catch (IllegalArgumentException e) {
            vista.mostrarPopup("Cliente no encontrado");
        }
    }

    public void listarClientes() {
        try {
            List<Cliente> clientes = clienteDaoImpl.listar();
            if (clientes.isEmpty()) {
                vista.mostrarPopup("No hay clientes para mostrar");
                return;
            }
            vista.mostrarClientes(clientes);
        } catch (IllegalArgumentException e) {
            vista.mostrarPopup("No hay clientes para mostrar");
        }
    }
}
