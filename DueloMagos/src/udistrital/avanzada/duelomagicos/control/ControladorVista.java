/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.duelomagicos.control;

import javax.swing.*;
import udistrital.avanzada.duelomagicos.vista.VistaPrincipal;

/**
 * Controlador para facilitar interacción con la vista.
 */
public class ControladorVista {

    private VistaPrincipal vista;

    public ControladorVista(VistaPrincipal vista) {
        this.vista = vista;
    }

    public String solicitarArchivo() {
        JFileChooser chooser = new JFileChooser();
        int res = chooser.showOpenDialog(vista);
        if (res == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(vista, mensaje, titulo, tipo);
    }

    public void mostrarAccion(String mensaje) {
        vista.mostrarAccion(mensaje);
    }
}
