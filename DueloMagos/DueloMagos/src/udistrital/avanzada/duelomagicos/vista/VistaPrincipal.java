package udistrital.avanzada.duelomagicos.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import udistrital.avanzada.duelomagicos.modelo.Hechizo;
import udistrital.avanzada.duelomagicos.modelo.Mago;

/**
 * Vista principal de la aplicación del Torneo de Magos.
 * Muestra la interfaz gráfica con los magos, hechizos y acciones del duelo.
 * 
 * @author Steban
 * @version 1.0
 */
public class VistaPrincipal extends JFrame {
    private final JPanel panelMagos;
    private final JPanel panelHechizos;
    private final JLabel lblAccion;
    private final JButton btnCargarMagos;
    private final JButton btnCargarHechizos;
    private final JButton btnIniciarDuelo;
    
    // Constantes de colores
    private static final Color COLOR_FONDO_OSCURO = new Color(45, 35, 65);
    private static final Color COLOR_BOTON_AZUL = new Color(70, 130, 180);
    private static final Color COLOR_BOTON_NARANJA = new Color(255, 140, 0);
    private static final Color COLOR_PANEL_OSCURO = new Color(40, 40, 60);
    private static final Color COLOR_TEXTO_ACCION = new Color(60, 60, 100);
    
    /**
     * Constructor de la vista principal.
     * Inicializa todos los componentes gráficos.
     */
    public VistaPrincipal() {
        // Configuración básica de la ventana
        setTitle("🔮 Torneo de los Tres Magos - Duelo Mágico ⚡");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);
        
        // Inicializar componentes
        panelMagos = new JPanel();
        panelHechizos = new JPanel();
        lblAccion = new JLabel("⏳ Esperando acción...");
        btnCargarMagos = new JButton("📚 Cargar Magos");
        btnCargarHechizos = new JButton("✨ Cargar Hechizos");
        btnIniciarDuelo = new JButton("⚔️ Iniciar Torneo");
        
