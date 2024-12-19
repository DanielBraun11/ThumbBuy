/**
 * Clase <code>General.Main</code>: Punto de entrada de la aplicación.
 *
 * Esta clase inicializa la interfaz gráfica principal de la aplicación llamando
 * a la clase correspondiente. Actualmente, inicia la interfaz de inicio
 * (<code>InterfazInicio</code>), pero se incluyen comentarios para iniciar otras
 * interfaces como registro, perfil o principal según sea necesario.
 *
 * Uso:
 * Ejecute esta clase para inicializar y lanzar la aplicación.
 */
public class Main{
    /**
     * Método principal de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados en esta aplicación).
     * Puede ser personalizado si se requieren parámetros adicionales en el futuro.
     */
    public static void main(String[] args) {
        new VentanaInicio();

        //new InterfazRegistro();

        //new General.VentanaPrincipal("");

        //new InterfazPerfil("");


    }
}


