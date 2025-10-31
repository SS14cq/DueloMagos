package udistrital.avanzada.duelomagicos.control;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import udistrital.avanzada.duelomagicos.vista.VistaPrincipal;

/**
 * Clase principal que inicia la aplicación del Torneo de Magos.
 * Punto de entrada del programa.
 * 
 * @author Steban
 * @version 1.1
 */
public class Launcher {
    
    /**
     * Método principal que inicia la aplicación
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Configurar Look and Feel del sistema
        configurarLookAndFeel();
        
        // Iniciar la aplicación en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            VistaPrincipal vista = new VistaPrincipal();
            new ControladorPrincipal(vista);
            vista.setVisible(true);
        });
    }
    
    /**
     * Configura el Look and Feel de la aplicación
     */
    private static void configurarLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si falla, se usará el Look and Feel por defecto
        }
    }
}