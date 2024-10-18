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
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar a la base de datos.");
        }
    }

    // Método para verificar si el usuario y la contraseña están registrados
    public boolean verificarUsuario(String nombre, String contrasenia) {
        boolean usuarioRegistrado = false;
        String consultaSQL = "SELECT * FROM datosusuarios WHERE nombre = ? AND contraseña = ?";

        try {
            preparedStatement = connection.prepareStatement(consultaSQL);
            preparedStatement.setString(1, nombre);         // Asigna el nombre al primer ?
            preparedStatement.setString(2, contrasenia);     // Asigna la contraseña al segundo ?

            ResultSet resultado = preparedStatement.executeQuery();

            // Si hay un resultado, significa que el usuario está registrado
            if (resultado.next()) {
                usuarioRegistrado = true;
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
        String consultaVerificacion = "SELECT * FROM datosusuarios WHERE nombre = ?";
        String consultaSQL = "INSERT INTO datosusuarios (nombre, contraseña, correo, telefono) VALUES (?, ?, ?, ?)";

        try {
            // Verificar si el nombre de usuario ya existe
            preparedStatement = connection.prepareStatement(consultaVerificacion);
            preparedStatement.setString(1, nombre);
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next()) {
                System.out.println("Error: El nombre de usuario ya está en uso.");
            } else {
                // Si no existe, registrar al usuario
                preparedStatement = connection.prepareStatement(consultaSQL);
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, contrasenia);
                preparedStatement.setString(3, correo);
                preparedStatement.setString(4, telefono);

                preparedStatement.executeUpdate();
                System.out.println("Usuario registrado correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al registrar el usuario.");
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



    public static void main(String[] args) {
        ConexionBBDD conexion = new ConexionBBDD();

        // Ejemplo de verificación
        String nombre = "daniel";  // reemplaza con los valores que quieras verificar
        String contrasenia = "4321";

        boolean registrado = conexion.verificarUsuario(nombre, contrasenia);
        if (registrado) {
            System.out.println("Usuario y contraseña válidos.");
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }
}

