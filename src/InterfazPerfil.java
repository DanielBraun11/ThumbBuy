import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazPerfil extends Herramientas {

    public InterfazPerfil() {
        // CREACION DE LA VENTANA
        JFrame ventanaPerfil = crearVentana();

        // CREACION PANEL DE INICIO
        JPanel panelPerfil = crearPanel();
        panelPerfil.setBackground(Color.PINK);
        ventanaPerfil.add(panelPerfil);

        // CREAR Y AGREGAR UNA IMAGEN EN EL ENCABEZADO
        JLabel imagenEncabezadoPerfil = crearEtiqueta(); // Reemplaza con la ruta de tu imagen
        imagenEncabezadoPerfil.setIcon(new ImageIcon("Imagenes/icono_perfil.png"));
        imagenEncabezadoPerfil.setBounds(70, 0, 400, 250);
        panelPerfil.add(imagenEncabezadoPerfil);




        // DAR VISIBILIDAD A LA VENTANA
        ventanaPerfil.setVisible(true);
    }

}



