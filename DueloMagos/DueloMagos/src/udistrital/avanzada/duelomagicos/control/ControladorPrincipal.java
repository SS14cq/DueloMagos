package udistrital.avanzada.duelomagicos.control;

import java.util.List;
import javax.swing.JOptionPane;
import udistrital.avanzada.duelomagicos.modelo.Hechizo;
import udistrital.avanzada.duelomagicos.modelo.Mago;
import udistrital.avanzada.duelomagicos.vista.VistaPrincipal;

/**
 * Controlador principal que conecta todos los componentes del sistema.
 * Coordina la interacción entre vista, modelo y controladores específicos.
 * Aplica el patrón MVC.
 * 
 * @author Steban
 * @version 1.1
 */
public class ControladorPrincipal {
    private final VistaPrincipal vista;
    private final ControladorVista controladorVista;
    private final ControladorMagos controladorMagos;
    private final ControladorHechizos controladorHechizos;
    private final ControladorTorneo controladorTorneo;
    
    /**
     * Constructor del controlador principal
     * @param vista Vista principal de la aplicación
     */
    public ControladorPrincipal(VistaPrincipal vista) {
        this.vista = vista;
        this.controladorVista = new ControladorVista(vista);
        this.controladorMagos = new ControladorMagos();
        this.controladorHechizos = new ControladorHechizos();
        this.controladorTorneo = new ControladorTorneo(vista);
        inicializarEventos();
    }
    
    /**
     * Inicializa los eventos de los componentes de la vista
     */
    private void inicializarEventos() {
        vista.getBtnCargarMagos().addActionListener(e -> cargarMagos());
        vista.getBtnCargarHechizos().addActionListener(e -> cargarHechizos());
        vista.getBtnIniciarDuelo().addActionListener(e -> iniciarTorneo());
    }
    
    /**
     * Carga los magos desde un archivo seleccionado por el usuario
     */
    private void cargarMagos() {
        String ruta = controladorVista.solicitarArchivo();
        if (ruta != null) {
            try {
                controladorMagos.cargarMagos(ruta);
                vista.mostrarMagos(controladorMagos.getMagos());
                controladorVista.mostrarMensaje(
                    "Magos cargados exitosamente: " + controladorMagos.getMagos().size(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } catch (Exception e) {
                controladorVista.mostrarMensaje(
                    "Error al cargar magos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    /**
     * Carga los hechizos desde un archivo seleccionado por el usuario
     */
    private void cargarHechizos() {
        String ruta = controladorVista.solicitarArchivo();
        if (ruta != null) {
            try {
                controladorHechizos.cargarHechizos(ruta);
                vista.mostrarHechizos(controladorHechizos.getHechizos());
                controladorVista.mostrarMensaje(
                    "Hechizos cargados exitosamente: " + controladorHechizos.getHechizos().size(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } catch (Exception e) {
                controladorVista.mostrarMensaje(
                    "Error al cargar hechizos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    /**
     * Inicia el torneo de magos en un hilo separado
     */
    private void iniciarTorneo() {
        List<Mago> magos = controladorMagos.getMagos();
        List<Hechizo> hechizos = controladorHechizos.getHechizos();
        
        if (validarDatosTorneo(magos, hechizos)) {
            new Thread(() -> ejecutarTorneo(magos, hechizos)).start();
        }
    }
    
    /**
     * Valida que existan los datos necesarios para iniciar el torneo
     * @param magos Lista de magos
     * @param hechizos Lista de hechizos
     * @return true si los datos son válidos
     */
    private boolean validarDatosTorneo(List<Mago> magos, List<Hechizo> hechizos) {
        if (magos == null || magos.isEmpty()) {
            controladorVista.mostrarMensaje(
                "Debe cargar los magos primero",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        
        if (magos.size() < 2) {
            controladorVista.mostrarMensaje(
                "Se necesitan al menos 2 magos para el torneo",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        
        if (hechizos == null || hechizos.isEmpty()) {
            controladorVista.mostrarMensaje(
                "Debe cargar los hechizos primero",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        
        return true;
    }
    
    /**
     * Ejecuta el torneo y muestra el resultado final
     * @param magos Lista de magos participantes
     * @param hechizos Lista de hechizos disponibles
     */
    private void ejecutarTorneo(List<Mago> magos, List<Hechizo> hechizos) {
        Mago ganador = controladorTorneo.ejecutarTorneo(magos, hechizos);
        
        if (ganador != null) {
            javax.swing.SwingUtilities.invokeLater(() -> 
                controladorVista.mostrarMensaje(
                    "🏆 CAMPEÓN DEL TORNEO 🏆\n\n" +
                    "Nombre: " + ganador.getNombre() + "\n" +
                    "Casa: " + ganador.getCasa() + "\n" +
                    "Hechizos lanzados: " + ganador.getCantidadHechizos() + "\n" +
                    "Puntos finales: " + ganador.getPuntos(),
                    "Resultado Final",
                    JOptionPane.INFORMATION_MESSAGE
                )
            );
        }
    }
}