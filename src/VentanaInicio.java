import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase {@code InterfazInicio} representa la ventana de inicio del programa,
 * donde los usuarios pueden iniciar sesión o registrarse.
 * <p>
 * Incluye campos para el ingreso del nombre de usuario y contraseña, además de opciones
 * para aceptar términos y condiciones.
 * <p>
 * Utiliza herencia de la clase {@code Herramientas} para crear componentes gráficos.
 */
public class VentanaInicio extends Herramientas {

    /**
     * Constructor de la clase {@code InterfazInicio}.
     * <p>
     * Inicializa y configura todos los componentes de la interfaz gráfica,
     * incluyendo etiquetas, campos de texto, botones y paneles.
     */
    public VentanaInicio() {
        // CREACIÓN DE LA VENTANA
        JFrame ventanaInicio = crearVentana();

        // CREACIÓN DEL PANEL DE INICIO
        JPanel panelInicial = crearPanel();
        panelInicial.setBackground(Color.PINK); // Color de fondo de la interfaz
        ventanaInicio.add(panelInicial);

        // CREACIÓN DE LOGOTIPO
        JLabel logo = crearEtiqueta();
        logo.setIcon(new ImageIcon("Imagenes/Logo.png")); // Imagen del logotipo
        logo.setBounds(70, 20, 400, 250); // Posición y tamaño del logotipo
        panelInicial.add(logo);

        // ICONO DE SOBRE (Representación visual de correo electrónico)
        JLabel sobre = crearEtiqueta();
        sobre.setBounds(10, 320, 25, 25);
        ImageIcon iconoSobre = new ImageIcon("Imagenes/sobre_inicio.png");
        Image imagenSobre = iconoSobre.getImage();
        Image imagenSobreEscalada = imagenSobre.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        sobre.setIcon(new ImageIcon(imagenSobreEscalada));
        panelInicial.add(sobre);

        // ICONO DE CANDADO (Representación visual de seguridad)
        JLabel candado = crearEtiqueta();
        candado.setBounds(10, 380, 25, 25);
        ImageIcon iconoCandado = new ImageIcon("Imagenes/candado_inicio.png");
        Image imagenCandado = iconoCandado.getImage();
        Image imagenCandadoEscalada = imagenCandado.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        candado.setIcon(new ImageIcon(imagenCandadoEscalada));
        panelInicial.add(candado);

        // CREACIÓN DE CAMPOS DE TEXTO PARA NOMBRE DE USUARIO
        JLabel nombreEtiqueta = crearEtiqueta();
        nombreEtiqueta.setText("NOMBRE DE USUARIO: ");
        nombreEtiqueta.setBounds(40, 290, 150, 25);
        panelInicial.add(nombreEtiqueta);

        JTextArea nombre = crearAreaTexto();
        nombre.setBounds(40, 320, 280, 25);
        panelInicial.add(nombre);

        // CREACIÓN DE CAMPO PARA CONTRASEÑA
        JLabel contraseniaEtiqueta = crearEtiqueta();
        contraseniaEtiqueta.setText("CONTRASEÑA: ");
        contraseniaEtiqueta.setBounds(40, 350, 100, 25);
        panelInicial.add(contraseniaEtiqueta);

        JPasswordField contrasenia = new JPasswordField(); // Campo para ingresar la contraseña de forma oculta
        contrasenia.setBounds(40, 380, 180, 25);
        panelInicial.add(contrasenia);

        // BOTÓN PARA MOSTRAR/OCULTAR CONTRASEÑA
        JButton verContraseniaButton = new JButton();
        verContraseniaButton.setBounds(230, 380, 25, 25);
        ImageIcon iconoOjo = new ImageIcon("Imagenes/ojo_vista.png");
        Image imagenOjo = iconoOjo.getImage();
        Image imagenOjoEscalada = imagenOjo.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        verContraseniaButton.setIcon(new ImageIcon(imagenOjoEscalada));
        panelInicial.add(verContraseniaButton);

        // Configuración del botón para mostrar/ocultar la contraseña
        verContraseniaButton.addActionListener(new ActionListener() {
            private boolean mostrando = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (mostrando) {
                    contrasenia.setEchoChar('●'); // Ocultar contraseña
                } else {
                    contrasenia.setEchoChar((char) 0); // Mostrar contraseña
                }
                mostrando = !mostrando;
            }
        });

        // BOTONES DE INICIO DE SESIÓN Y REGISTRO
        JButton iniciarSesion = crearBotones();
        iniciarSesion.setBounds(40, 450, 120, 50);
        iniciarSesion.setText("Iniciar Sesión");
        panelInicial.add(iniciarSesion);

        JButton registrarse = crearBotones();
        registrarse.setBounds(180, 450, 120, 50);
        registrarse.setText("Registrarme");
        panelInicial.add(registrarse);

        // CHECKBOX PARA ACEPTACIÓN DE TÉRMINOS Y CONDICIONES
        JRadioButton terminosCheck = new JRadioButton("He leído y acepto los términos y condiciones");
        terminosCheck.setBounds(40, 520, 300, 30);
        terminosCheck.setBackground(Color.PINK);
        panelInicial.add(terminosCheck);

        // CONFIGURACIÓN DEL BOTÓN "REGISTRARME"
        registrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaInicio.dispose(); // Cierra la ventana actual
                new VentanaRegistro(); // Abre la interfaz de registro
            }
        });

        // CONFIGURACIÓN DEL BOTÓN "INICIAR SESIÓN"
        iniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!terminosCheck.isSelected()) {
                    // Muestra un mensaje de advertencia si no se aceptan los términos
                    JOptionPane.showMessageDialog(ventanaInicio, "Acepte los términos y condiciones", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    String nombreUsuario = nombre.getText(); // Obtiene el texto ingresado en el campo de nombre
                    String contraseniaUsuario = new String(contrasenia.getPassword()); // Obtiene la contraseña ingresada

                    // Verifica las credenciales en la base de datos
                    ConexionBBDD conexion = new ConexionBBDD();
                    if (conexion.verificarUsuario(nombreUsuario, contraseniaUsuario)) {
                        ventanaInicio.dispose(); // Cierra la ventana de inicio
                        new VentanaPrincipal(nombre.getText()); // Abre la interfaz principal
                    } else {
                        JOptionPane.showMessageDialog(ventanaInicio, "Usuario o contraseña no válidos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        // HACE VISIBLE LA VENTANA
        ventanaInicio.setVisible(true);
    }
}
