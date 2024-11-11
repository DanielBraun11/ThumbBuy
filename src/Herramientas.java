import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Herramientas extends ConexionBBDD {
    // MÉTODO PARA CREAR VENTANAS
    public JFrame crearVentana(){
        JFrame ventana = new JFrame();
        ventana.setSize(360, 640);
        ventana.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return ventana;
    }

    // MÉTODO PARA CREAR EL PANEL PRINCIPAL
    public JPanel crearPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);  // Importante para usar setBounds
        return panel;
    }

    // MÉTODO PARA CREAR ETIQUETAS (logo u otros)
    public JLabel crearEtiqueta() {
        JLabel etiqueta = new JLabel();
        return etiqueta;
    }

    // MÉTODO PARA CREAR ÁREAS DE TEXTO
    public JTextArea crearAreaTexto() {
        JTextArea areaTexto = new JTextArea();
        areaTexto.setBackground(Color.WHITE);  // Color de fondo del área de texto
        areaTexto.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Borde alrededor del área de texto
        return areaTexto;
    }

    // MÉTODO PARA CREAR CAMPOS DE TEXTO (JTextField)
    public JTextField crearCampoTexto() {
        JTextField campoTexto = new JTextField();
        campoTexto.setBackground(Color.WHITE);  // Fondo blanco para el campo de texto
        campoTexto.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Borde negro
        return campoTexto;
    }

    // MÉTODO PARA CREAR BOTONES
    public JButton crearBotones() {
        JButton boton = new JButton();
        boton.setEnabled(true);
        return boton;
    }

}
