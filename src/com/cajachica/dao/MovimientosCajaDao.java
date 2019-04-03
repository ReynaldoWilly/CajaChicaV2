/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.dao;

import com.cajachica.pojos.MovimientosCaja;
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

    public void registrarMontoIngreso(MovimientosCaja movCaja) throws Exception {
        Connection miConexion = (Connection) Conexion.getConectar();
        try 
        {
            PreparedStatement statement = miConexion.prepareStatement("INSERT INTO movimientos VALUES(?,?,?);");
            statement.setInt(1, 0);//id autoincrementable
            statement.setInt(2, movCaja.getIdPresupuesto());//ingresos
            statement.setInt(3, movCaja.getIdFactura());//egresos
            statement.executeUpdate();
            statement.close();
            miConexion.close();
          //  return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el movimiento" + ex.getMessage());
            //return false;
        }
    }

}
