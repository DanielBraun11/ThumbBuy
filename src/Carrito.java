import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Carrito extends Herramientas {

    // Constructor que recibe la lista de productos en el carrito
    public Carrito(List<Producto> carrito) {
        // Crear ventana para el carrito
        JFrame carritoVentana = new JFrame();
        carritoVentana.setSize(360, 640);
        carritoVentana.setLocationRelativeTo(null);
        carritoVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear panel para los productos del carrito
        JPanel carritoPanel = crearPanel();
        carritoPanel.setLayout(null);
        carritoPanel.setBackground(Color.PINK);

        // Añadir productos al panel del carrito
        int y = 50;
        for (Producto producto : carrito) {
            carritoPanel.add(crearProductoPanel(producto.getNombre(), producto.getPrecio(), producto.getRutaImagen(), 10, y));
            y += 130; // Ajustar la posición para el siguiente producto
        }

        // Establecer tamaño preferido para el panel
        carritoPanel.setPreferredSize(new Dimension(340, y + 10));


        // Crear un JScrollPane para manejar el desplazamiento
        JScrollPane scrollPane = new JScrollPane(carritoPanel);
        scrollPane.setBounds(0, 40, 360, 600); // Ajustar el tamaño del JScrollPane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Crear un botón de "Volver"
        JButton volverButton = crearBotones();
        volverButton.setText("Volver");
        volverButton.setBounds(270, 5, 90, 30);

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carritoVentana.dispose(); // Cierra la ventana del carrito
            }
        });

        // Crear un panel para la parte superior con el botón de volver
        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 360, 90);
        topPanel.setBackground(Color.PINK);
        topPanel.setLayout(null);
        topPanel.add(volverButton);
        //topPanel.setBorder(LineBorder.createBlackLineBorder());

        //Imagen Frame Logo
        JLabel FrameLogo = crearEtiqueta();
        FrameLogo.setBounds(5, 5, 240, 40);

        ImageIcon iconoFrameLogo = new ImageIcon("Imagenes/Frame Logo.png");
        Image imagenFrameLogo = iconoFrameLogo.getImage();
        Image imagenFrameLogoEscalada = imagenFrameLogo.getScaledInstance(240, 50, Image.SCALE_SMOOTH);
        FrameLogo.setIcon(new ImageIcon(imagenFrameLogoEscalada));

        topPanel.add(FrameLogo);

        // BOTÓN PAGAR
        JButton botonPagar = new JButton("Pagar");
        botonPagar.setBounds(0, 50, 120, 30);
        topPanel.add(botonPagar);

        botonPagar.addActionListener(e -> {
            carritoVentana.dispose();
            new InterfazPago();
        });

        // Ajustar el layout de la ventana
        carritoVentana.setLayout(null);
        carritoVentana.add(topPanel);
        carritoVentana.add(scrollPane);
        carritoVentana.setVisible(true);
    }

    // Método para crear el panel de un producto con posicionamiento manual
    private JPanel crearProductoPanel(String nombre, String precio, String rutaImagen, int x, int y) {
        JPanel productoPanel = new JPanel();
        productoPanel.setLayout(null);
        productoPanel.setBounds(x, y, 340, 120);
        productoPanel.setBackground(Color.PINK);
        productoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Imagen del producto redimensionada
        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(rutaImagen);
        Image imagen = imagenIcon.getImage();
        Image imagenEscalada = imagen.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        imagenLabel.setBounds(10, 10, 100, 100);
        productoPanel.add(imagenLabel);

        // Etiqueta de precio
        JLabel precioLabel = crearEtiqueta();
        precioLabel.setText(precio);
        precioLabel.setBounds(120, 10, 100, 30);
        productoPanel.add(precioLabel);

        // Etiqueta de nombre
        JLabel nombreLabel = crearEtiqueta();
        nombreLabel.setText(nombre);
        nombreLabel.setBounds(120, 50, 200, 30);
        productoPanel.add(nombreLabel);

        return productoPanel;
    }
}
