/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.dao;

import com.cajachica.pojos.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import util.Conexion;

/**
 *
 * @author Reynaldo
 */
public class CargarfacturaDao {

    //metodo que recupera el monto total de ingresos actualizados por seleccion de proyecto
    public String recuperarMontoTotaByProyecto(int idProyecto) throws Exception {

        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
        String sql = "SELECT totalIngresos FROM montostotales WHERE idProyecto=?";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, idProyecto);
        ResultSet consulta = stm.executeQuery();//ejecutando la consulta
        String montoTotal = null;
        while (consulta.next()) {
            montoTotal = consulta.getString(1);
        }
        return montoTotal;
    }

    //metodo que recupera el monto total de ingresos actualizados por seleccion de proyecto
    public int recuperarIdByNombreProyecto(String nombre) throws Exception {

        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
        String sql = "Select idProyecto from proyecto where nombreProyecto=?";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, nombre);
        ResultSet consulta = stm.executeQuery();//ejecutando la consulta
        int idProyecto = 0;
        while (consulta.next()) {
            idProyecto = consulta.getInt(1);
        }
        return idProyecto;
    }

    //Metodo que realiza la insercion de la factura
    public boolean cargarFactura(Factura factura) {
        Connection miConexion = (Connection) Conexion.getConectar();

        java.sql.Date fechaF = new java.sql.Date(factura.getFecha().getTime()); //your SQL date object
        java.sql.Date fechaC = new java.sql.Date(factura.getFechaCargaSistema().getTime()); //your SQL date object
        
        SimpleDateFormat simpDate = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(simpDate.format(date)); //output String in MM-dd-yyyy
        //JOptionPane.showMessageDialog(null, "Error al relizar la inserion de los datos " + simpDate.format(fechaF));

          try 
        {
            PreparedStatement statement = miConexion.prepareStatement("INSERT INTO factura VALUES(?,?,?,?,?,?,?,?,?);");
            statement.setInt(1, 0);//id autoincrementable
            statement.setInt(2, factura.getNroFactura());//numero de factura
            statement.setString(3, factura.getTipoDoc());//tipo de documento
            statement.setString(4, factura.getDetalleFactura());//Detalle factura
            statement.setString(5, String.valueOf(factura.getMontoFactura()));//Monto
            statement.setDate(6, fechaF);
            statement.setDate(7, fechaC);//Fecha Carga
            statement.setInt(8, factura.getIdProyecto());//id pre proyecto
            statement.setInt(9, factura.getIdUsuario());//id de usuario
            statement.executeUpdate();
            statement.close();
            miConexion.close();
            return true;
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Error al relizar la inserion de los datos " + ex.getMessage());
            return false;
        }
    }
    //Consulta que realiza la consulta para el rol de ususario del sistema 
    public String ultimoRegistroFactura() throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
        String sql = "Select montoFActura from factura where idFActura=(select max(idFactura) from factura)";
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet consulta = stm.executeQuery();
        //si hay resuktados enviarlos
        String id="0";
        if(consulta.next())
        {
            id=consulta.getString(1);
        }
        consulta.close();
        return id;
    }
}
