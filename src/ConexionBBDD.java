import javax.swing.*;
import java.sql.*;

/**
 * La clase <code>ConexionBBDD</code> proporciona métodos para gestionar la conexión con una base de datos MySQL
 * y realizar operaciones comunes como verificar usuarios, registrar nuevos usuarios, obtener datos y cerrar la conexión.
 */
public class ConexionBBDD {

    private Connection connection;

    /**
     * Constructor que establece una conexión con la base de datos.
     * Configura la conexión utilizando los datos de acceso a la base de datos.
     */
    public ConexionBBDD() {
        try {
            String url = "jdbc:mysql://localhost:3306/thumbbuy";
            String usuario = "root";
            String contrasenia = "D2304bs11!";
            connection = DriverManager.getConnection(url, usuario, contrasenia);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar a la base de datos.");
        }
    }

    /**
     * Verifica si un usuario con las credenciales proporcionadas existe en la base de datos.
     *
     * @param nombreUsuario El nombre del usuario.
     * @param contrasena La contraseña del usuario.
     * @return <code>true</code> si las credenciales son válidas, <code>false</code> en caso contrario.
     */
    public boolean verificarUsuario(String nombreUsuario, String contrasena) {
        String consultaSQL = "SELECT nombre_usuario FROM datosusuarios WHERE nombre_usuario = ? AND contraseña = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setString(1, nombreUsuario);
            preparedStatement.setString(2, contrasena);
            ResultSet resultado = preparedStatement.executeQuery();
            return resultado.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param nombre El nombre del usuario.
     * @param contrasena La contraseña del usuario.
     * @param correo El correo electrónico del usuario.
     * @param telefono El número de teléfono del usuario.
     * @return <code>true</code> si el registro fue exitoso, <code>false</code> en caso de error.
     */
    public boolean registrarUsuario(String nombre, String contrasena, String correo, String telefono) {
        String consultaSQL = "INSERT INTO datosusuarios (nombre_usuario, contraseña, correo, telefono) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, contrasena);
            preparedStatement.setString(3, correo);
            preparedStatement.setString(4, telefono);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene los datos de un usuario específico desde la base de datos.
     *
     * @param nombreUsuario El nombre del usuario.
     * @return Un arreglo de <code>String</code> que contiene los datos del usuario:
     *         nombre, correo, teléfono y contraseña. Si el usuario no existe, el arreglo estará vacío.
     */
    public String[] obtenerDatosUsuario(String nombreUsuario) {
        String[] datosUsuario = new String[4];
        String consultaSQL = "SELECT nombre_usuario, correo, telefono, contraseña FROM datosusuarios WHERE nombre_usuario = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setString(1, nombreUsuario);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado.next()) {
                datosUsuario[0] = resultado.getString("nombre_usuario");
                datosUsuario[1] = resultado.getString("correo");
                datosUsuario[2] = resultado.getString("telefono");
                datosUsuario[3] = resultado.getString("contraseña");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron datos para el usuario: " + nombreUsuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener los datos del usuario.");
        }
        return datosUsuario;
    }

    /**
     * Comprueba si un nombre de usuario ya existe en la base de datos.
     *
     * @param nombreUsuario El nombre del usuario a verificar.
     * @return <code>true</code> si el nombre de usuario ya existe, <code>false</code> en caso contrario.
     */
    public boolean nombreUsuarioExiste(String nombreUsuario) {
        String consultaSQL = "SELECT COUNT(*) FROM datosusuarios WHERE nombre_usuario = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setString(1, nombreUsuario);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado.next()) {
                return resultado.getInt(1) > 0; // Si el contador es mayor que 0, el nombre existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si no se encuentra ningún resultado, el nombre no existe
    }

    /**
     * Cierra la conexión a la base de datos si está abierta.
     */
    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
