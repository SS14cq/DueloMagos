/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.duelomagicos.modelo;

/**
 * Modelo de un hechizo en el sistema.
 * Tiene nombre y cantidad de puntos de impacto.
 */
public class Hechizo {
    private final String nombre;
    private final int puntos;

    public Hechizo(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }
    public String getNombre() { return nombre; }
    public int getPuntos() { return puntos; }
    @Override
    public String toString() { return nombre + " [" + puntos + "]"; }
}
