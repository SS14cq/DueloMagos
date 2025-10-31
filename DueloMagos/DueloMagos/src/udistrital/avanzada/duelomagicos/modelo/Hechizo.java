package udistrital.avanzada.duelomagicos.modelo;

/**
 * Representa un hechizo mágico con nombre y puntos de daño. Los hechizos son
 * inmutables una vez creados.
 *
 * @author Steban
 * @version 1.1
 */
public class Hechizo {

    private final String nombre;
    private final int puntos;

    /**
     * Constructor de Hechizo
     *
     * @param nombre Nombre del hechizo
     * @param puntos Puntos de daño que causa el hechizo
     */
    public Hechizo(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }

    /**
     * Obtiene el nombre del hechizo
     *
     * @return Nombre del hechizo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los puntos de daño del hechizo
     *
     * @return Puntos del hechizo
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Representación en cadena del hechizo
     *
     * @return Cadena con formato "nombre [puntos]"
     */
    @Override
    public String toString() {
        return nombre + " [" + puntos + " pts]";
    }
}
