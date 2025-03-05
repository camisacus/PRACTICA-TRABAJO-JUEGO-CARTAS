import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * ¡Juguemos al apuntado!
 * Juego con interfaz mejorada, más organizada y con un diseño más fresco y llamativo.
 */
public class FrmJuego extends JFrame {
    
    private JPanel pnlJugador1, pnlJugador2, pnlLateral, pnlControles;
    private JTabbedPane tpJugadores;
    private JButton btnRepartir, btnVerificar;

    private Jugador jugador1 = new Jugador();
    private Jugador jugador2 = new Jugador();
    
    /**
     * Configura la ventana principal del juego con un diseño más profesional.
     */
    public FrmJuego() {
        // Configuración de la ventana principal
        setTitle("¡Juguemos al apuntado!");
        setSize(800, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout());
        
        // Panel lateral con información del juego
        pnlLateral = new JPanel();
        pnlLateral.setLayout(new BoxLayout(pnlLateral, BoxLayout.Y_AXIS));
        pnlLateral.setBackground(new Color(45, 45, 45));
        pnlLateral.setPreferredSize(new Dimension(200, getHeight()));
        
        JLabel lblTitulo = new JLabel("Juguemos al Apuntado!", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        pnlLateral.add(lblTitulo);
        
        // Panel de controles con botones
        pnlControles = new JPanel();
        pnlControles.setLayout(new FlowLayout());
        pnlControles.setBackground(new Color(60, 60, 60));
        
        btnRepartir = new JButton("🎴 Repartir Cartas");
        btnVerificar = new JButton("🔍 Verificar Jugada");
        
        pnlControles.add(btnRepartir);
        pnlControles.add(btnVerificar);
        
        // Pestañas para los jugadores con una distribución más estética
        tpJugadores = new JTabbedPane();
        
        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(34, 177, 76)); // Verde vibrante
        pnlJugador1.setLayout(new FlowLayout());
        
        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(0, 162, 232)); // Azul moderno
        pnlJugador2.setLayout(new FlowLayout());
        
        tpJugadores.addTab("🎭 Martín Estrada", pnlJugador1);
        tpJugadores.addTab("🎩 Raúl Vidal", pnlJugador2);
        
        // Agregar los componentes a la ventana
        add(pnlLateral, BorderLayout.WEST);
        add(pnlControles, BorderLayout.NORTH);
        add(tpJugadores, BorderLayout.CENTER);
        
        // Acciones de los botones
        btnRepartir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repartirCartas();
            }
        });
        
        btnVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarJugador();
            }
        });
    }

    /**
     * Reparte las cartas a los jugadores.
     */
    private void repartirCartas() {
        jugador1.repartir();
        jugador1.mostrar(pnlJugador1);
        jugador2.repartir();
        jugador2.mostrar(pnlJugador2);
    }

    /**
     * Verifica la jugada del jugador seleccionado.
     */
    private void verificarJugador() {
        int pestañaSeleccionada = tpJugadores.getSelectedIndex();
        String mensaje = (pestañaSeleccionada == 0) ? jugador1.getGrupos() : jugador2.getGrupos();
        JOptionPane.showMessageDialog(this, mensaje, "📜 Mano del jugador", JOptionPane.INFORMATION_MESSAGE);
    }
}
