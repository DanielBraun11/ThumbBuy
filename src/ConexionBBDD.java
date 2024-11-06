import javax.swing.*;
import java.sql.*;

public class ConexionBBDD {

    private Connection connection;

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
