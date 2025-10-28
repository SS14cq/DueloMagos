package udistrital.avanzada.duelomagicos.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import udistrital.avanzada.duelomagicos.modelo.Hechizo;
import udistrital.avanzada.duelomagicos.modelo.Mago;

public class VistaPrincipal extends JFrame {
    private final JPanel panelMagos = new JPanel();
    private final JPanel panelHechizos = new JPanel();
    private final JLabel lblAccion = new JLabel("Esperando acción...");
    private final JButton btnCargarMagos = new JButton("Cargar Magos");
    private final JButton btnCargarHechizos = new JButton("Cargar Hechizos");
    private final JButton btnIniciarDuelo = new JButton("Iniciar Torneo");

    public VistaPrincipal() {
        setTitle("Torneo de los Tres Magos - ¡Duelo Mágico!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Top buttons panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(45, 35, 65));
        btnCargarMagos.setBackground(new Color(70, 130, 180));
        btnCargarMagos.setForeground(Color.white);
        btnCargarHechizos.setBackground(new Color(70, 130, 180));
        btnCargarHechizos.setForeground(Color.white);
        btnIniciarDuelo.setBackground(new Color(255, 140, 0));
        btnIniciarDuelo.setForeground(Color.white);
        topPanel.add(btnCargarMagos);
        topPanel.add(btnCargarHechizos);
        topPanel.add(btnIniciarDuelo);
        add(topPanel, BorderLayout.NORTH);

        // Panels for magos and hechizos list
        panelMagos.setLayout(new BoxLayout(panelMagos, BoxLayout.Y_AXIS));
        panelMagos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.ORANGE, 2), "Magos Participantes", 0, 0, new Font("Serif", Font.BOLD, 18), Color.ORANGE));
        panelHechizos.setLayout(new BoxLayout(panelHechizos, BoxLayout.Y_AXIS));
        panelHechizos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2), "Hechizos Disponibles", 0, 0, new Font("Serif", Font.BOLD, 18), Color.MAGENTA));
        JScrollPane scrollMagos = new JScrollPane(panelMagos);
        JScrollPane scrollHechizos = new JScrollPane(panelHechizos);
        scrollMagos.setPreferredSize(new Dimension(380, 420));
        scrollHechizos.setPreferredSize(new Dimension(380, 420));

        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.add(scrollMagos);
        centerPanel.add(scrollHechizos);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom action label
        lblAccion.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblAccion.setOpaque(true);
        lblAccion.setBackground(new Color(60, 60, 100));
        lblAccion.setForeground(Color.white);
        lblAccion.setPreferredSize(new Dimension(780, 50));
        lblAccion.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblAccion, BorderLayout.SOUTH);
    }

    public JButton getBtnCargarMagos() { return btnCargarMagos; }
    public JButton getBtnCargarHechizos() { return btnCargarHechizos; }
    public JButton getBtnIniciarDuelo() { return btnIniciarDuelo; }

    public void mostrarMagos(List<Mago> magos) {
        panelMagos.removeAll();
        for (Mago m : magos) {
            JPanel p = crearPanelMago(m);
            panelMagos.add(p);
        }
        panelMagos.revalidate();
        panelMagos.repaint();
    }

    public void mostrarHechizos(List<Hechizo> hechizos) {
        panelHechizos.removeAll();
        for (Hechizo h : hechizos) {
            JLabel label = new JLabel(h.toString());
            label.setFont(new Font("Dialog", Font.ITALIC, 14));
            label.setForeground(Color.MAGENTA.darker());
            panelHechizos.add(label);
        }
        panelHechizos.revalidate();
        panelHechizos.repaint();
    }

    public void mostrarAccion(String texto) {
        lblAccion.setText(texto);
    }

    private JPanel crearPanelMago(Mago mago) {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel nombre = new JLabel("Nombre: " + mago.getNombre());
        nombre.setFont(new Font("Serif", Font.BOLD, 16));
        nombre.setForeground(Color.ORANGE.darker());

        JLabel casa = new JLabel("Casa: " + mago.getCasa());
        casa.setFont(new Font("Serif", Font.PLAIN, 14));
        casa.setForeground(Color.WHITE);

        JProgressBar barraPuntos = new JProgressBar(0, 300);
        barraPuntos.setValue(mago.getPuntos());
        barraPuntos.setStringPainted(true);
        barraPuntos.setForeground(new Color(255, 140, 0)); // naranja
        barraPuntos.setBackground(Color.DARK_GRAY);
        barraPuntos.setBorder(BorderFactory.createLineBorder(Color.ORANGE));

        JLabel hechizos = new JLabel("Hechizos lanzados: " + mago.getCantidadHechizos());
        hechizos.setFont(new Font("Serif", Font.PLAIN, 14));
        hechizos.setForeground(Color.WHITE);

        p.setBackground(new Color(40, 40, 60));
        p.add(nombre);
        p.add(casa);
        p.add(barraPuntos);
        p.add(hechizos);

        return p;
    }
}
