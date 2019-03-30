/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import util.Conexion;

/**
 *
 * @author Reynaldo
 */
public class MontosTotalesDao {

    public boolean registrarMontoIngreso(String monto, int idProyecto) throws Exception {
        Connection miConexion = (Connection) Conexion.getConectar();
        try {
            PreparedStatement statement = miConexion.prepareStatement("INSERT INTO montostotales VALUES(?,?,?,?);");
            statement.setInt(1, 0);//id autoincrementable
            statement.setString(2, monto);//ingresos
            statement.setString(3, "0");//egresos
            statement.setInt(4, idProyecto);//id proyecto
            statement.executeUpdate();
            statement.close();
            miConexion.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el monto total " + ex.getMessage());
            return false;
        }
    }

    //metodo que actualiza el total de los ingresos
    public boolean updateIngresosTotales(String monto, int idProyecto, int idMonto) throws Exception {
        Connection miConexion = (Connection) Conexion.getConectar();
        try {
            PreparedStatement statement = miConexion.prepareStatement("UPDATE montostotales SET totalIngresos=? WHERE idProyecto=? and idMontos=?");
            statement.setString(1, monto);//monto ingreso a actualizar
            statement.setInt(2, idProyecto);//idProyecto
            statement.setInt(3, idMonto);//id tabala monto
            statement.executeUpdate();
            statement.close();
            miConexion.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el total de monto Ingreso.." + ex.getMessage());
            return false;
        }
    }

    //Metodo que realiza la recupetracion del monto para la actualizacin del valor
    public ResultSet recuperarMontoIngreso(int idProyecto) {
        ResultSet consulta = null;
        try 
        {
            Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
            String sql = "select totalIngresos,idMontos from montostotales where idProyecto=" + idProyecto;
            PreparedStatement stm = con.prepareStatement(sql);
            consulta = stm.executeQuery();
            return consulta;
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error al recuperar el monto total de los ingresos..!! " + ex.getMessage());
            return consulta;
        }
    }

//Metodo que realiza la verificacion de fila de pryecto vacio
//retorna true si esta vacia 
    public boolean verTablaVacia(int idProyecto) {
        Connection miConexion = (Connection) Conexion.getConectar();
        try {
            PreparedStatement statement = miConexion.prepareStatement("SELECT * FROM montostotales WHERE idProyecto=?;");
            statement.setInt(1, idProyecto);//id autoincrementable
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return false;
            } else {
                return true;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el monto total " + ex.getMessage());
            return false;
        }
    }
    
    //metodo que actualiza el total de los egresos
    public boolean updateEgresosTotales(String monto, int idProyecto, int idMonto) throws Exception {
        Connection miConexion = (Connection) Conexion.getConectar();
        try {
            PreparedStatement statement = miConexion.prepareStatement("UPDATE montostotales SET totalEgresos=? WHERE idProyecto=? and idMontos=?");
            statement.setString(1, monto);//monto ingreso a actualizar
            statement.setInt(2, idProyecto);//idProyecto
            statement.setInt(3, idMonto);//id tabala monto
            statement.executeUpdate();
            statement.close();
            miConexion.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el total de monto Ingreso.." + ex.getMessage());
            return false;
        }
    }
}
