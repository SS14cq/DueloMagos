package udistrital.avanzada.duelomagicos.control;

import java.util.Random;
import java.util.function.BiConsumer;
import udistrital.avanzada.duelomagicos.modelo.DueloMagico;
import udistrital.avanzada.duelomagicos.modelo.Hechizo;
import udistrital.avanzada.duelomagicos.modelo.Mago;

/**
 * Runnable que representa la acción de un mago durante un duelo.
 * Cada mago ejecuta en su propio hilo respetando los turnos.
 * 
 * @author Steban
 * @version 1.0
 */
public class DueloRunnable implements Runnable {
    private final Mago magoActual;
    private final Mago magoOponente;
    private final DueloMagico duelo;
    private final boolean esMago1;
    private final BiConsumer<String, String> notificadorVista;
    private final Runnable actualizadorVista;
    private final Random random;
    
    private static final int TIEMPO_MAX_ATURDIMIENTO = 250;
    private static final int TIEMPO_MAX_ESPERA = 500;
    
    /**
     * Constructor del runnable de duelo
     * @param magoActual Mago que ejecutará este hilo
     * @param magoOponente Mago oponente
     * @param duelo Duelo mágico en curso
     * @param esMago1 true si es el mago 1, false si es el mago 2
     * @param notificadorVista Callback para notificar acciones a la vista
     * @param actualizadorVista Callback para actualizar la vista
     */
    public DueloRunnable(Mago magoActual, Mago magoOponente, DueloMagico duelo, 
                         boolean esMago1, BiConsumer<String, String> notificadorVista,
                         Runnable actualizadorVista) {
        this.magoActual = magoActual;
        this.magoOponente = magoOponente;
        this.duelo = duelo;
        this.esMago1 = esMago1;
        this.notificadorVista = notificadorVista;
        this.actualizadorVista = actualizadorVista;
        this.random = new Random();
    }
    
    @Override
    public void run() {
        try {
            while (duelo.getCampoDuelo().isDueloActivo() && !duelo.dueloTerminado()) {
                // Esperar mi turno
                if (esMago1) {
                    duelo.getCampoDuelo().esperarTurnoMago1();
                } else {
                    duelo.getCampoDuelo().esperarTurnoMago2();
                }
                
                // Verificar si aún está activo después de esperar
                if (!duelo.getCampoDuelo().isDueloActivo() || duelo.dueloTerminado()) {
                    break;
                }
                
                // Si estoy aturdido, debo esperar antes de actuar
                if (magoActual.isAturdido()) {
                    int tiempoAturdimiento = random.nextInt(TIEMPO_MAX_ATURDIMIENTO) + 1;
                    Thread.sleep(tiempoAturdimiento);
                    magoActual.setAturdido(false);
                }
                
                // Lanzar hechizo
                Hechizo hechizoLanzado = seleccionarHechizoAleatorio();
                lanzarHechizo(hechizoLanzado);
                
                // Notificar a la vista
                javax.swing.SwingUtilities.invokeLater(() -> {
                    notificadorVista.accept(magoActual.getNombre(), hechizoLanzado.getNombre());
                    actualizadorVista.run();
                });
                
                // Esperar tiempo aleatorio después de lanzar
                int tiempoEspera = random.nextInt(TIEMPO_MAX_ESPERA) + 1;
                Thread.sleep(tiempoEspera);
                
                // Cambiar turno
                duelo.getCampoDuelo().cambiarTurno();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Selecciona un hechizo aleatorio de la lista disponible
     * @return Hechizo seleccionado
     */
    private Hechizo seleccionarHechizoAleatorio() {
        int indice = random.nextInt(duelo.getHechizosDisponibles().size());
        return duelo.getHechizosDisponibles().get(indice);
    }
    
    /**
     * Ejecuta el lanzamiento del hechizo
     * @param hechizo Hechizo a lanzar
     */
    private void lanzarHechizo(Hechizo hechizo) {
        // El atacante suma puntos por el hechizo lanzado
        magoActual.sumarPuntos(hechizo.getPuntos());
        magoActual.incrementarHechizos();
        
        // El oponente queda aturdido
        magoOponente.setAturdido(true);
    }
}