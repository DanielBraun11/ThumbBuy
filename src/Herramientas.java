import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Herramientas extends ConexionBBDD {
    //METODO PARA CREAR VENTANAS
    public JFrame crearVentana(){
        JFrame ventana = new JFrame();
        ventana.setSize(360, 640);
        ventana.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return ventana;
    }

    // Método para crear el panel principal
    public JPanel crearPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);  // Importante para usar setBounds
        return panel;
    }

    // Método para crear etiquetas (logo)
    public JLabel crearEtiqueta() {
        JLabel etiqueta = new JLabel();
        return etiqueta;
    }

    // Método para crear áreas de texto
    public JTextArea crearAreaTexto() {
        JTextArea areaTexto = new JTextArea();
        areaTexto.setBackground(Color.WHITE);  // Color de fondo del área de texto
        areaTexto.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Borde alrededor del área de texto
        return areaTexto;
    }

    //Método para crear botones
    public JButton crearBotones(){
        JButton boton = new JButton();
        boton.setEnabled(true);
        return boton;
    }


}
