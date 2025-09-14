package controlador;

import DAO.ClienteDAO;
import modelo.Cliente;
import vista.ClienteVista;

public class ClienteControlador {

	private ClienteVista vista;
	private ClienteDAO dao;

	@SuppressWarnings("unused")
	public ClienteControlador(ClienteVista vista, ClienteDAO dao) {
		this.vista = vista;
		this.dao = dao;
		vista.addCrearClienteListener(e -> crearCliente());
		vista.addLeerClienteListener(e -> leerCliente());
		vista.addActualizarClienteListener(e -> actualizarCliente());
		vista.addEliminarClienteListener(e -> eliminarCliente());
		vista.addListarClientesListener(e -> listarClientes());
	}

	public void iniciar() {
		vista.setVisible(true);
	}

	// TODO: Implementar los métodos CRUD
	public void crearCliente() {
	}

	public void leerCliente() {
	}

	public void actualizarCliente() {
	}

	public void eliminarCliente() {
	}

	public void listarClientes() {
	}

}
