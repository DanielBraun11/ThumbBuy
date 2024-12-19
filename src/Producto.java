/**
 * Clase General.Producto
 *
 * Representa un producto en la tienda online, con información sobre su nombre,
 * precio y la ruta de la imagen asociada.
 * Esta clase proporciona métodos para obtener estos detalles del producto.
 *
 * @author Daniel Braun Sandino
 */

public class Producto {
    private String nombre;
    private String precio;
    private String rutaImagen;

    /**
     * Constructor que inicializa un nuevo producto con su nombre, precio y ruta de imagen.
     *
     * @param nombre      el nombre del producto.
     * @param precio      el precio del producto.
     * @param rutaImagen  la ruta de la imagen asociada al producto.
     */

    public Producto(String nombre, String precio, String rutaImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return el nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return el precio del producto.
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * Obtiene la ruta de la imagen del producto.
     *
     * @return la ruta de la imagen del producto.
     */
    public String getRutaImagen() {
        return rutaImagen;
    }


}
