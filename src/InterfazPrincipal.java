import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InterfazPrincipal extends Herramientas {

    private List<String> carrito;

    // Constructor donde se construye la interfaz
    public InterfazPrincipal() {
        carrito = new ArrayList<>();

        // Crear ventana y panelPrincipal principal
        JFrame ventana = crearVentana();
        JPanel panelPrincipal = crearPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(Color.PINK);  // Fondo rosa

        // Agregar productos manualmente
        panelPrincipal.add(crearProductoPanel("Camiseta del Barça", "15.99€", "Imagenes/camisetaBarca.jpeg", 10, 100));
        panelPrincipal.add(crearProductoPanel("Camiseta del Atleti", "29.99€", "Imagenes/camisetaAtleti.jpg", 10, 250));
        panelPrincipal.add(crearProductoPanel("Pantalón Adidas", "9.99€", "Imagenes/pantalonAdidasNegro.jpg", 10, 400));
        panelPrincipal.add(crearProductoPanel("Zapatos Nike", "39.99€", "Imagenes/zapatillasNikeNegras.jpg", 10, 550));

        // Crear un JScrollPane para manejar el desplazamiento
        JScrollPane scrollPane = new JScrollPane(panelPrincipal);
        scrollPane.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());  // Ajustar el tamaño del JScrollPane a la ventana
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Ajustar el tamaño del panelPrincipal según el contenido para que funcione el JScrollPane
        panelPrincipal.setPreferredSize(new Dimension(360, 800));  // Ajustar según la cantidad de productos

        // Crear botón del carrito en la esquina superior derecha
        JButton carritoButton = new JButton();
        ImageIcon carritoIcon = new ImageIcon("Imagenes/Carrito.png");  // Cambia la ruta por la imagen de tu carrito
        Image carritoImg = carritoIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        carritoButton.setIcon(new ImageIcon(carritoImg));
        carritoButton.setBounds(280, 5, 40, 40);  // Botón en la esquina superior derecha
        panelPrincipal.add(carritoButton);

        // Crear botón del perfil al lado del de carrito
        JButton perfilButton = new JButton();
        ImageIcon perfilIcon = new ImageIcon("Imagenes/perfil_logo.jpg");  // Cambia la ruta por la imagen de tu carrito
        Image perfilImg = perfilIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        perfilButton.setIcon(new ImageIcon(perfilImg));
        perfilButton.setBounds(220, 5, 40, 40);  // Botón en la esquina superior derecha
        panelPrincipal.add(perfilButton);

        //BUSCADOR
        JLabel buscadorText = crearEtiqueta();
        buscadorText.setText("BUSCADOR: ");
        buscadorText.setBounds(20, 40, 100, 25);
        panelPrincipal.add(buscadorText);

        // Crear area de texto para el buscador(encabezado)
        JTextArea buscador = crearAreaTexto();
        buscador.setBounds(20, 60, 210, 25);
        panelPrincipal.add(buscador);


        // Acción del botón del carrito
        carritoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la interfaz del carrito
                new Carrito(carrito);
            }
        });

        // Acción del botón del perfil
        perfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la interfaz del perfil
                new InterfazPerfil();
            }
        });

        // Agregar el JScrollPane a la ventana y hacerla visible
        ventana.add(scrollPane);
        ventana.setVisible(true);
    }

    // Método para crear el panel de un producto con posicionamiento manual
    private JPanel crearProductoPanel(String nombre, String precio, String rutaImagen, int x, int y) {
        JPanel productoPanel = new JPanel();
        productoPanel.setLayout(null);  // Para usar setBounds dentro del producto
        productoPanel.setBounds(x, y, 340, 120);  // Posicionamiento y tamaño del producto
        productoPanel.setBackground(Color.PINK);
        productoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde negro

        // Imagen del producto redimensionada
        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(rutaImagen);
        Image imagen = imagenIcon.getImage();  // Obtener la imagen
        Image imagenEscalada = imagen.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        imagenLabel.setBounds(10, 10, 100, 100);  // Posición y tamaño del JLabel
        productoPanel.add(imagenLabel);

        // Etiqueta de precio
        JLabel precioLabel = crearEtiqueta();
        precioLabel.setText(precio);
        precioLabel.setBounds(120, 10, 100, 30);
        productoPanel.add(precioLabel);

        // Etiqueta de nombre
        JLabel nombreLabel = crearEtiqueta();
        nombreLabel.setText(nombre);
        nombreLabel.setBounds(120, 35, 170, 30);
        productoPanel.add(nombreLabel);

        // Botón de selección
        JButton seleccionarButton = new JButton("Seleccionar");
        seleccionarButton.setBounds(120, 70, 150, 30);
        productoPanel.add(seleccionarButton);

        // Acción del botón seleccionar
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir detalles del producto
                new DetallesProducto(nombre, precio, rutaImagen, carrito);
            }
        });

        return productoPanel;
    }
}


