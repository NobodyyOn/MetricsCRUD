package DAO;

import java.util.List;

import modelo.Producto;

public class ProductoDAO {

    protected static final String ruta = "json/productos.json";
    private JsonUtil jsonUtil = new JsonUtil();
    Producto producto;

    public List<Producto> listar() {
        return jsonUtil.leerJson(ruta, Producto.class);
    }

    public void insertar(Producto nuevoProducto) {
        if (existeProducto(nuevoProducto.getId())) {
            throw new IllegalArgumentException("El cliente ya existe");

        }
        List<Producto> productos = listar();
        productos.add(nuevoProducto);
        jsonUtil.escribirJson(ruta, productos);
    }

    public void actualizar(Producto clienteActualizado) {
        if(!existeProducto(clienteActualizado.getId())) {
            throw new IllegalArgumentException("El producto no existe");
        }
        List<Producto> productos = listar();
        int index = -1;
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == clienteActualizado.getId()) {
                index = i;
                break;
            }
        }
        productos.set(index, clienteActualizado);
        jsonUtil.escribirJson(ruta, productos);
    }

    public void eliminar(int idCliente) {
        if(!existeProducto(idCliente)) {
            throw new IllegalArgumentException("El producto no existe");
        }
        List<Producto> productos = listar();
        int index = -1;
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == idCliente) {
                index = i;
                break;
            }
        }
        productos.remove(index);
        jsonUtil.escribirJson(ruta, productos);
    }


    public Producto buscar(int idProducto) {
        return listar().stream()
                .filter(p -> p.getId() == idProducto)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("El producto con ID " + idProducto + " no existe"));
    }

    private boolean existeProducto(int id) {
        return listar().stream().anyMatch(p -> p.getId() == id);
    }

}
