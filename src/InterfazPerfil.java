import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazPerfil extends Herramientas {

    public InterfazPerfil(String nombre_usuario) {
        JFrame ventanaPerfil = crearVentana();
        JPanel panelPerfil = crearPanel();
        panelPerfil.setBackground(Color.PINK);
        ventanaPerfil.add(panelPerfil);

        JLabel imagenEncabezadoPerfil = crearEtiqueta();
        imagenEncabezadoPerfil.setIcon(new ImageIcon("Imagenes/prueba.png"));
        imagenEncabezadoPerfil.setBounds(52, 30, 400, 250);
        panelPerfil.add(imagenEncabezadoPerfil);

        ConexionBBDD conexion = new ConexionBBDD();
        String[] datosUsuario = conexion.obtenerDatosUsuario(nombre_usuario);
        conexion.cerrarConexion();

        // Verifica si los datos del usuario fueron recuperados
        if (datosUsuario[0] == null) {
            JOptionPane.showMessageDialog(null, "Error: No se encontraron datos para el usuario.");
            return;
        }

        // Mostrar los datos del perfil
        JLabel nombreEtiqueta = crearEtiqueta();
        nombreEtiqueta.setText("NOMBRE: ");
        nombreEtiqueta.setBounds(40, 330, 100, 25);
        panelPerfil.add(nombreEtiqueta);

        JLabel nombreEtiquetaDato = crearEtiqueta();
        nombreEtiquetaDato.setText(datosUsuario[0]);
        nombreEtiquetaDato.setBounds(150, 330, 200, 25);
        panelPerfil.add(nombreEtiquetaDato);

        JLabel correoEtiqueta = crearEtiqueta();
        correoEtiqueta.setText("CORREO: ");
        correoEtiqueta.setBounds(40, 380, 100, 25);
        panelPerfil.add(correoEtiqueta);

        JLabel correoEtiquetaDato = crearEtiqueta();
        correoEtiquetaDato.setText(datosUsuario[1]);
        correoEtiquetaDato.setBounds(150, 380, 200, 25);
        panelPerfil.add(correoEtiquetaDato);

        JLabel telefonoEtiqueta = crearEtiqueta();
        telefonoEtiqueta.setText("TELEFONO: ");
        telefonoEtiqueta.setBounds(40, 430, 100, 25);
        panelPerfil.add(telefonoEtiqueta);

        JLabel telefonoEtiquetaDato = crearEtiqueta();
        telefonoEtiquetaDato.setText(datosUsuario[2]);
        telefonoEtiquetaDato.setBounds(150, 430, 200, 25);
        panelPerfil.add(telefonoEtiquetaDato);

        JLabel contraseniaEtiqueta = crearEtiqueta();
        contraseniaEtiqueta.setText("CONTRASEÑA: ");
        contraseniaEtiqueta.setBounds(40, 480, 100, 25);
        panelPerfil.add(contraseniaEtiqueta);

        JLabel contraseniaEtiquetaDato = crearEtiqueta();
        contraseniaEtiquetaDato.setText(datosUsuario[3]);
        contraseniaEtiquetaDato.setBounds(150, 480, 200, 25);
        panelPerfil.add(contraseniaEtiquetaDato);

        JButton volver = crearBotones();
        volver.setBounds(180, 550, 120, 30);
        volver.setText("Volver");
        panelPerfil.add(volver);

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPerfil.dispose();
            }
        });

        ventanaPerfil.setVisible(true);
    }
}
