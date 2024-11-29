import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * La clase <code>Herramientas</code> proporciona métodos reutilizables para la creación de elementos de interfaz
 * gráfica (GUI), como ventanas, paneles, etiquetas, áreas de texto, campos de texto y botones.
 * También hereda de <code>ConexionBBDD</code>, lo que permite su integración con funcionalidades relacionadas
 * con bases de datos.
 */
public class Herramientas extends ConexionBBDD {
    /**
     * Crea y configura una ventana estándar con un tamaño predefinido de 360x640 píxeles.
     *
     * @return Un objeto <code>JFrame</code> configurado.
     */
    public JFrame crearVentana(){
        JFrame ventana = new JFrame();
        ventana.setSize(360, 640);
        ventana.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return ventana;
    }

    /**
     * Crea y devuelve un panel principal con un diseño nulo (<code>null layout</code>),
     * lo que permite el uso de <code>setBounds</code> para posicionar componentes manualmente.
     *
     * @return Un objeto <code>JPanel</code> configurado.
     */
    public JPanel crearPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);  // Importante para usar setBounds
        return panel;
    }

    /**
     * Crea y devuelve una etiqueta (<code>JLabel</code>) estándar.
     *
     * @return Un objeto <code>JLabel</code>.
     */
    public JLabel crearEtiqueta() {
        JLabel etiqueta = new JLabel();
        return etiqueta;
    }

    /**
     * Crea y devuelve un área de texto (<code>JTextArea</code>) configurada con un fondo blanco
     * y un borde negro.
     *
     * @return Un objeto <code>JTextArea</code>.
     */
    public JTextArea crearAreaTexto() {
        JTextArea areaTexto = new JTextArea();
        areaTexto.setBackground(Color.WHITE);  // Color de fondo del área de texto
        areaTexto.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Borde alrededor del área de texto
        return areaTexto;
    }

    /**
     * Crea y devuelve un campo de texto (<code>JTextField</code>) configurado con un fondo blanco
     * y un borde negro.
     *
     * @return Un objeto <code>JTextField</code>.
     */
    public JTextField crearCampoTexto() {
        JTextField campoTexto = new JTextField();
        campoTexto.setBackground(Color.WHITE);  // Fondo blanco para el campo de texto
        campoTexto.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Borde negro
        return campoTexto;
    }

    /**
     * Crea y devuelve un botón (<code>JButton</code>) habilitado por defecto.
     *
     * @return Un objeto <code>JButton</code>.
     */
    public JButton crearBotones() {
        JButton boton = new JButton();
        boton.setEnabled(true);
        return boton;
    }

}
