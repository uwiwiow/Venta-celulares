package itsrv.dao;
import itsrv.utils.DBConnection;
import itsrv.models.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gael_
 */
public class EmpleadoDao {

    public boolean agregar(Empleado empleado) {
        Connection conexion = DBConnection.obtenerConexion();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("INSERT INTO empleado (NombreE, ApeP, ApeM, Turno) VALUES (?, ?, ?, ?)")) {
                // Establecer los parámetros
                preparedStatement.setString(1, empleado.getNombreE());
                preparedStatement.setString(2, empleado.getApeP());
                preparedStatement.setString(3, empleado.getApeM());
                preparedStatement.setString(4, empleado.getTurno());

                // Ejecutar la consulta
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
                DBConnection.cerrarConexion(conexion);
                return false;
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return true;
    }

    public List<Empleado> listar() {
        Connection conexion = DBConnection.obtenerConexion();
        List<Empleado> lista = new ArrayList<>();

        if (conexion != null) {
            try (Statement statement = conexion.createStatement()) {
                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM empleado")) {
                    while (resultSet.next()) {
                        Empleado empleado = new Empleado();
                        empleado.setNombreE(resultSet.getString("NombreE"));
                        empleado.setApeP(resultSet.getString("ApeP"));
                        empleado.setApeM(resultSet.getString("ApeM"));
                        empleado.setTurno(resultSet.getString("Turno"));
                        empleado.setNumEmpleado(resultSet.getInt("NumEmpleado"));
                        lista.add(empleado);
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return lista;
    }

    public Empleado obtener(int numEmpleado) {
        Connection conexion = DBConnection.obtenerConexion();
        Empleado empleado = new Empleado();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM empleado WHERE NumEmpleado = ?")) {
                preparedStatement.setInt(1, numEmpleado);
                // Ejecutar la consulta
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        empleado.setNombreE(resultSet.getString("NombreE"));
                        empleado.setApeP(resultSet.getString("ApeP"));
                        empleado.setApeM(resultSet.getString("ApeM"));
                        empleado.setTurno(resultSet.getString("Turno"));
                        empleado.setNumEmpleado(resultSet.getInt("NumEmpleado"));
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return empleado;
    }

    public boolean actualizar(Empleado empleado) {
        Connection conexion = DBConnection.obtenerConexion();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("UPDATE empleado SET NombreE = ?, ApeP = ?, ApeM = ?, Turno = ? WHERE NumEmpleado = ?")) {
                // Establecer los parámetros
                preparedStatement.setString(1, empleado.getNombreE());
                preparedStatement.setString(2, empleado.getApeP());
                preparedStatement.setString(3, empleado.getApeM());
                preparedStatement.setString(4, empleado.getTurno());
                preparedStatement.setInt(5, empleado.getNumEmpleado());

                // Ejecutar la consulta de actualización
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
                DBConnection.cerrarConexion(conexion);
                return false;
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return true;
    }

    public boolean eliminar(int numEmpleado) {
        Connection conexion = DBConnection.obtenerConexion();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("DELETE FROM empleado WHERE NumEmpleado = ?")) {
                // Establecer el parámetro
                preparedStatement.setInt(1, numEmpleado);

                // Ejecutar la consulta de eliminación
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
                DBConnection.cerrarConexion(conexion);
                return false;
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return true;
    }
}