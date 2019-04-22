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
public class historialPagosDao {
    //Devuelve la suma de total a devolver de un determinado presupuesto

    public ResultSet sumarMontoTotal(String financiador) throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion

        String sql = "Select sum(monto) as monto from presupuesto where nombreFinanciador=? and reembolso='Devolver'";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, financiador);
        ResultSet consulta = stm.executeQuery();//ejecutando la consulta
        return consulta;
    }

    //metodo que realiza la busqueda de pago realizado
    public boolean buscarPago(int idProyecto) throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion

        /*  String sql = "Select sum(monto) as monto from presupuesto where nombreFinanciador=? and reembolso='Devolver'";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, financiador);
        ResultSet consulta = stm.executeQuery();//ejecutando la consulta
         */
        return true;
    }

    //metodo que realiza la insercion en la tabla pago
    public boolean adicionarPago(String status, double monto, int idProyecto) throws Exception {
        Connection miConexion = (Connection) Conexion.getConectar();
        //recuperando la fecha de registro
        Date fecha = new Date();
        Date sqldate = new java.sql.Date(fecha.getTime());
        //fin de la recuperacion de la fecha
        try {
            PreparedStatement statement = miConexion.prepareStatement("INSERT INTO pagos VALUES(?,?,?,?);");
            statement.setInt(1, 0);//id autoincrementable
            statement.setString(2, status);//status
            statement.setDouble(3, monto);//importe
            statement.setInt(4, idProyecto);//id proyecto
            statement.executeUpdate();
            statement.close();
            miConexion.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
            return false;
        }
    }

    //metodo que realiza la insercion del historial de pago
    public boolean adicionarDetallePago(double importe, double saldo, int idPago, int idUuario) throws Exception {
        Connection miConexion = (Connection) Conexion.getConectar();
        //recuperando la fecha de registro
        Date fecha = new Date();
        Date sqldate = new java.sql.Date(fecha.getTime());
        //fin de la recuperacion de la fecha
        try 
        {
            PreparedStatement statement = miConexion.prepareStatement("INSERT INTO detallepagos VALUES(?,?,?,?,?,?);");
            statement.setInt(1, 0);//id autoincrementable
            statement.setDouble(2, importe);//monto
            statement.setDouble(3, saldo);//saldo
            statement.setInt(4, idPago);//monto
            statement.setDate(5, (java.sql.Date) sqldate);//id campo fecha
            statement.setInt(6, idUuario);//id usuario responsable de reh¿gistro en sistema
            statement.executeUpdate();
            statement.close();
            miConexion.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el detalle del pago..!!" + ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    //metodo que devuelve el ID del ultimo registro 
    public int buscarUltimoRegPago() throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
        int id = 0;
        String sql = "select max(idPagos) as idPago from pagos";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet consulta = stm.executeQuery();//ejecutando la consulta
        if (consulta.next()) {
            id = consulta.getInt("idPago");
        }
        return id;
    }
     //metodo que realiza la insercion del historial de pago
    public boolean adicionarItemsPresupuesto(int idPresupuesto, int idPago) throws Exception {
        Connection miConexion = (Connection) Conexion.getConectar();
        //recuperando la fecha de registro
        //fin de la recuperacion de la fecha
        try 
        {
            PreparedStatement statement = miConexion.prepareStatement("INSERT INTO itemspresupuestos VALUES(?,?,?);");
            statement.setInt(1, 0);//id autoincrementable
            statement.setInt(2, idPresupuesto);//monto
            statement.setInt(3, idPago);//saldo
            statement.executeUpdate();
            statement.close();
            miConexion.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar tabla Items Presupuesto..!!" + ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
