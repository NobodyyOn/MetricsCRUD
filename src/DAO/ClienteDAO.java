package DAO;

import java.util.List;

import modelo.Cliente;

public class ClienteDAO {
    protected static final String ruta = "json/clientes.json";
    private JsonUtil jsonUtil = new JsonUtil();
    Cliente cliente;

    public List<Cliente> listar() {
        return jsonUtil.leerJson(ruta, Cliente.class);
    }

    public void insertar(Cliente nuevoCliente) {

        if (existeCliente(nuevoCliente.getId())) {
            throw new IllegalArgumentException("El cliente ya existe");
        }

        List<Cliente> clientes = listar();
        clientes.add(nuevoCliente);
        jsonUtil.escribirJson(ruta, clientes);

    }

    public void actualizar(Cliente clienteActualizado) {
        if(!existeCliente(clienteActualizado.getId())) {
            throw new IllegalArgumentException("El cliente no existe");
        }
        List<Cliente> clientes = listar();
        int index = -1;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == clienteActualizado.getId()) {
                index = i;
                break;
            }
        }
        clientes.set(index, clienteActualizado);
        jsonUtil.escribirJson(ruta, clientes);

    }

    public void eliminar(int idCliente) {
        if(!existeCliente(idCliente)) {
            throw new IllegalArgumentException("El cliente no existe");
        }
        List<Cliente> clientes = listar();
        int index = -1;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == idCliente) {
                index = i;
                break;
            }
        }
        clientes.remove(index);
        jsonUtil.escribirJson(ruta, clientes);

    }

    public Cliente buscar(int idCliente) {
        return listar().stream()
                .filter(c -> c.getId() == idCliente)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("El cliente con ID " + idCliente + " no existe"));
    }

    private boolean existeCliente(int idCliente) {
        return listar().stream().anyMatch(c -> c.getId() == idCliente);
    }
}
