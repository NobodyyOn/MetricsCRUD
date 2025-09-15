import controlador.MenuControlador;
import vista.MenuGeneral;

public class Main {
    public static void main(String[] args) {

        new MenuControlador(new MenuGeneral());
    }
}
