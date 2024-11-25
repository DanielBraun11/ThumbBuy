import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductosBuscados extends Herramientas {

    private GestorCarrito gestorCarrito;

    public ProductosBuscados(List<Producto> productosFiltrados, GestorCarrito gestorCarrito) {

        // Utilizar la instancia de GestorCarrito pasada como argumento
        this.gestorCarrito = gestorCarrito;

        // Crear ventana y establecer tamaño
        JFrame ventana = new JFrame();
        ventana.setSize(360, 640);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear panel principal
        JPanel panelPrincipal = crearPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(Color.PINK);

        int yPos = 10; // Posición inicial de los productos
        for (Producto producto : productosFiltrados) {
            agregarProducto(panelPrincipal, producto, 10, yPos);
            yPos += 130; // Incrementar la posición para el siguiente producto
        }

        panelPrincipal.setPreferredSize(new Dimension(340, yPos + 10)); // Ajustar el tamaño del panel

        // Crear un JScrollPane para manejar el desplazamiento
        JScrollPane scrollPane = new JScrollPane(panelPrincipal);
        scrollPane.setBounds(0, 40, 360, 600); // Ajustar el tamaño del JScrollPane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Crear un botón de "Volver"
        JButton volverButton = crearBotones();
        volverButton.setText("Volver");
        volverButton.setBounds(270, 5, 90, 30);

        // Crear un panel para la parte superior con el botón de volver
        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 360, 40);
        topPanel.setBackground(Color.PINK);
        topPanel.add(volverButton);

        // Acción del botón "Volver"
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();  // Cierra la ventana de ProductosBuscados
            }
        });

        // Ajustar el layout de la ventana
        ventana.setLayout(null);
        ventana.add(topPanel);
        ventana.add(scrollPane);
        ventana.setVisible(true);
    }

    private void agregarProducto(JPanel panelPrincipal, Producto producto, int x, int y) {
        JPanel productoPanel = crearProductoPanel(producto, x, y);
        panelPrincipal.add(productoPanel);
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
                new DetallesProducto(producto, gestorCarrito); // Pasar el producto completo y el carrito
            }
        });

        return productoPanel;
    }
}
