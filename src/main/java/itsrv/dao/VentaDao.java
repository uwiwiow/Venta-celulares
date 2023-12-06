package itsrv.dao;
import itsrv.utils.DBConnection;
import itsrv.models.Venta;
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
public class VentaDao {

    public boolean agregar(Venta venta) {
        Connection conexion = DBConnection.obtenerConexion();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("INSERT INTO venta (NoE, NoC, Fecha, FolioV) VALUES (?, ?, ?, ?)")) {
                // Establecer los parámetros
                preparedStatement.setInt(1, venta.getNoE());
                preparedStatement.setInt(2, venta.getNoC());
                preparedStatement.setDate(3, venta.getFecha());
                preparedStatement.setInt(4, venta.getFolioV());

                // Ejecutar la consulta
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(VentaDao.class.getName()).log(Level.SEVERE, null, e);
                DBConnection.cerrarConexion(conexion);
                return false;
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return true;
    }

    public List<Venta> listar() {
        Connection conexion = DBConnection.obtenerConexion();
        List<Venta> lista = new ArrayList<>();

        if (conexion != null) {
            try (Statement statement = conexion.createStatement()) {
                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM venta")) {
                    while (resultSet.next()) {
                        Venta venta = new Venta();
                        venta.setNoE(resultSet.getInt("NoE"));
                        venta.setNoC(resultSet.getInt("NoC"));
                        venta.setFecha(resultSet.getDate("Fecha"));
                        venta.setFolioV(resultSet.getInt("FolioV"));
                        lista.add(venta);
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(VentaDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return lista;
    }

    public Venta obtener(int folioV) {
        Connection conexion = DBConnection.obtenerConexion();
        Venta venta = new Venta();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM venta WHERE FolioV = ?")) {
                preparedStatement.setInt(1, folioV);
                // Ejecutar la consulta
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        venta.setNoE(resultSet.getInt("NoE"));
                        venta.setNoC(resultSet.getInt("NoC"));
                        venta.setFecha(resultSet.getDate("Fecha"));
                        venta.setFolioV(resultSet.getInt("FolioV"));
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(VentaDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return venta;
    }

    public boolean actualizar(Venta venta) {
        Connection conexion = DBConnection.obtenerConexion();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("UPDATE venta SET NoE = ?, NoC = ?, Fecha = ? WHERE FolioV = ?")) {
                // Establecer los parámetros
                preparedStatement.setInt(1, venta.getNoE());
                preparedStatement.setInt(2, venta.getNoC());
                preparedStatement.setDate(3, venta.getFecha());
                preparedStatement.setInt(4, venta.getFolioV());

                // Ejecutar la consulta de actualización
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(VentaDao.class.getName()).log(Level.SEVERE, null, e);
                DBConnection.cerrarConexion(conexion);
                return false;
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return true;
    }

    public boolean eliminar(int folioV) {
        Connection conexion = DBConnection.obtenerConexion();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("DELETE FROM venta WHERE FolioV = ?")) {
                // Establecer el parámetro
                preparedStatement.setInt(1, folioV);

                // Ejecutar la consulta de eliminación
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(VentaDao.class.getName()).log(Level.SEVERE, null, e);
                DBConnection.cerrarConexion(conexion);
                return false;
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return true;
    }
}
