import javax.swing.*;
import java.sql.*;

public class ConexionBBDD {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public ConexionBBDD() {
        try {
            String url = "jdbc:mysql://localhost:3306/thumbbuy";
            String usuario = "root";
            String contrasenia = "D2304bs11!";

            // Establecer la conexión
            connection = DriverManager.getConnection(url, usuario, contrasenia);
            //System.out.println("Conexión exitosa a la base de datos.");         //ACTIVAR ANTES DE PRESENTAR POR SI ACASO!!!!
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar a la base de datos.");
        }
    }

    // Método para verificar si el usuario y la contraseña están registrados
    public boolean verificarUsuario(String nombre, String contrasenia) {
        boolean usuarioRegistrado = false;
        String consultaSQL = "SELECT * FROM datosusuarios WHERE nombre_usuario = ? AND contraseña = ?";

        try {
            preparedStatement = connection.prepareStatement(consultaSQL);
            preparedStatement.setString(1, nombre);         // Asigna el nombre al primer ?
            preparedStatement.setString(2, contrasenia);     // Asigna la contraseña al segundo ?

            ResultSet resultado = preparedStatement.executeQuery();

            // Si hay un resultado, significa que el usuario está registrado
            if (resultado.next()) {
                usuarioRegistrado = true;
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al realizar la consulta.");
        } finally {
            // Cerrar recursos
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuarioRegistrado;
    }

    public void registrarUsuario(String nombre, String contrasenia, String correo, String telefono) {
        String consultaVerificacion = "SELECT * FROM datosusuarios WHERE nombre_usuario = ?";
        String consultaSQL = "INSERT INTO datosusuarios (nombre_usuario, contraseña, correo, telefono) VALUES (?, ?, ?, ?)";

        try {
            // Verificar si el nombre de usuario ya existe
            preparedStatement = connection.prepareStatement(consultaVerificacion);
            preparedStatement.setString(1, nombre);
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next()) {
                JOptionPane.showMessageDialog(null, "Error: El nombre de usuario ya está en uso.");
            } else {
                // Si no existe, registrar al usuario
                preparedStatement = connection.prepareStatement(consultaSQL);
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, contrasenia);
                preparedStatement.setString(3, correo);
                preparedStatement.setString(4, telefono);

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
                new InterfazPrincipal("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario.");
        } finally {
            // Cerrar recursos
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
