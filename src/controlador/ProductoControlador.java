
package controlador;

import controlador.DAO.ProductoDAO;
import vista.ProductoVista;

public class ProductoControlador {
	private ProductoVista vista;
	private ProductoDAO dao;

	public ProductoControlador(ProductoVista vista, ProductoDAO dao) {
		this.vista = vista;
		this.dao = dao;
	}

	public void iniciar() {

	}
	//TODO: Implementar los métodos CRUD

	public void crearProducto() {}
	public void leerProducto() {}
	public void actualizarProducto() {}
	public void eliminarProducto() {}
}
