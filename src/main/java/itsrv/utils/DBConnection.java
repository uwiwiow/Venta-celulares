package itsrv.utils;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author gael_
 */
public class DBConnection {
    
    private static final String PROPERTIES_FILE = "/DatabaseConfig.properties";

    public static Connection obtenerConexion() {
        Properties prop = cargarConfiguracion();

        if (prop == null) {
            return null;
        }

        try {
            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, "Error estableciendo la conexion a la base de datos", e);
            return null;
        }
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, "Error al cerrar la conexion a la base de datos", e);
            }
        }
    }

    private static Properties cargarConfiguracion() {
        try (InputStream input = DBConnection.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, "Error, no se pudo encontrar el archivo de configuración");
                return null;
            }
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (Exception e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, "Error al cargar las credenciales de la base de datos", e);
            return null;
        }
    }
    
}
