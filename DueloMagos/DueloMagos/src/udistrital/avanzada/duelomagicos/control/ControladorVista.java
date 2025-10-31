package udistrital.avanzada.duelomagicos.control;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import udistrital.avanzada.duelomagicos.vista.VistaPrincipal;

/**
 * Controlador para facilitar la interacción con la vista. Gestiona diálogos,
 * mensajes y selección de archivos.
 *
 * @author Steban
 * @version 1.1
 */
public class ControladorVista {

    private final VistaPrincipal vista;

    /**
     * Constructor del controlador de vista
     *
     * @param vista Vista principal de la aplicación
     */
    public ControladorVista(VistaPrincipal vista) {
        this.vista = vista;
    }

    /**
     * Muestra un diálogo para seleccionar un archivo .properties
     *
     * @return Ruta absoluta del archivo seleccionado, o null si se cancela
     */
    public String solicitarArchivo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar archivo de propiedades");

        // Filtro para archivos .properties
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Archivos Properties (*.properties)", "properties"
        );
        chooser.setFileFilter(filter);

        // Establecer directorio inicial en resources si existe
        String userDir = System.getProperty("user.dir");
        chooser.setCurrentDirectory(new java.io.File(userDir));

        int resultado = chooser.showOpenDialog(vista);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }

        return null;
    }

    /**
     * Muestra un mensaje al usuario mediante un JOptionPane
     *
     * @param mensaje Mensaje a mostrar
     * @param titulo Título de la ventana
     * @param tipo Tipo de mensaje (JOptionPane.INFORMATION_MESSAGE, etc.)
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(vista, mensaje, titulo, tipo);
    }

    /**
     * Actualiza el texto de acción en la vista
     *
     * @param mensaje Mensaje de acción a mostrar
     */
    public void mostrarAccion(String mensaje) {
        vista.mostrarAccion(mensaje);
    }
}
