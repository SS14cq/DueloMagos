package udistrital.avanzada.duelomagicos.control;

import java.util.List;
import javax.swing.SwingUtilities;
import udistrital.avanzada.duelomagicos.modelo.Hechizo;
import udistrital.avanzada.duelomagicos.modelo.Mago;
import udistrital.avanzada.duelomagicos.vista.VistaPrincipal;

public class ControladorTorneo {
    private VistaPrincipal vista;

    public ControladorTorneo(VistaPrincipal vista) {
        this.vista = vista;
    }

    public Mago ejecutarTorneo(List<Mago> magos, List<Hechizo> hechizos) {
        if (magos == null || magos.size() < 2 || hechizos == null || hechizos.isEmpty()) return null;

        Mago ganador = magos.get(0);

        for (int i = 1; i < magos.size(); i++) {
            Mago desafiante = magos.get(i);

            ganador.resetPuntos();
            ganador.resetCantidadHechizos();
            desafiante.resetPuntos();
            desafiante.resetCantidadHechizos();

            // Mensaje inicio duelo
            final String nombreGanadorActual = ganador.getNombre();
            final String nombreDesafianteActual = desafiante.getNombre();
            SwingUtilities.invokeLater(() ->
                vista.mostrarAccion("Inicia duelo entre " + nombreGanadorActual + " y " + nombreDesafianteActual)
            );

            Runnable refrescarMagos = () -> SwingUtilities.invokeLater(() -> vista.mostrarMagos(magos));

            DueloRunnable duelo1 = new DueloRunnable(ganador, desafiante, hechizos,
                (m, n) -> vista.mostrarAccion("El mago " + m + " lanza " + n),
                refrescarMagos);

            DueloRunnable duelo2 = new DueloRunnable(desafiante, ganador, hechizos,
                (m, n) -> vista.mostrarAccion("El mago " + m + " lanza " + n),
                refrescarMagos);

            Thread hilo1 = new Thread(duelo1);
            Thread hilo2 = new Thread(duelo2);

            hilo1.start();
            hilo2.start();

            while (ganador.getPuntos() < 250 && desafiante.getPuntos() < 250) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }

            hilo1.interrupt();
            hilo2.interrupt();

            try {
                hilo1.join();
                hilo2.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Obtener resultados con variables effectively final para la lambda
            String nombreGanadorFinal;
            int puntosGanadorFinal;
            if (ganador.getPuntos() > desafiante.getPuntos()) {
                nombreGanadorFinal = ganador.getNombre();
                puntosGanadorFinal = ganador.getPuntos();
            } else {
                nombreGanadorFinal = desafiante.getNombre();
                puntosGanadorFinal = desafiante.getPuntos();
            }
            final String ganadorMostrar = nombreGanadorFinal;
            final int puntosMostrar = puntosGanadorFinal;

            SwingUtilities.invokeLater(() -> {
                vista.mostrarAccion("Fin del duelo. Ganador: " +
                        ganadorMostrar +
                        ". Puntos: " + puntosMostrar);
            });

            if (desafiante.getPuntos() > ganador.getPuntos()) {
                ganador = desafiante;
            }

            try {
                Thread.sleep(1500); // Pausa para que usuario vea el estado
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return ganador;
    }
}
