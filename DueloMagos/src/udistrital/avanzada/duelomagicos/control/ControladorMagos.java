/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.duelomagicos.control;

import java.util.ArrayList;
import java.util.List;
import udistrital.avanzada.duelomagicos.modelo.Mago;
import udistrital.avanzada.duelomagicos.modelo.control.ConexionPropiedades;

/**
 * Controlador dedicado a operaciones sobre magos.
 */
public class ControladorMagos {
    private List<Mago> magos = new ArrayList<>();

    public void cargarMagos(String rutaArchivo) throws Exception {
        magos.clear();
        for (String[] datos : ConexionPropiedades.leerMagos(rutaArchivo))
            magos.add(new Mago(datos[0], datos[1]));
    }

    public List<Mago> getMagos() { return magos; }
}

