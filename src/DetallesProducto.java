import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DetallesProducto extends Herramientas {

    // Constructor que recibe los detalles del producto y el carrito
    public DetallesProducto(String nombre, String precio, String rutaImagen, List<String> carrito) {
        // Crear ventana
        JFrame ventanaDetalles = crearVentana();
        JPanel panelDetalles = crearPanel();
        panelDetalles.setBackground(Color.PINK);
        panelDetalles.setLayout(null);

        // Mostrar imagen en grande
        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(rutaImagen);
        Image imagen = imagenIcon.getImage();
        Image imagenEscalada = imagen.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Imagen en grande
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        imagenLabel.setBounds(80, 30, 200, 200); // Centrada en la parte superior
        panelDetalles.add(imagenLabel);

        // Mostrar nombre del producto
        JLabel nombreLabel = crearEtiqueta();
        nombreLabel.setText(nombre);
        nombreLabel.setBounds(80, 250, 200, 30);
        panelDetalles.add(nombreLabel);

        // Mostrar precio del producto
        JLabel precioLabel = crearEtiqueta();
        precioLabel.setText(precio);
        precioLabel.setBounds(80, 290, 200, 30);
        panelDetalles.add(precioLabel);

        // Crear panel desplegable para seleccionar talla
        String[] tallas = {"S", "M", "L", "XL"};
        JComboBox<String> tallasComboBox = new JComboBox<>(tallas);
        tallasComboBox.setBounds(80, 340, 200, 30);
        panelDetalles.add(tallasComboBox);

        // Botón para añadir al carrito
        JButton anadirCarritoButton = new JButton("Añadir al carrito");
        anadirCarritoButton.setBounds(80, 390, 200, 40);
        panelDetalles.add(anadirCarritoButton);

        // Acción para añadir al carrito
        anadirCarritoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la talla seleccionada
                String tallaSeleccionada = (String) tallasComboBox.getSelectedItem();

                // Formato: nombre;precio;rutaImagen;talla
                String producto = nombre + ";" + precio + ";" + rutaImagen + ";" + tallaSeleccionada;

                carrito.add(producto);  // Añadir el producto al carrito
                JOptionPane.showMessageDialog(ventanaDetalles, "Producto añadido al carrito");
            }
        });


        // Botón para cerrar solo la ventana de detalles
        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.setBounds(80, 450, 200, 40);
        panelDetalles.add(cerrarButton);

        // Acción para cerrar la ventana de detalles sin cerrar la principal
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaDetalles.dispose();  // Solo cierra la ventana de detalles
            }
        });

        // Agregar panel a la ventana y hacerla visible
        ventanaDetalles.add(panelDetalles);
        ventanaDetalles.setVisible(true);
    }
}

