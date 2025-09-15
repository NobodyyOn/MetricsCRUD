package DAO;

import java.util.List;

import modelo.Cliente;

interface ClienteDAO {

    public List<Cliente> listar();

    public Cliente buscar(int idCliente);

    public void insertar(Cliente nuevoCliente);

    public void actualizar(Cliente clienteActualizado);

    public void eliminar(int idCliente);
    

    
}
