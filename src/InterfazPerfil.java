import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazPerfil extends Herramientas {

    public InterfazPerfil(String nombre_usuario) {
        // CREACION DE LA VENTANA
        JFrame ventanaPerfil = crearVentana();

        // CREACION PANEL DE INICIO
        JPanel panelPerfil = crearPanel();
        panelPerfil.setBackground(Color.PINK);
        ventanaPerfil.add(panelPerfil);

        // CREAR Y AGREGAR UNA IMAGEN EN EL ENCABEZADO
        JLabel imagenEncabezadoPerfil = crearEtiqueta();
        imagenEncabezadoPerfil.setIcon(new ImageIcon("Imagenes/prueba.png"));
        imagenEncabezadoPerfil.setBounds(52, 30, 400, 250);
        panelPerfil.add(imagenEncabezadoPerfil);


        //MOSTRAR LOS DATOS DEL PERFIL

        //NOMBRE
        JLabel nombreEtiqueta = crearEtiqueta();
        nombreEtiqueta.setText("NOMBRE: ");
        nombreEtiqueta.setBounds(40, 330, 100, 25);
        panelPerfil.add(nombreEtiqueta);

        //CORREO
        JLabel correoEtiqueta = crearEtiqueta();
        correoEtiqueta.setText("CORREO: ");
        correoEtiqueta.setBounds(40, 380, 100, 25);
        panelPerfil.add(correoEtiqueta);

        //TELEFONO
        JLabel telefonoEtiqueta = crearEtiqueta();
        telefonoEtiqueta.setText("TELEFONO: ");
        telefonoEtiqueta.setBounds(40, 430, 100, 25);
        panelPerfil.add(telefonoEtiqueta);

        //CONTRASEÑA
        JLabel contraseniaEtiqueta = crearEtiqueta();
        contraseniaEtiqueta.setText("CONTRASEÑA: ");
        contraseniaEtiqueta.setBounds(40, 480, 100, 25);
        panelPerfil.add(contraseniaEtiqueta);

        //BOTÓN PARA VOLVER AL MENU PRINCIPAL
        JButton volver = crearBotones();
        volver.setBounds(180, 550, 120, 30);
        volver.setText("Volver");
        panelPerfil.add(volver);

        // CREACION DEL ACTION LISTENER DEL BOTON VOLVER(VOLVER AL MENU PRINCIPAL)
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPerfil.dispose();



            }
        });

        //---------------------------------------------------------------------------------
        //OBTENER Y MOSTRAR LOS DATOS - los recojo de la clase InterfazRegistro / BBDD

        //NOMBRE
        JLabel nombreEtiquetaDato = crearEtiqueta();
        nombreEtiquetaDato.setText("");
        nombreEtiquetaDato.setBounds(40, 330, 100, 25);
        panelPerfil.add(nombreEtiquetaDato);






        // DAR VISIBILIDAD A LA VENTANA
        ventanaPerfil.setVisible(true);
    }

}



