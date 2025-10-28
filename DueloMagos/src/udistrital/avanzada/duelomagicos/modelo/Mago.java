package udistrital.avanzada.duelomagicos.modelo;

public class Mago {
    private final String nombre;
    private final String casa;
    private int puntos = 0;
    private int cantidadHechizos = 0;

    public Mago(String nombre, String casa){
        this.nombre = nombre;
        this.casa = casa;
    }

    public String getNombre() { return nombre; }
    public String getCasa() { return casa; }
    public int getPuntos() { return puntos; }
    public int getCantidadHechizos() { return cantidadHechizos; }

    public synchronized void sumarPuntos(int p) { puntos += p; }
    public synchronized void incrementarHechizos() { cantidadHechizos++; }

    public synchronized void resetPuntos() {
        this.puntos = 0;
    }

    public synchronized void resetCantidadHechizos() {
        this.cantidadHechizos = 0;
    }
}
