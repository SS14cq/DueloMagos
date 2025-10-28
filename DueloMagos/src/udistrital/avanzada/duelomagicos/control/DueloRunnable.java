package udistrital.avanzada.duelomagicos.control;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import javax.swing.SwingUtilities;
import udistrital.avanzada.duelomagicos.modelo.Hechizo;
import udistrital.avanzada.duelomagicos.modelo.Mago;

public class DueloRunnable implements Runnable {
    private final Mago atacante;
    private final Mago defensor;
    private final List<Hechizo> hechizos;
    private final BiConsumer<String, String> actualizarVista;
    private final Runnable refrescarMagos;
    private volatile boolean aturdido;
    private final Random random = new Random();

    public DueloRunnable(
            Mago atacante,
            Mago defensor,
            List<Hechizo> hechizos,
            BiConsumer<String, String> actualizarVista,
            Runnable refrescarMagos) {
        this.atacante = atacante;
        this.defensor = defensor;
        this.hechizos = hechizos;
        this.actualizarVista = actualizarVista;
        this.refrescarMagos = refrescarMagos;
        this.aturdido = false;
    }

    public synchronized void setAturdido(boolean estado) {
        this.aturdido = estado;
    }

    public synchronized boolean isAturdido() {
        return aturdido;
    }

    @Override
    public void run() {
        while (atacante.getPuntos() < 250 && defensor.getPuntos() < 250) {
            if (!isAturdido()) {
                Hechizo hechizo = hechizos.get(random.nextInt(hechizos.size()));
                atacante.incrementarHechizos();
                defensor.sumarPuntos(hechizo.getPuntos());
                setAturdido(true);

                SwingUtilities.invokeLater(() -> {
                    actualizarVista.accept(atacante.getNombre(), hechizo.getNombre());
                    refrescarMagos.run();
                });

                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                setAturdido(false);
            }
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
