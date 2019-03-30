/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import util.Conexion;

/**
 *
 * @author Reynaldo
 */
public class MovimientosCajaDao 
{

    public boolean registrarMontoIngreso(String montoI, String montoE,int idProyecto) throws Exception {
        Connection miConexion = (Connection) Conexion.getConectar();
        try {
            PreparedStatement statement = miConexion.prepareStatement("INSERT INTO movimientos VALUES(?,?,?,?,?);");
            statement.setInt(1, 0);//id autoincrementable
            statement.setString(2, montoI);//ingresos
            statement.setString(3, montoE);//egresos
            statement.setDate(4, new Date(idProyecto));
            statement.setInt(5, idProyecto);//id proyecto
            statement.executeUpdate();
            statement.close();
            miConexion.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el movimiento" + ex.getMessage());
            return false;
        }
    }

}
