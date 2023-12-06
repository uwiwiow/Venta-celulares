package itsrv.dao;
import itsrv.utils.DBConnection;
import itsrv.models.Cliente;
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
public class ClienteDao {

    public boolean agregar(Cliente cliente) {
        Connection conexion = DBConnection.obtenerConexion();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("INSERT INTO cliente (NombreC, ApePC, ApeMC, CalleC, Numero, Colonia) VALUES (?, ?, ?, ?, ?, ?)")) {
                // Establecer los parámetros
                preparedStatement.setString(1, cliente.getNombreC());
                preparedStatement.setString(2, cliente.getApePC());
                preparedStatement.setString(3, cliente.getApeMC());
                preparedStatement.setString(4, cliente.getCalleC());
                preparedStatement.setInt(5, cliente.getNumero());
                preparedStatement.setString(6, cliente.getColonia());

                // Ejecutar la consulta
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, e);
                DBConnection.cerrarConexion(conexion);
                return false;
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return true;
    }

    public List<Cliente> listar() {
        Connection conexion = DBConnection.obtenerConexion();
        List<Cliente> lista = new ArrayList<>();

        if (conexion != null) {
            try (Statement statement = conexion.createStatement()) {
                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM cliente")) {
                    while (resultSet.next()) {
                        Cliente cliente = new Cliente();
                        cliente.setNombreC(resultSet.getString("NombreC"));
                        cliente.setApePC(resultSet.getString("ApePC"));
                        cliente.setApeMC(resultSet.getString("ApeMC"));
                        cliente.setCalleC(resultSet.getString("CalleC"));
                        cliente.setNumero(resultSet.getInt("Numero"));
                        cliente.setColonia(resultSet.getString("Colonia"));
                        cliente.setNumCliente(resultSet.getInt("NumCliente"));
                        lista.add(cliente);
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return lista;
    }

    public Cliente obtener(int numCliente) {
        Connection conexion = DBConnection.obtenerConexion();
        Cliente cliente = new Cliente();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM cliente WHERE NumCliente = ?")) {
                preparedStatement.setInt(1, numCliente);
                // Ejecutar la consulta
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        cliente.setNombreC(resultSet.getString("NombreC"));
                        cliente.setApePC(resultSet.getString("ApePC"));
                        cliente.setApeMC(resultSet.getString("ApeMC"));
                        cliente.setCalleC(resultSet.getString("CalleC"));
                        cliente.setNumero(resultSet.getInt("Numero"));
                        cliente.setColonia(resultSet.getString("Colonia"));
                        cliente.setNumCliente(resultSet.getInt("NumCliente"));
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return cliente;
    }

    public boolean actualizar(Cliente cliente) {
        Connection conexion = DBConnection.obtenerConexion();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("UPDATE cliente SET NombreC = ?, ApePC = ?, ApeMC = ?, CalleC = ?, Numero = ?, Colonia = ? WHERE NumCliente = ?")) {
                // Establecer los parámetros
                preparedStatement.setString(1, cliente.getNombreC());
                preparedStatement.setString(2, cliente.getApePC());
                preparedStatement.setString(3, cliente.getApeMC());
                preparedStatement.setString(4, cliente.getCalleC());
                preparedStatement.setInt(5, cliente.getNumero());
                preparedStatement.setString(6, cliente.getColonia());
                preparedStatement.setInt(7, cliente.getNumCliente());

                // Ejecutar la consulta de actualización
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, e);
                DBConnection.cerrarConexion(conexion);
                return false;
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return true;
    }

    public boolean eliminar(int numCliente) {
        Connection conexion = DBConnection.obtenerConexion();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("DELETE FROM cliente WHERE NumCliente = ?")) {
                // Establecer el parámetro
                preparedStatement.setInt(1, numCliente);

                // Ejecutar la consulta de eliminación
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, e);
                DBConnection.cerrarConexion(conexion);
                return false;
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return true;
    }
}

