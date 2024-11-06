import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InterfazPrincipal extends Herramientas {

    private List<JPanel> productoPanels;
    private List<Producto> carrito;  // Lista de productos en el carrito
    private Producto[] productos;

    public InterfazPrincipal(String nombre_usuario) {
        carrito = new ArrayList<>();
        productoPanels = new ArrayList<>();

        // Crear ventana y panel principal
        JFrame ventana = crearVentana();
        JPanel panelPrincipal = crearPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(Color.PINK);

        // Crear lista de productos
        productos = new Producto[]{
                new Producto("Camiseta del Barça", "15.99€", "Imagenes/camisetaBarca.jpeg"),
                new Producto("Camiseta del Atleti", "29.99€", "Imagenes/camisetaAtleti.jpg"),
                new Producto("Pantalon Adidas", "9.99€", "Imagenes/pantalonAdidasNegro.jpg"),
                new Producto("Zapatos Nike", "39.99€", "Imagenes/zapatillasNikeNegras.jpg")
        };

        // Añadir productos a la interfaz
        int yPos = 100;
        for (Producto producto : productos) {
            agregarProducto(panelPrincipal, producto, 10, yPos);
            yPos += 150;
        }

        JScrollPane scrollPane = new JScrollPane(panelPrincipal);
        scrollPane.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelPrincipal.setPreferredSize(new Dimension(360, 800));

        //Imagen Frame Logo
        JLabel FrameLogo = crearEtiqueta();
        FrameLogo.setBounds(5, 5, 240, 40);

        ImageIcon iconoFrameLogo = new ImageIcon("Imagenes/Frame Logo.png");
        Image imagenFrameLogo = iconoFrameLogo.getImage();
        Image imagenFrameLogoEscalada = imagenFrameLogo.getScaledInstance(240, 50, Image.SCALE_SMOOTH);
        FrameLogo.setIcon(new ImageIcon(imagenFrameLogoEscalada));

        panelPrincipal.add(FrameLogo);

        // Crear botón del carrito en la esquina superior derecha
        JButton carritoButton = new JButton();
        ImageIcon carritoIcon = new ImageIcon("Imagenes/Carrito.png");  // Imagen del carrito
        Image carritoImg = carritoIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        carritoButton.setIcon(new ImageIcon(carritoImg));
        carritoButton.setBounds(287, 5, 40, 40);  // Botón en la esquina superior derecha
        panelPrincipal.add(carritoButton);

        // Acción del botón del carrito
        carritoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Carrito(carrito); // Cambia a la clase que maneja la interfaz del carrito
            }
        });

        // Crear botón del perfil al lado del de carrito
        JButton perfilButton = new JButton();
        ImageIcon perfilIcon = new ImageIcon("Imagenes/perfil_logo.jpg");  // Imagen del perfil
        Image perfilImg = perfilIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        perfilButton.setIcon(new ImageIcon(perfilImg));
        perfilButton.setBounds(245, 5, 40, 40);  // Botón en la esquina superior derecha
        panelPrincipal.add(perfilButton);

        perfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new InterfazPerfil(nombre_usuario); // Cambia a la clase que maneja la interfaz de perfil
            }
        });

        // Buscador
        JTextArea buscador = new JTextArea();
        buscador.setBounds(20, 60, 210, 25);
        panelPrincipal.add(buscador);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(230, 60, 90, 25);
        panelPrincipal.add(buscarButton);

        // Acción del botón de buscar
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoBusqueda = buscador.getText().trim();
                if (!textoBusqueda.isEmpty()) {
                    List<Producto> productosFiltrados = buscarProductos(textoBusqueda);
                    new ProductosBuscados(productosFiltrados, carrito);
                }
            }
        });

        ventana.add(scrollPane);
        ventana.setVisible(true);
    }

    //-----------------------------------------------------------------------------------

    // Método que filtra los productos según el nombre
    private List<Producto> buscarProductos(String nombre) {
        List<Producto> productosFiltrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                productosFiltrados.add(producto);
            }
        }
        return productosFiltrados;
    }

    private void agregarProducto(JPanel panelPrincipal, Producto producto, int x, int y) {
        JPanel productoPanel = crearProductoPanel(producto, x, y);
        panelPrincipal.add(productoPanel);
        productoPanels.add(productoPanel);
    }

    private JPanel crearProductoPanel(Producto producto, int x, int y) {
        JPanel productoPanel = new JPanel();
        productoPanel.setLayout(null);
        productoPanel.setBounds(x, y, 340, 120);
        productoPanel.setBackground(Color.PINK);
        productoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(producto.getRutaImagen());
        Image imagen = imagenIcon.getImage();
        Image imagenEscalada = imagen.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        imagenLabel.setBounds(10, 10, 100, 100);
        productoPanel.add(imagenLabel);

        JLabel precioLabel = crearEtiqueta();
        precioLabel.setText(producto.getPrecio());
        precioLabel.setBounds(120, 10, 100, 30);
        productoPanel.add(precioLabel);

        JLabel nombreLabel = crearEtiqueta();
        nombreLabel.setText(producto.getNombre());
        nombreLabel.setBounds(120, 35, 170, 30);
        productoPanel.add(nombreLabel);

        JButton seleccionarButton = new JButton("Seleccionar");
        seleccionarButton.setBounds(120, 70, 150, 30);
        productoPanel.add(seleccionarButton);

        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DetallesProducto(producto, carrito);
            }
        });

        return productoPanel;
    }
}
