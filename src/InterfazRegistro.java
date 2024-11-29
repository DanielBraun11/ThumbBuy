import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase {@code InterfazRegistro} representa la interfaz gráfica para que los usuarios
 * se registren en la aplicación. Incluye campos para ingresar nombre de usuario,
 * contraseña, correo y teléfono, además de botones para aceptar o rechazar el registro.
 *
 * <p>Características principales:</p>
 * <ul>
 *     <li>Encabezado con imagen y mensaje de bienvenida con animación de "fade in".</li>
 *     <li>Campos de entrada para los datos del usuario.</li>
 *     <li>Validación de campos obligatorios antes de procesar el registro.</li>
 *     <li>Conexión con la base de datos para verificar nombres de usuario duplicados.</li>
 * </ul>
 */
public class InterfazRegistro extends Herramientas {

    /**
     * Constructor de la clase {@code InterfazRegistro}. Crea la ventana de registro,
     * configura los componentes gráficos y define las acciones asociadas a los botones.
     */
    public InterfazRegistro() {
        // CREACION DE LA VENTANA
        JFrame ventanaRegistro = crearVentana();

        // CREACION PANEL DE REGISTRO
        JPanel panelRegistro = crearPanel();
        panelRegistro.setBackground(Color.pink);
        ventanaRegistro.add(panelRegistro);

        // CREAR Y AGREGAR UNA IMAGEN EN EL ENCABEZADO
        JLabel imagenEncabezado = crearEtiqueta();
        imagenEncabezado.setIcon(new ImageIcon("Imagenes/Logo.png"));
        imagenEncabezado.setBounds(70, 0, 400, 250);
        panelRegistro.add(imagenEncabezado);

        // TEXTO BIENVENIDA
        JLabel bienvenidaEtiqueta = new JLabel("<html><div style='text-align: center;'>¡Bienvenido a nuestra<br>comunidad!</div></html>");
        bienvenidaEtiqueta.setBounds(40, 250, 280, 60);
        bienvenidaEtiqueta.setFont(new Font("Arial", Font.BOLD, 18));
        bienvenidaEtiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        bienvenidaEtiqueta.setForeground(Color.BLACK);
        panelRegistro.add(bienvenidaEtiqueta);

        // ANIMACION FADE IN
        Timer timer = new Timer(50, new ActionListener() {
            private float opacity = 0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (opacity < 1f) {
                    opacity += 0.05f;
                    bienvenidaEtiqueta.setForeground(new Color(0, 0, 0, (int)(opacity * 255)));
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();

        // CREACION DE TODAS LAS AREAS Y ETIQUETAS DE TEXTO PARA LOS DATOS

        // NOMBRE
        JLabel nombreEtiqueta = crearEtiqueta();
        nombreEtiqueta.setText("NOMBRE DE USUARIO: ");
        nombreEtiqueta.setBounds(40, 330, 150, 25);
        panelRegistro.add(nombreEtiqueta);

        JTextArea nombre = crearAreaTexto();
        nombre.setBounds(40, 350, 280, 25);
        panelRegistro.add(nombre);

        // CONTRASEÑA
        JLabel contraseniaEtiqueta = crearEtiqueta();
        contraseniaEtiqueta.setText("CONTRASEÑA: ");
        contraseniaEtiqueta.setBounds(40, 380, 100, 25);
        panelRegistro.add(contraseniaEtiqueta);

        JTextArea contrasenia = crearAreaTexto();
        contrasenia.setBounds(40, 400, 280, 25);
        panelRegistro.add(contrasenia);

        // CORREO
        JLabel correoEtiqueta = crearEtiqueta();
        correoEtiqueta.setText("CORREO: ");
        correoEtiqueta.setBounds(40, 430, 100, 25);
        panelRegistro.add(correoEtiqueta);

        JTextArea correo = crearAreaTexto();
        correo.setBounds(40, 450, 280, 25);
        panelRegistro.add(correo);

        // TELEFONO
        JLabel telefonoEtiqueta = crearEtiqueta();
        telefonoEtiqueta.setText("TELEFONO: ");
        telefonoEtiqueta.setBounds(40, 480, 100, 25);
        panelRegistro.add(telefonoEtiqueta);

        JTextArea telefono = crearAreaTexto();
        telefono.setBounds(40, 500, 280, 25);
        panelRegistro.add(telefono);

        // CREACION BOTONES DE INICIO DE SESION Y REGISTRO
        JButton iniciarSesion = crearBotones();
        iniciarSesion.setBounds(40, 550, 120, 30);
        iniciarSesion.setText("Aceptar");
        panelRegistro.add(iniciarSesion);

        JButton registrarse = crearBotones();
        registrarse.setBounds(180, 550, 120, 30);
        registrarse.setText("Rechazar");
        panelRegistro.add(registrarse);

        // CREACION DEL ACTION LISTENER DEL BOTON REGISTRO (ACEPTAR)
        iniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si algún campo está vacío
                if (nombre.getText().trim().isEmpty() || contrasenia.getText().trim().isEmpty() ||
                        correo.getText().trim().isEmpty() || telefono.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(ventanaRegistro, "Todos los campos son obligatorios", "Error en el registro", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Crea una instancia de la conexión y verifica si el usuario ya existe
                    ConexionBBDD conexion = new ConexionBBDD();
                    if (conexion.nombreUsuarioExiste(nombre.getText())) {
                        JOptionPane.showMessageDialog(ventanaRegistro, "El nombre de usuario ya está en uso", "Nombre en uso", JOptionPane.WARNING_MESSAGE);
                    } else if (conexion.registrarUsuario(nombre.getText(), contrasenia.getText(), correo.getText(), telefono.getText())) {
                        ventanaRegistro.dispose();
                        new InterfazPrincipal(nombre.getText());
                    } else {
                        JOptionPane.showMessageDialog(ventanaRegistro, "Error al registrar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // CREACION DEL ACTION LISTENER DEL BOTON REGISTRO (RECHAZAR)
        registrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaRegistro.dispose();
                new InterfazInicio();
            }
        });

        // DAR VISIBILIDAD A LA VENTANA
        ventanaRegistro.setVisible(true);
    }
}
