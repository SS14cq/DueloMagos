package udistrital.avanzada.duelomagicos.control;

import java.util.List;
import udistrital.avanzada.duelomagicos.modelo.Hechizo;
import udistrital.avanzada.duelomagicos.modelo.Mago;
import udistrital.avanzada.duelomagicos.vista.VistaPrincipal;

/**
 * Controlador principal que conecta componentes y eventos.
 */
public class ControladorPrincipal {
    private final VistaPrincipal vista;
    private final ControladorVista controladorVista;
    private final ControladorMagos controladorMagos;
    private final ControladorHechizos controladorHechizos;
    private final ControladorTorneo controladorTorneo;

    public ControladorPrincipal(VistaPrincipal vista) {
        this.vista = vista;
        this.controladorVista = new ControladorVista(vista);
        this.controladorMagos = new ControladorMagos();
        this.controladorHechizos = new ControladorHechizos();
        this.controladorTorneo = new ControladorTorneo(vista);
        inicializarEventos();
    }

    private void inicializarEventos() {
        vista.getBtnCargarMagos().addActionListener(e -> cargarMagos());
        vista.getBtnCargarHechizos().addActionListener(e -> cargarHechizos());
        vista.getBtnIniciarDuelo().addActionListener(e -> iniciarTorneo());
    }

    private void cargarMagos() {
        String ruta = controladorVista.solicitarArchivo();
        if (ruta != null) {
            try {
                controladorMagos.cargarMagos(ruta);
                vista.mostrarMagos(controladorMagos.getMagos());
            } catch (Exception e) {
                controladorVista.mostrarMensaje("Error al cargar magos: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cargarHechizos() {
        String ruta = controladorVista.solicitarArchivo();
        if (ruta != null) {
            try {
                controladorHechizos.cargarHechizos(ruta);
                vista.mostrarHechizos(controladorHechizos.getHechizos());
            } catch (Exception e) {
                controladorVista.mostrarMensaje("Error al cargar hechizos: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void iniciarTorneo() {
        List<Mago> magos = controladorMagos.getMagos();
        List<Hechizo> hechizos = controladorHechizos.getHechizos();
        if (magos == null || magos.size() < 2 || hechizos == null || hechizos.isEmpty())
            return;

        new Thread(() -> {
            Mago ganador = controladorTorneo.ejecutarTorneo(magos, hechizos);

            // Al final mostrar el resultado en el hilo de eventos de Swing
            javax.swing.SwingUtilities.invokeLater(() -> 
                controladorVista.mostrarMensaje(
                    "Ganador final: " + ganador.getNombre() + "\nCasa: " + ganador.getCasa()
                        + "\nHechizos lanzados: " + ganador.getCantidadHechizos() + "\nPuntos: " + ganador.getPuntos(),
                    "Resultado", javax.swing.JOptionPane.INFORMATION_MESSAGE
                )
            );
        }).start();
    }
}
