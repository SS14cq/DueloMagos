package udistrital.avanzada.duelomagicos.modelo.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Clase utilitaria para cargar datos desde archivos de propiedades.
 * Proporciona métodos para leer magos y hechizos desde archivos .properties
 * 
 * @author Steban
 * @version 1.1
 */
public class ConexionPropiedades {
    
    /**
     * Lee la lista de magos desde un archivo de propiedades
     * @param archivo Ruta del archivo .properties
     * @return Lista de arreglos con datos de magos [nombre, casa]
     * @throws IOException Si ocurre un error al leer el archivo
     */
    public static List<String[]> leerMagos(String archivo) throws IOException {
        List<String[]> magos = new ArrayList<>();
        
        try (InputStream in = new FileInputStream(archivo)) {
            Properties props = new Properties();
            props.load(in);
            
            int count = Integer.parseInt(props.getProperty("count", "0"));
            
            for (int i = 1; i <= count; i++) {
                String nombre = props.getProperty("mago" + i + ".nombre");
                String casa = props.getProperty("mago" + i + ".casa");
                
                if (nombre != null && casa != null) {
                    magos.add(new String[]{nombre, casa});
                }
            }
        }
        
        return magos;
    }
    
    /**
     * Lee la lista de hechizos desde un archivo de propiedades
     * @param archivo Ruta del archivo .properties
     * @return Lista de arreglos con datos de hechizos [nombre, puntos]
     * @throws IOException Si ocurre un error al leer el archivo
     */
    public static List<Object[]> leerHechizos(String archivo) throws IOException {
        List<Object[]> hechizos = new ArrayList<>();
        
        try (InputStream in = new FileInputStream(archivo)) {
            Properties props = new Properties();
            props.load(in);
            
            int count = Integer.parseInt(props.getProperty("count", "0"));
            
            for (int i = 1; i <= count; i++) {
                String nombre = props.getProperty("hechizo" + i + ".nombre");
                String puntosStr = props.getProperty("hechizo" + i + ".puntos");
                
                if (nombre != null && puntosStr != null) {
                    int puntos = Integer.parseInt(puntosStr);
                    hechizos.add(new Object[]{nombre, puntos});
                }
            }
        }
        
        return hechizos;
    }
}