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
    public ResultSet facturaFecha(Date desde, Date hasta) throws Exception 
    {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
        String sql = "Select  distinct (F.idFactura), F.nroFactura,F.tipoDoc,F.detalleFActura,F.montoFactura,F.fecha,F.FechaCargaSistema,P.nombreProyecto, U.nombre,U.apellido\n"
                + "from factura as F, usuario as U, proyecto as P\n"
                + "where F.idproyecto=P.idProyecto and U.idUsuario=F.idUsuario and F.fecha between ? and ?";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setDate(1, (java.sql.Date) desde);
        stm.setDate(2, (java.sql.Date) hasta);
        ResultSet consulta = stm.executeQuery();//ejecutando la consulta
        return consulta;
    }
}