        configurarComponentes();
        construirInterfaz();
    }
    
    /**
     * Configura los estilos y propiedades de los componentes
     */
    private void configurarComponentes() {
        // Configurar botones
        configurarBoton(btnCargarMagos, COLOR_BOTON_AZUL);
        configurarBoton(btnCargarHechizos, COLOR_BOTON_AZUL);
        configurarBoton(btnIniciarDuelo, COLOR_BOTON_NARANJA);
        
        // Configurar paneles
        configurarPanelMagos();
        configurarPanelHechizos();
        configurarLabelAccion();
    }
    
    /**
     * Configura un botón con el color especificado
     * @param boton Botón a configurar
     * @param color Color de fondo del botón
     */
    private void configurarBoton(JButton boton, Color color) {
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    /**
     * Configura el panel de magos
     */
    private void configurarPanelMagos() {
        panelMagos.setLayout(new BoxLayout(panelMagos, BoxLayout.Y_AXIS));
        panelMagos.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.ORANGE, 2),
            "🧙 Magos Participantes",
            0, 0,
            new Font("Serif", Font.BOLD, 18),
            Color.ORANGE
        ));
        panelMagos.setBackground(COLOR_PANEL_OSCURO);
    }
    
    /**
     * Configura el panel de hechizos
     */
    private void configurarPanelHechizos() {
        panelHechizos.setLayout(new BoxLayout(panelHechizos, BoxLayout.Y_AXIS));
        panelHechizos.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.MAGENTA, 2),
            "✨ Hechizos Disponibles",
            0, 0,
            new Font("Serif", Font.BOLD, 18),
            Color.MAGENTA
        ));
        panelHechizos.setBackground(COLOR_PANEL_OSCURO);
    }
    
    /**
     * Configura la etiqueta de acciones
     */
    private void configurarLabelAccion() {
        lblAccion.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblAccion.setOpaque(true);
        lblAccion.setBackground(COLOR_TEXTO_ACCION);
        lblAccion.setForeground(Color.WHITE);
        lblAccion.setPreferredSize(new Dimension(830, 50));
        lblAccion.setHorizontalAlignment(SwingConstants.CENTER);
        lblAccion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    
    /**
     * Construye la estructura de la interfaz gráfica
     */
    private void construirInterfaz() {
        // Panel superior con botones
        JPanel topPanel = crearPanelSuperior();
        add(topPanel, BorderLayout.NORTH);
        
        // Panel central con magos y hechizos
        JPanel centerPanel = crearPanelCentral();
        add(centerPanel, BorderLayout.CENTER);
        
        // Panel inferior con etiqueta de acción
        add(lblAccion, BorderLayout.SOUTH);
    }
    
    /**
     * Crea el panel superior con los botones de control
     * @return Panel superior configurado
     */
    private JPanel crearPanelSuperior() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(COLOR_FONDO_OSCURO);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        topPanel.add(btnCargarMagos);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(btnCargarHechizos);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(btnIniciarDuelo);
        
        return topPanel;
    }
    
    /**
     * Crea el panel central con scroll para magos y hechizos
     * @return Panel central configurado
     */
    private JPanel crearPanelCentral() {
        JScrollPane scrollMagos = new JScrollPane(panelMagos);
        JScrollPane scrollHechizos = new JScrollPane(panelHechizos);
        
        scrollMagos.setPreferredSize(new Dimension(400, 450));
        scrollHechizos.setPreferredSize(new Dimension(400, 450));
        
        scrollMagos.setBorder(BorderFactory.createEmptyBorder());
        scrollHechizos.setBorder(BorderFactory.createEmptyBorder());
        
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        centerPanel.setBackground(COLOR_FONDO_OSCURO);
        centerPanel.add(scrollMagos);
        centerPanel.add(scrollHechizos);
        
        return centerPanel;
    }
    
    /**
     * Obtiene el botón de cargar magos
     * @return Botón de cargar magos
     */
    public JButton getBtnCargarMagos() {
        return btnCargarMagos;
    }
    
    /**
     * Obtiene el botón de cargar hechizos
     * @return Botón de cargar hechizos
     */
    public JButton getBtnCargarHechizos() {
        return btnCargarHechizos;
    }
    
    /**
     * Obtiene el botón de iniciar duelo
     * @return Botón de iniciar duelo
     */
    public JButton getBtnIniciarDuelo() {
        return btnIniciarDuelo;
    }
    
    /**
     * Muestra la lista de magos en la interfaz
     * @param magos Lista de magos a mostrar
     */
    public void mostrarMagos(List<Mago> magos) {
        panelMagos.removeAll();
        
        if (magos != null && !magos.isEmpty()) {
            for (Mago mago : magos) {
                JPanel panelMago = crearPanelMago(mago);
                panelMagos.add(panelMago);
                panelMagos.add(Box.createVerticalStrut(10));
            }
        }
        
        panelMagos.revalidate();
        panelMagos.repaint();
    }
    
    /**
     * Muestra la lista de hechizos en la interfaz
     * @param hechizos Lista de hechizos a mostrar
     */
    public void mostrarHechizos(List<Hechizo> hechizos) {
        panelHechizos.removeAll();
        
        if (hechizos != null && !hechizos.isEmpty()) {
            for (Hechizo hechizo : hechizos) {
                JLabel label = crearLabelHechizo(hechizo);
                panelHechizos.add(label);
                panelHechizos.add(Box.createVerticalStrut(5));
            }
        }
        
        panelHechizos.revalidate();
        panelHechizos.repaint();
    }
    
    /**
     * Muestra un mensaje de acción en la etiqueta inferior
     * @param texto Texto de la acción a mostrar
     */
    public void mostrarAccion(String texto) {
        lblAccion.setText(texto);
    }
    
    /**
     * Crea un panel visual para mostrar información de un mago
     * @param mago Mago a representar
     * @return Panel configurado con la información del mago
     */
    private JPanel crearPanelMago(Mago mago) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.ORANGE, 2),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        panel.setBackground(new Color(50, 50, 70));
        panel.setMaximumSize(new Dimension(360, 120));
        
        // Nombre del mago
        JLabel lblNombre = new JLabel("🧙 " + mago.getNombre());
        lblNombre.setFont(new Font("Serif", Font.BOLD, 16));
        lblNombre.setForeground(Color.ORANGE);
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Casa del mago
        JLabel lblCasa = new JLabel("🏰 Casa: " + mago.getCasa());
        lblCasa.setFont(new Font("Serif", Font.PLAIN, 14));
        lblCasa.setForeground(Color.WHITE);
        lblCasa.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Barra de progreso de puntos
        JProgressBar barraPuntos = new JProgressBar(0, 300);
        barraPuntos.setValue(mago.getPuntos());
        barraPuntos.setString(mago.getPuntos() + " / 250 puntos");
        barraPuntos.setStringPainted(true);
        barraPuntos.setForeground(new Color(255, 140, 0));
        barraPuntos.setBackground(Color.DARK_GRAY);
        barraPuntos.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        barraPuntos.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Hechizos lanzados
        JLabel lblHechizos = new JLabel("✨ Hechizos lanzados: " + mago.getCantidadHechizos());
        lblHechizos.setFont(new Font("Serif", Font.PLAIN, 14));
        lblHechizos.setForeground(Color.WHITE);
        lblHechizos.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Agregar componentes al panel
        panel.add(lblNombre);
        panel.add(Box.createVerticalStrut(5));
        panel.add(lblCasa);
        panel.add(Box.createVerticalStrut(8));
        panel.add(barraPuntos);
        panel.add(Box.createVerticalStrut(5));
        panel.add(lblHechizos);
        
        return panel;
    }
    
    /**
     * Crea una etiqueta para mostrar un hechizo
     * @param hechizo Hechizo a representar
     * @return Etiqueta configurada con información del hechizo
     */
    private JLabel crearLabelHechizo(Hechizo hechizo) {
        JLabel label = new JLabel("⚡ " + hechizo.toString());
        label.setFont(new Font("Dialog", Font.ITALIC, 14));
        label.setForeground(Color.MAGENTA.brighter());
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
}
