import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase General.DetallesProducto
 *
 * Representa la interfaz gráfica para mostrar los detalles de un producto seleccionado.
 * Permite a los usuarios visualizar la información del producto, seleccionar una talla
 * y añadir el producto al carrito.
 *
 * Esta clase utiliza un {@link Producto} para mostrar la información y un {@link GestorCarrito}
 * para gestionar la funcionalidad de añadir productos al carrito.
 *
 * @author Daniel Braun Sandino

 */

public class DetallesProducto extends Herramientas {

    /**
     * Constructor de la clase General.DetallesProducto.
     *
     * Crea y muestra una interfaz gráfica que contiene:
     * - Una imagen ampliada del producto.
     * - El nombre y precio del producto.
     * - Un menú desplegable para seleccionar la talla.
     * - Un botón para añadir el producto al carrito.
     * - Un botón para cerrar la ventana de detalles.
     *
     * @param producto       el producto cuyas propiedades se mostrarán en la interfaz.
     * @param gestorCarrito  el objeto que gestiona el carrito de compras y permite añadir productos.
     */

    public DetallesProducto(Producto producto, GestorCarrito gestorCarrito) {
        // Crear ventana
        JFrame ventanaDetalles = crearVentana();
        JPanel panelDetalles = crearPanel();
        panelDetalles.setBackground(Color.PINK);
        panelDetalles.setLayout(null);

        // Mostrar imagen en grande
        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(producto.getRutaImagen());
        Image imagen = imagenIcon.getImage();
        Image imagenEscalada = imagen.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Imagen en grande
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        imagenLabel.setBounds(80, 30, 200, 200); // Centrada en la parte superior
        panelDetalles.add(imagenLabel);

        // Mostrar nombre del producto
        JLabel nombreLabel = crearEtiqueta();
        nombreLabel.setText(producto.getNombre());
        nombreLabel.setBounds(80, 250, 200, 30);
        panelDetalles.add(nombreLabel);

        // Mostrar precio del producto
        JLabel precioLabel = crearEtiqueta();
        precioLabel.setText(producto.getPrecio());
        precioLabel.setBounds(80, 290, 200, 30);
        panelDetalles.add(precioLabel);

        // Crear panel desplegable para seleccionar la talla
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
                String tallaSeleccionada = (String) tallasComboBox.getSelectedItem();
                // Se puede incluir la talla seleccionada en el objeto producto si es necesario
                gestorCarrito.agregarProducto(producto);  // Añadir el producto al carrito
                JOptionPane.showMessageDialog(ventanaDetalles, "General.Producto añadido al carrito");
            }
        });

        // Botón para cerrar solo la ventana de detalles
        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.setBounds(80, 450, 200, 40);
        panelDetalles.add(cerrarButton);

        // Acción para cerrar la ventana de detalles SIN cerrar la principal
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