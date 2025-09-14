package DAO;

import modelo.Cliente;
import java.util.List;

public class ClienteDAOimpl implements ClienteDAO {
    private JsonUtil jsonUtil = new JsonUtil();

    @Override
    public List<Cliente> listar() {
        return jsonUtil.leerJson("clientes.json", Cliente.class);
    }

    @Override
    public Cliente buscar(int idCliente) {
        List <Cliente> clientes = listar();
        if(!clientes.contains(new Cliente(idCliente, null, null, null))) {
            throw new IllegalArgumentException("El cliente con ID " + idCliente + " no existe");
        }
        return clientes.get(clientes.indexOf(new Cliente(idCliente, null, null, null)));
    }

    @Override
    public void insertar(Cliente nuevoCliente) {
        List<Cliente> clientes = listar();
        if (clientes.contains(nuevoCliente)) {
            throw new IllegalArgumentException("El cliente ya existe");
        }
        clientes.add(nuevoCliente);
        jsonUtil.escribirJson("clientes.json", clientes);

    }

    @Override
    public void actualizar(Cliente clienteActualizado) {
        List<Cliente> clientes = listar();
        if(!clientes.contains(clienteActualizado)) {
            throw new IllegalArgumentException("El cliente con ID " + clienteActualizado.getId() + " no existe");
        }
        clientes.set(clientes.indexOf(clienteActualizado), clienteActualizado);
        jsonUtil.escribirJson("clientes.json", clientes);

    }

    @Override
    public void eliminar(int idCliente) {
        List<Cliente> clientes = listar();
        if (clientes.removeIf(cliente -> cliente.getId() == idCliente)) {
            jsonUtil.escribirJson("clientes.json", clientes);
        } else {
            throw new IllegalArgumentException("El cliente con ID " + idCliente + " no existe");
        }

    }
    
}
