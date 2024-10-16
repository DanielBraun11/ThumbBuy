import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Carrito extends Herramientas {

    // Constructor que recibe la lista de productos en el carrito
    public Carrito(List<String> carrito) {
        // Crear ventana para el carrito
        JFrame carritoVentana = new JFrame("Carrito de Compras");
        carritoVentana.setSize(360, 640);
        carritoVentana.setLocationRelativeTo(null);
        carritoVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear panel para los productos del carrito
        JPanel carritoPanel = crearPanel();
        carritoPanel.setLayout(null);
        carritoPanel.setBackground(Color.PINK);

        // Añadir productos al panel del carrito
        int y = 10;
        for (String producto : carrito) {
            String[] datos = producto.split(";");
            if (datos.length < 3) continue; // Asegurarse de que los datos sean válidos
            String nombre = datos[0];
            String precio = datos[1];
            String rutaImagen = datos[2];
            carritoPanel.add(crearProductoPanel(nombre, precio, rutaImagen, 10, y));
            y += 130; // Ajustar la posición para el siguiente producto
        }

        // Establecer tamaño preferido para el panel
        carritoPanel.setPreferredSize(new Dimension(340, y + 10)); // Ajustar el tamaño del panel

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
        topPanel.setBounds(0, 0, 360, 40);
        topPanel.setBackground(Color.PINK);
        topPanel.setLayout(null);
        topPanel.add(volverButton);

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

