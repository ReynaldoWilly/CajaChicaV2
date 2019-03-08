/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import util.Conexion;

/**
 *
 * @author Reynaldo
 */
public class addUsuarioProyecto {

    public boolean adicionarUsuarioProyecto(int idUsuario, int idProyecto) throws Exception {
        Connection miConexion = (Connection) Conexion.getConectar();
        try {
            PreparedStatement statement = miConexion.prepareStatement("INSERT INTO proyecto_usuario VALUES(?,?,?)");
            statement.setInt(1, 0);//id autoincrementable
            statement.setInt(2, idUsuario);//id de usuario
            statement.setInt(3, idProyecto);//id proyecto
            statement.executeUpdate();
            statement.close();
            miConexion.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
            return false;
        }

    }

    //metodo que realiza el listado de los usuarios asignados a los proyectos
    public ResultSet listarUsuarioProyecto() throws Exception {
        Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
        String sql = "SELECT PU.idProyectoUsuario, U.nombre,U.apellido, P.nombreProyecto FROM proyecto_usuario AS PU, usuario AS U, proyecto AS P WHERE PU.idUsuario=U.idUsuario and PU.idProyecto=P.idproyecto;";//cadena para la consulta s
        Statement stm = con.createStatement();
        ResultSet consulta = stm.executeQuery(sql);//ejecutando la consulta
        return consulta;
    }

    //metodo que realiza el listado de los usuarios asignados a los proyectos
    public boolean eliminarAsignacion(int idAsignacion) throws Exception {
        Connection miConexion = (Connection) Conexion.getConectar();
        try 
        {
            PreparedStatement statement = miConexion.prepareStatement("DELETE FROM proyecto_usuario WHERE idProyectoUsuario=?;");
            statement.setInt(1, idAsignacion);//id autoincrementable
            statement.executeUpdate();
            statement.close();
            miConexion.close();
            return true;
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Error al realizar la eliminación" + ex.getMessage());
            return false;
        }
    }
}
