package udistrital.avanzada.duelomagicos.control;

import java.util.List;
import javax.swing.SwingUtilities;
import udistrital.avanzada.duelomagicos.modelo.DueloMagico;
import udistrital.avanzada.duelomagicos.modelo.Hechizo;
import udistrital.avanzada.duelomagicos.modelo.Mago;
import udistrital.avanzada.duelomagicos.vista.VistaPrincipal;

/**
 * Controlador que gestiona la lógica del torneo de magos.
 * Coordina los duelos sucesivos hasta determinar un campeón.
 * 
 * @author Steban
 * @version 1.0
 */
public class ControladorTorneo {
    private final VistaPrincipal vista;
    private static final int PAUSA_ENTRE_DUELOS = 1500;
    
    /**
     * Constructor del controlador de torneo
     * @param vista Vista principal de la aplicación
     */
    public ControladorTorneo(VistaPrincipal vista) {
        this.vista = vista;
    }
    
    /**
     * Ejecuta el torneo completo entre todos los magos
     * @param magos Lista de magos participantes
     * @param hechizos Lista de hechizos disponibles
     * @return Mago ganador del torneo
     */
    public Mago ejecutarTorneo(List<Mago> magos, List<Hechizo> hechizos) {
        if (magos == null || magos.size() < 2 || hechizos == null || hechizos.isEmpty()) {
            return null;
        }
        
        Mago campeonActual = magos.get(0);
        
        // El campeón actual enfrenta a cada mago siguiente
        for (int i = 1; i < magos.size(); i++) {
            Mago desafiante = magos.get(i);
            
            notificarInicioDuelo(campeonActual, desafiante);
            
            Mago ganadorDuelo = ejecutarDuelo(campeonActual, desafiante, hechizos, magos);
            
            notificarFinDuelo(ganadorDuelo);
            
            campeonActual = ganadorDuelo;
            
            pausarEntreDuelos();
        }
        
        return campeonActual;
    }
    
    /**
     * Ejecuta un duelo individual entre dos magos
     * @param mago1 Primer mago
     * @param mago2 Segundo mago
     * @param hechizos Lista de hechizos disponibles
     * @param todosMagos Lista completa de magos para actualizar vista
     * @return Mago ganador del duelo
     */
    private Mago ejecutarDuelo(Mago mago1, Mago mago2, List<Hechizo> hechizos, List<Mago> todosMagos) {
        DueloMagico duelo = new DueloMagico(mago1, mago2, hechizos);
        duelo.prepararDuelo();
        
        Runnable actualizadorVista = () -> 
            SwingUtilities.invokeLater(() -> vista.mostrarMagos(todosMagos));
        
        // Crear hilos para cada mago
        DueloRunnable runnable1 = new DueloRunnable(
            mago1, mago2, duelo, true,
            (nombre, hechizo) -> vista.mostrarAccion("🔮 " + nombre + " lanza " + hechizo + "!"),
            actualizadorVista
        );
        
        DueloRunnable runnable2 = new DueloRunnable(
            mago2, mago1, duelo, false,
            (nombre, hechizo) -> vista.mostrarAccion("⚡ " + nombre + " lanza " + hechizo + "!"),
            actualizadorVista
        );
        
        Thread hilo1 = new Thread(runnable1);
        Thread hilo2 = new Thread(runnable2);
        
        // Iniciar el duelo
        duelo.getCampoDuelo().iniciarDuelo();
        hilo1.start();
        hilo2.start();
        
        // Esperar a que termine el duelo
        esperarFinDuelo(duelo);
        
        // Detener el duelo
        duelo.getCampoDuelo().finalizarDuelo();
        
        // Esperar a que los hilos terminen
        try {
            hilo1.join(1000);
            hilo2.join(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return duelo.obtenerGanador();
    }
    
    /**
     * Espera activamente hasta que el duelo termine
     * @param duelo Duelo a monitorear
     */
    private void esperarFinDuelo(DueloMagico duelo) {
        while (!duelo.dueloTerminado()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    /**
     * Notifica a la vista el inicio de un duelo
     * @param mago1 Primer participante
     * @param mago2 Segundo participante
     */
    private void notificarInicioDuelo(Mago mago1, Mago mago2) {
        SwingUtilities.invokeLater(() -> 
            vista.mostrarAccion("⚔️ INICIA DUELO: " + mago1.getNombre() + 
                              " vs " + mago2.getNombre())
        );
    }
    
    /**
     * Notifica a la vista el fin de un duelo
     * @param ganador Mago ganador del duelo
     */
    private void notificarFinDuelo(Mago ganador) {
        SwingUtilities.invokeLater(() -> 
            vista.mostrarAccion("🏆 GANADOR DEL DUELO: " + ganador.getNombre() + 
                              " (" + ganador.getCasa() + ") - " + 
                              ganador.getPuntos() + " puntos")
        );
    }
    
    /**
     * Pausa la ejecución entre duelos
     */
    private void pausarEntreDuelos() {
        try {
            Thread.sleep(PAUSA_ENTRE_DUELOS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}