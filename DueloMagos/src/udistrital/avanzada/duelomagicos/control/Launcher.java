/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.duelomagicos.control;

import javax.swing.SwingUtilities;
import udistrital.avanzada.duelomagicos.vista.VistaPrincipal;

/**
 *
 * @author sarit
 */
public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaPrincipal vista = new VistaPrincipal();
            new ControladorPrincipal(vista);
            vista.setVisible(true);
        });
    }
}
