package udistrital.avanzada.duelomagicos.modelo.control;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Utilitaria para cargar magos y hechizos desde archivos .properties.
 */
public class ConexionPropiedades {
    public static List<String[]> leerMagos(String archivo) {
        List<String[]> mags = new ArrayList<>();
        try (InputStream in = new FileInputStream(archivo)) {
            Properties props = new Properties();
            props.load(in);
            int count = Integer.parseInt(props.getProperty("count", "0"));
            for (int i = 1; i <= count; i++) {
                String nombre = props.getProperty("mago" + i + ".nombre");
                String casa = props.getProperty("mago" + i + ".casa");
                mags.add(new String[]{nombre, casa});
            }
        } catch (Exception e) { e.printStackTrace(); }
        return mags;
    }
    public static List<Object[]> leerHechizos(String archivo) {
        List<Object[]> hech = new ArrayList<>();
        try (InputStream in = new FileInputStream(archivo)) {
            Properties props = new Properties();
            props.load(in);
            int count = Integer.parseInt(props.getProperty("count", "0"));
            for (int i = 1; i <= count; i++) {
                String nombre = props.getProperty("hechizo" + i + ".nombre");
                int puntos = Integer.parseInt(props.getProperty("hechizo" + i + ".puntos"));
                hech.add(new Object[]{nombre, puntos});
            }
        } catch (Exception e) { e.printStackTrace(); }
        return hech;
    }
}
