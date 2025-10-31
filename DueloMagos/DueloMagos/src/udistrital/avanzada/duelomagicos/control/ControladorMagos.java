package udistrital.avanzada.duelomagicos.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import udistrital.avanzada.duelomagicos.modelo.Mago;
import udistrital.avanzada.duelomagicos.modelo.control.ConexionPropiedades;

/**
 * Controlador dedicado a operaciones sobre magos.
 * Gestiona la carga y administración de magos participantes.
 * 
 * @author Steban
 * @version 1.0
 */
public class ControladorMagos {
    private List<Mago> magos;
    
    /**
     * Constructor del controlador de magos
     */
    public ControladorMagos() {
        this.magos = new ArrayList<>();
    }
    
    /**
     * Carga los magos desde un archivo de propiedades
     * @param rutaArchivo Ruta del archivo .properties
     * @throws IOException Si ocurre un error al leer el archivo
     */
    public void cargarMagos(String rutaArchivo) throws IOException {
        magos.clear();
        List<String[]> datosMagos = ConexionPropiedades.leerMagos(rutaArchivo);
        
        for (String[] datos : datosMagos) {
            magos.add(new Mago(datos[0], datos[1]));
        }
    }
    
    /**
     * Obtiene la lista de magos cargados
     * @return Lista de magos
     */
    public List<Mago> getMagos() {
        return magos;
    }
}