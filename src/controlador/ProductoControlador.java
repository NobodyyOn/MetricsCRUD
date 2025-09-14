
package controlador;

import DAO.ProductoDAO;
import vista.ProductoVista;

public class ProductoControlador {
	private ProductoVista vista;
	private ProductoDAO dao;

	@SuppressWarnings("unused")
	public ProductoControlador(ProductoVista vista, ProductoDAO dao) {
		this.vista = vista;
		this.dao = dao;
		vista.addCrearProductoListener(e -> crearProducto());
		vista.addLeerProductoListener(e -> leerProducto());
		vista.addActualizarProductoListener(e -> actualizarProducto());
		vista.addEliminarProductoListener(e -> eliminarProducto());
		vista.addListarProductosListener(e -> listarProductos());
	}

	public void iniciar() {

	}
	// TODO: Implementar los métodos CRUD

	public void crearProducto() {
	}

	public void leerProducto() {
	}

	public void actualizarProducto() {
	}

	public void eliminarProducto() {
	}

	public void listarProductos() {
	}

}
