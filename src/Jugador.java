import java.util.Arrays;
import java.util.Random;
import javax.swing.JPanel;

public class Jugador {

    private int DISTANCIA = 40;
    private int MARGEN = 10;
    private int TOTAL_CARTAS = 10;
    private String MENSAJE_PREDETERMINADO = "No se encontraron grupos";
    private String ENCABEZADO_MENSAJE = "Se encontraron los siguientes grupos:\n";
    private int MINIMA_CANTIDAD_GRUPO = 2;
    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int x = MARGEN + (TOTAL_CARTAS - 1) * DISTANCIA;
        for (Carta carta : cartas) {
            carta.mostrar(pnl, x, MARGEN);
            x -= DISTANCIA;
        }
        pnl.repaint();
    }

    public String getGrupos() {
        String mensaje = MENSAJE_PREDETERMINADO;

        int[] contadores = new int[NombreCarta.values().length];
        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }

        boolean hayGrupos = false;
        for (int contador : contadores) {
            if (contador >= MINIMA_CANTIDAD_GRUPO) {
                hayGrupos = true;
                break;
            }
        }

        if (hayGrupos) {
            mensaje = ENCABEZADO_MENSAJE;
            int posicion = 0;
            for (int contador : contadores) {
                if (contador >= MINIMA_CANTIDAD_GRUPO) {
                    mensaje += Grupo.values()[contador] + " de " + NombreCarta.values()[posicion] + "\n";
                }
                posicion++;
            }
        }

        // Agregar escaleras encontradas
        mensaje += getEscaleras();
        // Agregar el puntaje calculado
        mensaje += "\nPuntaje total: " + calcularPuntaje();

        return mensaje;
    }

    public String getEscaleras() {
        String mensaje = "";

        for (Pinta pinta : Pinta.values()) {
            int[] valores = new int[13];
            for (Carta carta : cartas) {
                if (carta.getPinta() == pinta) {
                    valores[carta.getNombre().ordinal()] = 1;
                }
            }

            int longitudEscalera = 0;
            String nombresCartas = "";
            for (int i = 0; i < valores.length; i++) {
                if (valores[i] == 1) {
                    longitudEscalera++;
                    nombresCartas += NombreCarta.values()[i] + ", ";
                } else {
                    if (longitudEscalera >= 3) {
                        mensaje += "Escalera de " + pinta + ": " + nombresCartas + "\n";
                    }
                    longitudEscalera = 0;
                    nombresCartas = "";
                }
            }
            if (longitudEscalera >= 3) {
                mensaje += "Escalera de " + pinta + ": " + nombresCartas + "\n";
            }
        }

        return mensaje.isEmpty() ? "No se encontraron escaleras.\n" : mensaje;
    }

    public int calcularPuntaje() {
        int puntaje = 0;
        int[] contadores = new int[NombreCarta.values().length];

        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }

        for (Carta carta : cartas) {
            int valor = carta.getNombre().ordinal() + 1;
            if (valor > 10) valor = 10; // As, J, Q, K valen 10
            if (contadores[carta.getNombre().ordinal()] < 3) { // Solo contar si no está en una figura
                puntaje += valor;
            }
        }

        return puntaje;
    }

}
