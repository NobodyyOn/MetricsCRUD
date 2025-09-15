package controlador;

import DAO.ClienteDAOimpl;
import modelo.Cliente;
import vista.ClienteVista;

import java.util.Random;
import java.util.List;

public class ClienteControlador {
	private boolean modoEdicion = false;
	private int idEdicion = -1;

	private ClienteVista vista;
	private ClienteDAOimpl clienteDaoImpl;
	private Cliente cliente;


	public ClienteControlador(ClienteVista vista, ClienteDAOimpl clienteDaoImpl) {
		this.vista = vista;
		this.clienteDaoImpl = clienteDaoImpl;
		
		vista.addCrearClienteListener(e -> {
			modoEdicion = false;
			idEdicion = -1;
			vista.setNombre("");
			vista.setTelefono("");
			vista.setDireccion("");
			vista.mostrarDialogo(false); // Mostrar formulario para crear
		});

		vista.addActualizarClienteListener(e -> {
			int id = vista.getIdSeleccionado();
			if(id == -1) {
				// Si no hay selección, intentar con el campo de búsqueda
				id = vista.getIdBusqueda();
				if(id == -1) {
					vista.mostrarPopup("Debe seleccionar un cliente en la tabla o ingresar un ID válido");
					return;
				}
			}
			// Buscar el cliente y mostrar el formulario
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
		});

		// Listener para el botón Guardar del formulario
		vista.addGuardarClienteListener(e -> {
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
		});
        // (Eliminado: la lógica de validación y actualización se maneja en el listener de Guardar y en el formulario)

		vista.addLeerClienteListener(e -> buscarCliente(vista.getIdBusqueda()));
        // (Eliminado: la lógica de actualización se maneja en el listener de Guardar)
		vista.addEliminarClienteListener(e -> {
			int id = vista.getIdSeleccionado();
			if(id == -1) {
				// Si no hay selección, intentar con el campo de búsqueda
				id = vista.getIdBusqueda();
				if(id == -1) {
					vista.mostrarPopup("Debe seleccionar un cliente en la tabla o ingresar un ID válido");
					return;
				}
			}
			eliminarCliente(id);
		});
		vista.addListarClientesListener(e -> listarClientes());
	}

	public void iniciar() {
		vista.setVisible(true);
	}

	public void buscarCliente(int idCliente) {
		
		try {
			cliente = clienteDaoImpl.buscar(idCliente);
			vista.mostrarClientes(List.of(cliente));
			System.out.println(cliente);
		} catch (IllegalArgumentException e) {
			vista.mostrarPopup("Cliente no encontrado");
		}
	}

	public void crearCliente(String nombre, String telefono, String direccion) {
		if (nombre == "" || telefono == "" || direccion == "") {
			vista.mostrarPopup("Nombre, teléfono y dirección no pueden estar vacíos");
			return;
		}

		Random random = new Random();
		int id = random.nextInt(1000) + 1;
		clienteDaoImpl.insertar(new Cliente(id, nombre, telefono, direccion));
		vista.mostrarPopup("Cliente creado con ID: " + id);
	}


	public void actualizarCliente(int idCliente, String nombre, String telefono, String direccion) {
		if (idCliente == -1 || idCliente <= 0) {
			vista.mostrarPopup("El ID del cliente debe ser mayor que cero");
			return;
		}
		clienteDaoImpl.actualizar(new Cliente(idCliente, nombre, telefono, direccion));
		cliente = clienteDaoImpl.buscar(idCliente);
		vista.mostrarPopup("Cliente actualizado");
	}

	public void eliminarCliente(int idCliente) {
		if(idCliente <= 0) {
			vista.mostrarPopup("El ID del cliente debe ser mayor que cero");
			return;
		}
		try {
			clienteDaoImpl.eliminar(idCliente);
			vista.mostrarPopup("Cliente eliminado");
		} catch (IllegalArgumentException e) {
			vista.mostrarPopup("Cliente no encontrado");
			return;
		}
	}

	public void listarClientes() {
		try {
			List<Cliente> clientes = clienteDaoImpl.listar();
			if(clientes.isEmpty()) {
				vista.mostrarPopup("No hay clientes para mostrar");
				return;
			}
			vista.mostrarClientes(clientes);
		} catch (IllegalArgumentException e) {
			vista.mostrarPopup("No hay clientes para mostrar");
			return;
		}
		
	}

}
