import vista.MenuGeneral;

public class Main {
    public static void main(String[] args) {
        // Ejecutar la creación de la interfaz en el hilo de Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            MenuGeneral menu = new MenuGeneral();
            menu.setVisible(true);
        });
    }
}
