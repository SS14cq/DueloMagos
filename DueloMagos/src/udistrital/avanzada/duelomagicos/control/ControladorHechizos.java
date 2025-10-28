/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.duelomagicos.control;

import java.util.ArrayList;
import java.util.List;
import udistrital.avanzada.duelomagicos.modelo.Hechizo;
import udistrital.avanzada.duelomagicos.modelo.control.ConexionPropiedades;

/**
 * Controlador dedicado a operaciones sobre hechizos.
 */
public class ControladorHechizos {
    private List<Hechizo> hechizos = new ArrayList<>();

    public void cargarHechizos(String rutaArchivo) throws Exception {
        hechizos.clear();
        for (Object[] entrada : ConexionPropiedades.leerHechizos(rutaArchivo))
            hechizos.add(new Hechizo((String)entrada[0], (int)entrada[1]));
    }

    public List<Hechizo> getHechizos() { return hechizos; }
}
