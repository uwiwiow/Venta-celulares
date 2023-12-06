package itsrv.dao;
import itsrv.utils.DBConnection;
import itsrv.models.DSV;
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
public class DSVDao {

    public boolean agregar(DSV dsv) {
        Connection conexion = DBConnection.obtenerConexion();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("INSERT INTO dsv (FolioV, NoC, Cantidad, Importe) VALUES (?, ?, ?, ?)")) {
                // Establecer los parámetros
                preparedStatement.setInt(1, dsv.getFolioV());
                preparedStatement.setInt(2, dsv.getNoC());
                preparedStatement.setInt(3, dsv.getCantidad());
                preparedStatement.setFloat(4, dsv.getImporte());

                // Ejecutar la consulta
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(DSVDao.class.getName()).log(Level.SEVERE, null, e);
                DBConnection.cerrarConexion(conexion);
                return false;
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return true;
    }

    public List<DSV> listar() {
        Connection conexion = DBConnection.obtenerConexion();
        List<DSV> lista = new ArrayList<>();

        if (conexion != null) {
            try (Statement statement = conexion.createStatement()) {
                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM dsv")) {
                    while (resultSet.next()) {
                        DSV dsv = new DSV();
                        dsv.setFolioV(resultSet.getInt("FolioV"));
                        dsv.setNoC(resultSet.getInt("NoC"));
                        dsv.setCantidad(resultSet.getInt("Cantidad"));
                        dsv.setImporte(resultSet.getFloat("Importe"));
                        dsv.setNoV(resultSet.getInt("NoV"));
                        lista.add(dsv);
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(DSVDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return lista;
    }
    
    public List<DSV> listarPorFolio(int Folio) {
        Connection conexion = DBConnection.obtenerConexion();
        List<DSV> lista = new ArrayList<>();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("select * from dsv where FolioV = ?")) {
                preparedStatement.setInt(1, Folio);
                // Ejecutar la consulta
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        DSV dsv = new DSV();
                        dsv.setFolioV(resultSet.getInt("FolioV"));
                        dsv.setNoC(resultSet.getInt("NoC"));
                        dsv.setCantidad(resultSet.getInt("Cantidad"));
                        dsv.setImporte(resultSet.getFloat("Importe"));
                        dsv.setNoV(resultSet.getInt("NoV"));
                        lista.add(dsv);
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(DSVDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return lista;
    }

    public DSV obtener(int noV) {
        Connection conexion = DBConnection.obtenerConexion();
        DSV dsv = new DSV();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM dsv WHERE NoV = ?")) {
                preparedStatement.setInt(1, noV);
                // Ejecutar la consulta
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        dsv.setFolioV(resultSet.getInt("FolioV"));
                        dsv.setNoC(resultSet.getInt("NoC"));
                        dsv.setCantidad(resultSet.getInt("Cantidad"));
                        dsv.setImporte(resultSet.getFloat("Importe"));
                        dsv.setNoV(resultSet.getInt("NoV"));
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(DSVDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return dsv;
    }

    public boolean actualizar(DSV dsv) {
        Connection conexion = DBConnection.obtenerConexion();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("UPDATE dsv SET FolioV = ?, NoC = ?, Cantidad = ?, Importe = ? WHERE NoV = ?")) {
                // Establecer los parámetros
                preparedStatement.setInt(1, dsv.getFolioV());
                preparedStatement.setInt(2, dsv.getNoC());
                preparedStatement.setInt(3, dsv.getCantidad());
                preparedStatement.setFloat(4, dsv.getImporte());
                preparedStatement.setInt(5, dsv.getNoV());

                // Ejecutar la consulta de actualización
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(DSVDao.class.getName()).log(Level.SEVERE, null, e);
                DBConnection.cerrarConexion(conexion);
                return false;
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return true;
    }

    public boolean eliminar(int noV) {
        Connection conexion = DBConnection.obtenerConexion();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("DELETE FROM dsv WHERE NoV = ?")) {
                // Establecer el parámetro
                preparedStatement.setInt(1, noV);

                // Ejecutar la consulta de eliminación
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(DSVDao.class.getName()).log(Level.SEVERE, null, e);
                DBConnection.cerrarConexion(conexion);
                return false;
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return true;
    }
}
