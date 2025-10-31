package udistrital.avanzada.duelomagicos.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import udistrital.avanzada.duelomagicos.modelo.Hechizo;
import udistrital.avanzada.duelomagicos.modelo.control.ConexionPropiedades;

/**
 * Controlador dedicado a operaciones sobre hechizos.
 * Gestiona la carga y administración de hechizos disponibles.
 * 
 * @author Steban
 * @version 1.1
 */
public class ControladorHechizos {
    private List<Hechizo> hechizos;
    
    /**
     * Constructor del controlador de hechizos
     */
    public ControladorHechizos() {
        this.hechizos = new ArrayList<>();
    }
    
    /**
     * Carga los hechizos desde un archivo de propiedades
     * @param rutaArchivo Ruta del archivo .properties
     * @throws IOException Si ocurre un error al leer el archivo
     */
    public void cargarHechizos(String rutaArchivo) throws IOException {
        hechizos.clear();
        List<Object[]> datosHechizos = ConexionPropiedades.leerHechizos(rutaArchivo);
        
        for (Object[] datos : datosHechizos) {
            hechizos.add(new Hechizo((String)datos[0], (int)datos[1]));
        }
    }
    
    /**
     * Obtiene la lista de hechizos cargados
     * @return Lista de hechizos
     */
    public List<Hechizo> getHechizos() {
        return hechizos;
    }
}