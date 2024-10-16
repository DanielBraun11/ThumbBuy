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

