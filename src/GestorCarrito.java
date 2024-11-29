import java.util.ArrayList;
import java.util.List;

/**
 * La clase <code>GestorCarrito</code> gestiona el carrito de compras, permitiendo agregar y eliminar productos,
 * así como obtener la lista de productos actuales y contar el número de productos en el carrito.
 */
public class GestorCarrito {
    /**
     * Lista que almacena los productos en el carrito de compras.
     */
    private List<Producto> productos;

    /**
     * Constructor de la clase <code>GestorCarrito</code>.
     * Inicializa la lista de productos vacía.
     */
    public GestorCarrito() {
        this.productos = new ArrayList<>();
    }

    /**
     * Agrega un producto al carrito de compras.
     *
     * @param producto El producto a agregar al carrito.
     */
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    /**
     * Obtiene la lista de productos actualmente en el carrito de compras.
     *
     * @return Lista de productos en el carrito.
     */
    public List<Producto> obtenerProductos() {
        return productos;
    }


}

