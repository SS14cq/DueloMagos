package udistrital.avanzada.duelomagicos.modelo;

import java.util.List;

/**
 * Representa un duelo mágico entre dos magos.
 * Gestiona la lógica del enfrentamiento y determina el ganador.
 * 
 * @author Steban
 * @version 1.0
 */
public class DueloMagico {
    private final Mago mago1;
    private final Mago mago2;
    private final List<Hechizo> hechizosDisponibles;
    private final CampoDuelo campoDuelo;
    private static final int PUNTOS_VICTORIA = 250;
    
    /**
     * Constructor del duelo mágico
     * @param mago1 Primer mago participante
     * @param mago2 Segundo mago participante
     * @param hechizosDisponibles Lista de hechizos disponibles
     */
    public DueloMagico(Mago mago1, Mago mago2, List<Hechizo> hechizosDisponibles) {
        this.mago1 = mago1;
        this.mago2 = mago2;
        this.hechizosDisponibles = hechizosDisponibles;
        this.campoDuelo = new CampoDuelo();
    }
    
    /**
     * Prepara a los magos para el duelo
     */
    public void prepararDuelo() {
        mago1.resetPuntos();
        mago1.resetCantidadHechizos();
        mago1.resetAturdido();
        
        mago2.resetPuntos();
        mago2.resetCantidadHechizos();
        mago2.resetAturdido();
    }
    
    /**
     * Obtiene el campo de duelo para sincronización
     * @return Campo de duelo
     */
    public CampoDuelo getCampoDuelo() {
        return campoDuelo;
    }
    
    /**
     * Verifica si el duelo ha terminado
     * @return true si algún mago alcanzó los puntos de victoria
     */
    public boolean dueloTerminado() {
        return mago1.getPuntos() >= PUNTOS_VICTORIA || 
               mago2.getPuntos() >= PUNTOS_VICTORIA;
    }
    
    /**
     * Determina el ganador del duelo
     * @return El mago ganador
     */
    public Mago obtenerGanador() {
        return mago1.getPuntos() >= mago2.getPuntos() ? mago1 : mago2;
    }
    
    /**
     * Obtiene el primer mago
     * @return Mago 1
     */
    public Mago getMago1() {
        return mago1;
    }
    
    /**
     * Obtiene el segundo mago
     * @return Mago 2
     */
    public Mago getMago2() {
        return mago2;
    }
    
    /**
     * Obtiene la lista de hechizos disponibles
     * @return Lista de hechizos
     */
    public List<Hechizo> getHechizosDisponibles() {
        return hechizosDisponibles;
    }
}