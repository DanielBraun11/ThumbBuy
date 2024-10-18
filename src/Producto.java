public class Producto {
    private String nombre;
    private String precio;
    private String rutaImagen;

    // Constructor
    public Producto(String nombre, String precio, String rutaImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }
}

