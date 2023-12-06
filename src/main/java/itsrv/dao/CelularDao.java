package itsrv.dao;
import itsrv.utils.DBConnection;
import itsrv.models.Celular;
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
public class CelularDao {
    
    public boolean agregar (Celular celular) {
        Connection conexion = DBConnection.obtenerConexion();
        
        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("insert into celular (Marca, NS, Modelo, Color, Precio, Inventario) VALUES(?, ?, ?, ?, ?, ?);")) {
                // Establecer los parámetros
                preparedStatement.setString(1, celular.getMarca());
                preparedStatement.setString(2, celular.getNS());
                preparedStatement.setInt(3, celular.getModelo());
                preparedStatement.setString(4, celular.getColor());
                preparedStatement.setFloat(5, celular.getPrecio());
                preparedStatement.setInt(6, celular.getInventario());

                // Ejecutar la consulta
                preparedStatement.executeUpdate();
                
            } catch (SQLException e) {
                Logger.getLogger(CelularDao.class.getName()).log(Level.SEVERE, null, e);
                DBConnection.cerrarConexion(conexion);
                return false;
            } finally {
                DBConnection.cerrarConexion(conexion);
                return true;
            }
        }
        return false;
    }
    
    public List<Celular> listar () {
        Connection conexion = DBConnection.obtenerConexion();
        List<Celular> lista = new ArrayList<>();
        if (conexion != null) {
            try (Statement statement = conexion.createStatement()) {

                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery("select * from celular;");) {
                    while (resultSet.next()) {
                        Celular celular = new Celular();
                        celular.setMarca(resultSet.getString("Marca"));
                        celular.setNS(resultSet.getString("NS"));
                        celular.setModelo(resultSet.getInt("Modelo"));
                        celular.setColor(resultSet.getString("Color"));
                        celular.setPrecio(resultSet.getFloat("Precio"));
                        celular.setInventario(resultSet.getInt("Inventario"));
                        celular.setNumCelular(resultSet.getInt("NumCelular"));
                        lista.add(celular);
                    }
                }
                
            } catch (SQLException e) {
                Logger.getLogger(CelularDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return lista;
    }
    
    public Celular obtener (int NumCelular) {
        Connection conexion = DBConnection.obtenerConexion();
        Celular celular = new Celular();
        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("select * from celular where NumCelular = ?;")) {
                preparedStatement.setInt(1, NumCelular);
                // Ejecutar la consulta
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        celular.setMarca(resultSet.getString("Marca"));
                        celular.setNS(resultSet.getString("NS"));
                        celular.setModelo(resultSet.getInt("Modelo"));
                        celular.setColor(resultSet.getString("Color"));
                        celular.setPrecio(resultSet.getFloat("Precio"));
                        celular.setInventario(resultSet.getInt("Inventario"));
                        celular.setNumCelular(resultSet.getInt("NumCelular"));
                    }
                }
                
            } catch (SQLException e) {
                Logger.getLogger(CelularDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                DBConnection.cerrarConexion(conexion);
            }
        }
        return celular;
    }
    
    public boolean actualizar(Celular celular) {
        Connection conexion = DBConnection.obtenerConexion();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("UPDATE celular SET Marca = ?, NS = ?, Modelo = ?, Color = ?, Precio = ?, Inventario = ? WHERE NumCelular = ?")) {
                // Establecer los parámetros
                preparedStatement.setString(1, celular.getMarca());
                preparedStatement.setString(2, celular.getNS());
                preparedStatement.setInt(3, celular.getModelo());
                preparedStatement.setString(4, celular.getColor());
                preparedStatement.setFloat(5, celular.getPrecio());
                preparedStatement.setInt(6, celular.getInventario());
                preparedStatement.setInt(7, celular.getNumCelular());

                // Ejecutar la consulta de actualización
                preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    Logger.getLogger(CelularDao.class.getName()).log(Level.SEVERE, null, e);
                    DBConnection.cerrarConexion(conexion);
                    return false;
                } finally {
                    DBConnection.cerrarConexion(conexion);
                    return true;
                }
            }
        return false;
    }

    public boolean eliminar(int numCelular) {
        Connection conexion = DBConnection.obtenerConexion();

        if (conexion != null) {
            try (PreparedStatement preparedStatement = conexion.prepareStatement("DELETE FROM celular WHERE NumCelular = ?")) {
                // Establecer el parámetro
                preparedStatement.setInt(1, numCelular);

                // Ejecutar la consulta de actualización
                preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    Logger.getLogger(CelularDao.class.getName()).log(Level.SEVERE, null, e);
                    DBConnection.cerrarConexion(conexion);
                    return false;
                } finally {
                    DBConnection.cerrarConexion(conexion);
                    return true;
                }
            }
        return false;
    }

    
}
