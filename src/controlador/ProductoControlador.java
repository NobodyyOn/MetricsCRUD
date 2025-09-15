
package controlador;

import DAO.ProductoDAO;
import java.util.List;
import java.util.Random;
import modelo.Producto;
import vista.ProductoVista;

public class ProductoControlador {
	private boolean modoEdicion = false;
	private int idEdicion = -1;

	private ProductoVista vista;
	private ProductoDAO productoDaoImpl;
	private Producto producto;

	public ProductoControlador(ProductoVista vista, ProductoDAO productoDaoImpl) {
		this.vista = vista;
		this.productoDaoImpl = productoDaoImpl;
		
		vista.addCrearProductoListener(e -> {
			modoEdicion = false;
			idEdicion = -1;
			vista.setNombre("");
			vista.setPrecio(0);
			vista.setStock(0);
			vista.mostrarDialogo(false); // Mostrar formulario para crear
		});

		vista.addActualizarProductoListener(e -> {
			int id = vista.getIdSeleccionado();
			if(id == -1) {
				// Si no hay selección, intentar con el campo de búsqueda
				id = vista.getIdBusqueda();
				if(id == -1) {
					vista.mostrarPopup("Debe seleccionar un producto en la tabla o ingresar un ID válido");
					return;
				}
			}
			// Buscar el producto y mostrar el formulario
			try {
				Producto p = productoDaoImpl.buscar(id);
				modoEdicion = true;
				idEdicion = id;
				vista.setNombre(p.getNombre());
				vista.setPrecio(p.getPrecio());
				vista.setStock(p.getStock());
				vista.mostrarDialogo(true); // Mostrar formulario para actualizar
			} catch (IllegalArgumentException ex) {
				vista.mostrarPopup("Producto no encontrado");
			}
		});

		// Listener para el botón Guardar del formulario
		vista.addGuardarProductoListener(e -> {
			String nombre = vista.getNombre();
			double precio = vista.getPrecio();
			int stock = vista.getStock();
			
			if (nombre.isEmpty()) {
				vista.mostrarPopup("El nombre es obligatorio");
				return;
			}
			if (precio <= 0) {
				vista.mostrarPopup("El precio debe ser mayor que cero");
				return;
			}
			if (stock < 0) {
				vista.mostrarPopup("El stock no puede ser negativo");
				return;
			}
			
			if (modoEdicion && idEdicion != -1) {
				actualizarProducto(idEdicion, nombre, precio, stock);
			} else {
				crearProducto(nombre, precio, stock);
			}
			vista.cerrarDialogo();
		});

		vista.addLeerProductoListener(e -> buscarProducto(vista.getIdBusqueda()));
		vista.addEliminarProductoListener(e -> {
			int id = vista.getIdSeleccionado();
			if(id == -1) {
				// Si no hay selección, intentar con el campo de búsqueda
				id = vista.getIdBusqueda();
				if(id == -1) {
					vista.mostrarPopup("Debe seleccionar un producto en la tabla o ingresar un ID válido");
					return;
				}
			}
			eliminarProducto(id);
		});
		vista.addListarProductosListener(e -> listarProductos());
	}

	public void iniciar() {
		System.out.println("Iniciando vista de productos...");
		vista.setVisible(true);
		System.out.println("Vista de productos mostrada");
	}

	public void buscarProducto(int idProducto) {
		try {
			producto = productoDaoImpl.buscar(idProducto);
			vista.mostrarProductos(List.of(producto));
		} catch (IllegalArgumentException e) {
			vista.mostrarPopup("Producto no encontrado");
		}
	}

	public void crearProducto(String nombre, double precio, int stock) {
		if (nombre.isEmpty()) {
			vista.mostrarPopup("El nombre no puede estar vacío");
			return;
		}
		if (precio <= 0) {
			vista.mostrarPopup("El precio debe ser mayor que cero");
			return;
		}
		if (stock < 0) {
			vista.mostrarPopup("El stock no puede ser negativo");
			return;
		}

		Random random = new Random();
		int id = random.nextInt(1000) + 1;
		productoDaoImpl.insertar(new Producto(id, nombre, precio, stock));
		vista.mostrarPopup("Producto creado con ID: " + id);
	}

	public void actualizarProducto(int idProducto, String nombre, double precio, int stock) {
		if (idProducto == -1 || idProducto <= 0) {
			vista.mostrarPopup("El ID del producto debe ser mayor que cero");
			return;
		}
		if (nombre.isEmpty()) {
			vista.mostrarPopup("El nombre no puede estar vacío");
			return;
		}
		if (precio <= 0) {
			vista.mostrarPopup("El precio debe ser mayor que cero");
			return;
		}
		if (stock < 0) {
			vista.mostrarPopup("El stock no puede ser negativo");
			return;
		}
		
		productoDaoImpl.actualizar(new Producto(idProducto, nombre, precio, stock));
		producto = productoDaoImpl.buscar(idProducto);
		vista.mostrarPopup("Producto actualizado");
	}

	public void eliminarProducto(int idProducto) {
		if(idProducto <= 0) {
			vista.mostrarPopup("El ID del producto debe ser mayor que cero");
			return;
		}
		try {
			productoDaoImpl.eliminar(idProducto);
			vista.mostrarPopup("Producto eliminado");
		} catch (IllegalArgumentException e) {
			vista.mostrarPopup("Producto no encontrado");
			return;
		}
	}

	public void listarProductos() {
		try {
			List<Producto> productos = productoDaoImpl.listar();
			if(productos.isEmpty()) {
				vista.mostrarPopup("No hay productos para mostrar");
				return;
			}
			vista.mostrarProductos(productos);
		} catch (IllegalArgumentException e) {
			vista.mostrarPopup("No hay productos para mostrar");
			return;
		}
	}
}
