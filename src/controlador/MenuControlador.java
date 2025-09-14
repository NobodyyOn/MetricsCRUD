package controlador;

import DAO.ClienteDAOimpl;
import DAO.ProductoDAO;
import vista.ClienteVista;
import vista.MenuGeneral;
import vista.ProductoVista;

public class MenuControlador {

    private MenuGeneral menuGeneral;

    @SuppressWarnings("unused")
    public MenuControlador(MenuGeneral menuGeneral) {
        this.menuGeneral = menuGeneral;
        menuGeneral.addIrClientes(e -> irClientes());
        menuGeneral.addIrProductos(e -> irProductos());
        this.iniciar();
    }

    private void iniciar() {
        menuGeneral.setVisible(true);
    }

    private void irClientes() {
        // Lógica para abrir la ventana de clientes
        ClienteControlador clienteCtrl = new ClienteControlador(new ClienteVista(),
                new ClienteDAOimpl());
        clienteCtrl.iniciar();
    }

    private void irProductos() {
        ProductoControlador productoCtrl = new ProductoControlador(new ProductoVista(),
                new ProductoDAO());
        productoCtrl.iniciar();
    }
}