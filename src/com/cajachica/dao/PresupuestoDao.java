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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import util.Conexion;

/**
 *
 * @author Reynaldo
 */
public class PresupuestoDao {

    //metodo que realiza el listado de los usuarios asignados a los proyectos
    public ResultSet listarProyectos() throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
        String sql = "SELECT nombreProyecto FROM proyecto;";//cadena para la consulta s
        Statement stm = con.createStatement();
        ResultSet consulta = stm.executeQuery(sql);//ejecutando la consulta
        return consulta;
    }

    //metodo que realiza el listado de los usuarios asignados a los proyectos
    public int recuperarIdByNombre(String nombreProyecto) throws Exception {

        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
        String sql = "SELECT idProyecto FROM proyecto WHERE nombreProyecto =?";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, nombreProyecto);
        ResultSet consulta = stm.executeQuery();//ejecutando la consulta
        int id = 0;
        while (consulta.next()) {
            id = consulta.getInt("idProyecto");
        }
        return id;
    }

    public boolean adicionarPresupuesto(String monto, int idProyecto, String financiador,String obs, int idUuario) throws Exception {
        Connection miConexion = (Connection) Conexion.getConectar();
        //recuperando la fecha de registro
        Date fecha = new Date();
        Date sqldate = new java.sql.Date(fecha.getTime());
        //fin de la recuperacion de la fecha
        try 
        {
            PreparedStatement statement = miConexion.prepareStatement("INSERT INTO presupuesto VALUES(?,?,?,?,?,?,?);");
            statement.setInt(1, 0);//id autoincrementable
            statement.setString(2, monto);//monto
            statement.setDate(3, (java.sql.Date) sqldate);//id campo fecha
            statement.setInt(4, idProyecto);//id proyecto
            statement.setString(5, financiador);//nombre financiador
            statement.setString(6, obs);//observaciones
            statement.setInt(7, idUuario);//id usuario responsable de reh¿gistro en sistema
            statement.executeUpdate();
            
            statement.close();
            miConexion.close();
            return true;
        }
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
            return false;
        }
    }

    //metodo que realiza el listado de los presupuestos registrados
    public ResultSet listarRegPresupuesto() throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
        String sql = "select P.idpresupuesto, P.monto, P.fechaReg, Pr.nombreProyecto,P.nombrefinanciador,U.nombre,U.apellido,P.observaciones \n"
                + "from presupuesto AS P, proyecto as Pr, usuario as U\n"
                + "where Pr.idProyecto=P.idProyecto and U.idUsuario=P.idUsuario";//cadena para la consulta s
        Statement stm = con.createStatement();

        ResultSet consulta = stm.executeQuery(sql);//ejecutando la consulta
        return consulta;
    }

    //metodo que realiza el listado de los presupuestos por nombre de presupuesto
    public ResultSet listPresupuestoByNombre(String nombreProyecto) throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
        String sql = "Select P.idpresupuesto, P.monto, P.fechaReg, Pr.nombreProyecto,P.nombrefinanciador,U.nombre,U.apellido,P.observaciones \n"
                + "from presupuesto AS P, proyecto as Pr, usuario as U\n"
                + "where Pr.idProyecto=P.idProyecto and U.idUsuario=P.idUsuario and Pr.nombreProyecto=?";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, nombreProyecto);
        ResultSet consulta = stm.executeQuery();
        return consulta;
    }
    
    //Consulta que realiza la consulta para el rol de ususario del sistema 
    public ResultSet listarRegPresupuestoByRol(int idUsuario) throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
        String sql = "Select distinct nombreProyecto From proyecto as p, proyecto_usuario as pu, usuario as u where p.idProyecto=pu.IdProyecto and pu.idUsuario=?";//cadena para la consulta s
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, idUsuario);
        ResultSet consulta = stm.executeQuery();
        return consulta;
    }
    
    //Consulta que realiza la consulta para el rol de ususario del sistema 
    public int ultimoRegistroPresupuesto() throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
        String sql = "Select max(idPresupuesto) from presupuesto;";
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet consulta = stm.executeQuery();
        //si hay resuktados enviarlos
        int id=0;
        if(consulta.next())
        {
            id=consulta.getInt(1);
        }
        consulta.close();
        return id;
    }
}
