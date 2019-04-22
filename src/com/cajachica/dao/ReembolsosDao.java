/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import util.Conexion;

/**
 * select sum(monto) from presupuesto where nombreFinanciador='reynaldo rios'
 * and reembolso='Devolver'
 *
 * @author Reynaldo
 */
public class ReembolsosDao 
{

    //metodo que realiza el listado de los presupuestos registrados
    public ResultSet listarRegPresupuesto(Date desde, Date hasta) throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion

        String sql = "Select P.idpresupuesto, P.monto, P.fechaReg, Pr.nombreProyecto,P.nombrefinanciador,U.nombre,U.apellido,P.observaciones,P.reembolso\n"
                + "from presupuesto AS P, proyecto as Pr, usuario as U\n"
                + "where Pr.idProyecto=P.idProyecto and U.idUsuario=P.idUsuario and  P.fechaReg between ? and ? and P.reembolso='Devolver'";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setDate(1, (java.sql.Date) desde);
        stm.setDate(2, (java.sql.Date) hasta);
        ResultSet consulta = stm.executeQuery();//ejecutando la consulta
        return consulta;
    }

    //Listado por financiador
    public ResultSet listarReembolsoByFianciador(String fianciador) throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion

        String sql = "Select P.idpresupuesto, P.monto, P.fechaReg, Pr.nombreProyecto,P.nombrefinanciador,U.nombre,U.apellido,P.observaciones,P.reembolso\n"
                + "from presupuesto AS P, proyecto as Pr, usuario as U\n"
                + "where Pr.idProyecto=P.idProyecto and U.idUsuario=P.idUsuario and P.reembolso='Devolver' and  P.nombrefinanciador=?";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, fianciador);
        ResultSet consulta = stm.executeQuery();//ejecutando la consulta
        return consulta;
    }

    //Listado general
    public ResultSet listadogeneral() throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion

        String sql = "Select P.idpresupuesto, P.monto, P.fechaReg, Pr.nombreProyecto,P.nombrefinanciador,U.nombre,U.apellido,P.observaciones,P.reembolso\n"
                + "from presupuesto AS P, proyecto as Pr, usuario as U\n"
                + "where Pr.idProyecto=P.idProyecto and U.idUsuario=P.idUsuario and P.reembolso='Devolver'";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet consulta = stm.executeQuery();//ejecutando la consulta
        return consulta;
    }

    //Devuelve la suma de total a devolver de un determinado presupuesto
    public ResultSet sumarMontoTotal(String financiador) throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion

        String sql = "Select sum(monto) as monto from presupuesto where nombreFinanciador=? and reembolso='Devolver'";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, financiador);
        ResultSet consulta = stm.executeQuery();//ejecutando la consulta
        return consulta;
    }

    //Metodo que realiza el pago del presupuesto
    public boolean restituirPresupouesto(int idProyecto) throws Exception {
        Connection miConexion = (Connection) Conexion.getConectar();
        try 
        {
            PreparedStatement statement = miConexion.prepareStatement("UPDATE presupuesto SET reembolso='PAGADO' WHERE idPresupuesto=?");
            statement.setInt(1, idProyecto);//id autoincrementable
            statement.executeUpdate();
            statement.close();
            miConexion.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en la operacion restitución de presupuesto" + ex.getMessage());
            return false;
        }
    }
}
