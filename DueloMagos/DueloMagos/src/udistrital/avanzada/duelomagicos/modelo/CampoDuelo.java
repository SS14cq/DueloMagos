package udistrital.avanzada.duelomagicos.modelo;

/**
 * Campo de duelo mágico que sincroniza los turnos de los magos.
 * Garantiza que solo un mago lance hechizo a la vez.
 * 
 * @author Steban
 * @version 1.0
 */
public class CampoDuelo {
    private volatile boolean turnoMago1 = true;
    private volatile boolean dueloActivo = false;
    
    /**
     * Inicia el duelo entre dos magos
     */
    public synchronized void iniciarDuelo() {
        dueloActivo = true;
        turnoMago1 = true;
        notifyAll();
    }
    
    /**
     * Finaliza el duelo actual
     */
    public synchronized void finalizarDuelo() {
        dueloActivo = false;
        notifyAll();
    }
    
    /**
     * Verifica si el duelo está activo
     * @return true si el duelo está en curso
     */
    public synchronized boolean isDueloActivo() {
        return dueloActivo;
    }
    
    /**
     * Espera el turno del mago 1
     * @throws InterruptedException si el hilo es interrumpido
     */
    public synchronized void esperarTurnoMago1() throws InterruptedException {
        while (dueloActivo && !turnoMago1) {
            wait();
        }
    }
    
    /**
     * Espera el turno del mago 2
     * @throws InterruptedException si el hilo es interrumpido
     */
    public synchronized void esperarTurnoMago2() throws InterruptedException {
        while (dueloActivo && turnoMago1) {
            wait();
        }
    }
    
    /**
     * Cambia el turno al otro mago
     */
    public synchronized void cambiarTurno() {
        turnoMago1 = !turnoMago1;
        notifyAll();
    }
}