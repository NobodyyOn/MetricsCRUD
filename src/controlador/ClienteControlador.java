package controlador;

import DAO.ClienteDAOimpl;
import modelo.Cliente;
import vista.ClienteVista;
import java.util.Random;

public class ClienteControlador {

	private ClienteVista vista;
	private ClienteDAOimpl clienteDaoImpl;
	private Cliente cliente;

	@SuppressWarnings("unused")
	public ClienteControlador(ClienteVista vista, ClienteDAOimpl clienteDaoImpl) {
		this.vista = vista;
		this.clienteDaoImpl = clienteDaoImpl;
		
		//TODO: REEMPLAZAR POR INPUT
		vista.addCrearClienteListener(e -> crearCliente(String.valueOf("nombre"), String.valueOf("telefono"), String.valueOf("direccion")));
		vista.addLeerClienteListener(e -> buscarCliente(Integer.parseInt("220")));
		vista.addActualizarClienteListener(e -> actualizarCliente(Integer.parseInt("220"), String.valueOf("nombreNuevo"), String.valueOf("telefono"), String.valueOf("direccion")));
		vista.addEliminarClienteListener(e -> eliminarCliente(Integer.parseInt("82")));
		vista.addListarClientesListener(e -> listarClientes());
	}

	public void iniciar() {
		vista.setVisible(true);
	}

	public void buscarCliente(int idCliente) {
		// if( idCliente <= 0) {
		// 	throw new IllegalArgumentException("El ID del cliente debe ser mayor que cero");
		// }
		cliente = clienteDaoImpl.seleccionar(idCliente);
		System.out.println(cliente.getNombre());
	}

	public void crearCliente(String nombre, String telefono, String direccion) {
		if (nombre == null || telefono == null || direccion == null) {
			throw new IllegalArgumentException("El nombre, teléfono y dirección no pueden estar vacíos");
		}

		Random random = new Random();
		int id = random.nextInt(1000) + 1;
		clienteDaoImpl.insertar(new Cliente(id, nombre, telefono, direccion));
	}


	public void actualizarCliente(int idCliente, String nombre, String telefono, String direccion) {
		if (idCliente <= 0) {
			throw new IllegalArgumentException("El ID del cliente debe ser mayor que cero");
		}
		clienteDaoImpl.actualizar(new Cliente(idCliente, nombre, telefono, direccion));
		cliente = clienteDaoImpl.seleccionar(idCliente);
	}

	public void eliminarCliente(int idCliente) {
		if(idCliente <= 0) {
			throw new IllegalArgumentException("El ID del cliente debe ser mayor que cero");
		}
		clienteDaoImpl.eliminar(idCliente);
	}

	public void listarClientes() {
		clienteDaoImpl.listar();
	}

}
