import java.util.ArrayList;
import java.util.List;

public class GestorCarrito {
    private List<Producto> productos;

    public GestorCarrito() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    public List<Producto> obtenerProductos() {
        return productos;
    }

    public int contarProductos() {
        return productos.size();
    }
}

