/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.dao;

import com.cajachica.core.Utilitarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.Conexion;

/**
 *
 * @author Reynaldo
 */
public class ReportesDao {

    //metodo que realiza el listado de los usuarios asignados a los proyectos
    public ResultSet movimientosFactura(int idProyecto) throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
        String sql = "select m.idMovimientos, m.fecha,m.ingreso,m.egreso,m.saldo,m.nroDocumento,m.glosa,u.nombre,u.apellido\n"
                + "from movimientos as m, usuario as u, proyecto as pr\n"
                + "where m.idUsuario=u.idUsuario and m.idProyecto=pr.idProyecto and m.idProyecto=?";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, idProyecto);
        ResultSet consulta = stm.executeQuery();//ejecutando la consulta
        return consulta;
    }
    
    public ResultSet movimientosFacturaByFecha(int idProyecto, Date desde, Date hasta) throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
        String sql = "select m.idMovimientos, m.fecha,m.ingreso,m.egreso,m.saldo,m.nroDocumento,m.glosa,u.nombre,u.apellido\n"
                + "from movimientos as m, usuario as u, proyecto as pr\n"
                + "where m.idUsuario=u.idUsuario and m.idProyecto=pr.idProyecto and m.idProyecto=? and m.fecha between ? and ?";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, idProyecto);
        stm.setDate(2, (java.sql.Date) desde);
        stm.setDate(3, (java.sql.Date) hasta);
        ResultSet consulta = stm.executeQuery();//ejecutando la consulta
        return consulta;
    }
}
