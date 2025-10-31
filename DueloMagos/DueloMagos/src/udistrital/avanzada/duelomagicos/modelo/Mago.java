package udistrital.avanzada.duelomagicos.modelo;

/**
 * Representa un mago participante en el torneo.
 * Contiene información básica y estadísticas del duelo.
 * 
 * @author Steban
 * @version 1.0
 */
public class Mago {
    private final String nombre;
    private final String casa;
    private int puntos;
    private int cantidadHechizos;
    private boolean aturdido;
    
    /**
     * Constructor de Mago
     * @param nombre Nombre del mago
     * @param casa Casa mágica a la que pertenece
     */
    public Mago(String nombre, String casa) {
        this.nombre = nombre;
        this.casa = casa;
        this.puntos = 0;
        this.cantidadHechizos = 0;
        this.aturdido = false;
    }
    
    // Getters
    public String getNombre() { return nombre; }
    public String getCasa() { return casa; }
    public synchronized int getPuntos() { return puntos; }
    public synchronized int getCantidadHechizos() { return cantidadHechizos; }
    public synchronized boolean isAturdido() { return aturdido; }
    
    /**
     * Suma puntos por hechizo lanzado
     * @param p Puntos a sumar
     */
    public synchronized void sumarPuntos(int p) { 
        puntos += p; 
    }
    
    /**
     * Incrementa contador de hechizos lanzados
     */
    public synchronized void incrementarHechizos() { 
        cantidadHechizos++; 
    }
    
    /**
     * Establece el estado de aturdimiento del mago
     * @param estado true si está aturdido, false en caso contrario
     */
    public synchronized void setAturdido(boolean estado) {
        this.aturdido = estado;
    }
    
    /**
     * Reinicia los puntos del mago para un nuevo duelo
     */
    public synchronized void resetPuntos() {
        this.puntos = 0;
    }
    
    /**
     * Reinicia el contador de hechizos para un nuevo duelo
     */
    public synchronized void resetCantidadHechizos() {
        this.cantidadHechizos = 0;
    }
    
    /**
     * Reinicia el estado de aturdimiento
     */
    public synchronized void resetAturdido() {
        this.aturdido = false;
    }
}