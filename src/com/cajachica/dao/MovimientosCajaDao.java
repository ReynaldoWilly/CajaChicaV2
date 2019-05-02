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
public class MovimientosCajaDao {

    public void registrarIngresoMovimiento(MovimientosCaja movCaja) throws Exception {
        Connection miConexion = (Connection) Conexion.getConectar();
        try {

            java.util.Date fecha = new java.util.Date();
            java.util.Date sqldate = new java.sql.Date(fecha.getTime());

            PreparedStatement statement = miConexion.prepareStatement("INSERT INTO movimientos VALUES(?,?,?,?,?,?,?,?,?);");
            statement.setInt(1, 0);//id autoincrementable
            statement.setString(2, movCaja.getIngreso());//ingresos
            statement.setString(3, movCaja.getEgreso());//egresos
            statement.setString(4, movCaja.getSaldo());//saldo
            statement.setString(5, movCaja.getGlosa());
            statement.setInt(6, movCaja.getNroDocumento());
            statement.setInt(7, movCaja.getIdProyecto());
            statement.setDate(8, (Date) sqldate);
            statement.setInt(9, movCaja.getIdUsuario());
            statement.executeUpdate();
            statement.close();
            miConexion.close();
            //  return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el movimiento" + ex.getMessage());
            //return false;
        }
    }

    //Cargar factura
    public void registrarMontoEngreso(MovimientosCaja movCaja) throws Exception {
        Connection miConexion = (Connection) Conexion.getConectar();
        try {
            java.util.Date fecha = new java.util.Date();
            java.util.Date sqldate = new java.sql.Date(fecha.getTime());

            PreparedStatement statement = miConexion.prepareStatement("INSERT INTO movimientos VALUES(?,?,?,?,?,?,?,?,?);");
            statement.setInt(1, 0);//id autoincrementable
            statement.setString(2, movCaja.getIngreso());//ingresos
            statement.setString(3, movCaja.getEgreso());//egresos
            statement.setString(4, movCaja.getSaldo());//saldo
            statement.setString(5, movCaja.getGlosa());
            statement.setInt(6, movCaja.getNroDocumento());
            statement.setInt(7, movCaja.getIdProyecto());
            statement.setDate(8, (Date) sqldate);
            statement.setInt(9, movCaja.getIdUsuario());
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
