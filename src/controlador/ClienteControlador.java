package controlador;

import controlador.DAO.ClienteDAO;
import modelo.Cliente;
import vista.ClienteVista;

public class ClienteControlador {

	private ClienteVista vista;
	private ClienteDAO dao;
	
	
	public ClienteControlador(ClienteVista vista, ClienteDAO dao) {
		this.vista = vista;
		this.dao = dao;
	}

	public void iniciar() {
		vista.setVisible(true);
	}


//TODO: Implementar los métodos CRUD
	public void crearCliente() {}
	public void leerCliente() {}
	public void actualizarCliente() {}
	public void eliminarCliente() {}
	
}
