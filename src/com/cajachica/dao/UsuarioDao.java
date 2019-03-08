/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.dao;

import com.cajachica.pojos.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Reynaldo
 */
public class UsuarioDao {

    Session sesion;
    Transaction tx;

    //Metodo que inicia la sesion 
    public void iniciarOperacion() {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public void manejaexception(HibernateException he) {
        tx.rollback();
        throw new HibernateException("Ocurrio un error en la capa de acceso a datos");
    }

    public boolean registarUsuario(Usuario usr) throws Exception {
        iniciarOperacion();
        sesion.save(usr);
        tx.commit();
        sesion.close();
        return true;
    }

    public List<Object[]> listarUsuario() throws Exception {
        iniciarOperacion();
        Query query = sesion.createQuery("Select u.idUsuario, u.nombre,u.apellido,u.celular,u.cargo,u.email,u.tipoUsuario, u.password from Usuario As u");
        List<Object[]> lista = query.list();
        sesion.close();
        return lista;
    }

    //Buscar usuarios
    public List<Object[]> UsuarioById(int id) throws Exception 
    {
        
        iniciarOperacion();
        Query query = sesion.createQuery("Select u.idUsuario, u.nombre,u.apellido,u.celular,u.cargo,u.email,u.tipoUsuario, u.password FROM Usuario As u WHERE u.idUsuario=?");
        query.setInteger(0, id);
        List<Object[]> lista = query.list();
        
        
        
        for (int i = 0; i < lista.size(); i++)
        {
            System.out.println(""+lista.size());   
            System.out.println("--" + lista.get(i)[0]);
            System.out.println("--" + lista.get(i)[1]);
            System.out.println("--" + lista.get(i)[2]);
            System.out.println("--" + lista.get(i)[3]);
            System.out.println("--" + lista.get(i)[4]);

            sesion.close();
        }
        return lista;
    }

    public Usuario buscarUsuario(String usuario, String password) throws Exception {
        Usuario user = null;
        iniciarOperacion();
        Query query = sesion.createQuery("From Usuario where email=? and password=?");
        query.setString(0, usuario);

        query.setString(1, password);
        user = (Usuario) query.uniqueResult();
        tx.commit();
        sesion.close();
        return user;
    }

    public boolean eliminarUsuario(Usuario user) throws Exception {
        iniciarOperacion();
        sesion.delete(user);
        tx.commit();
        sesion.close();
        return true;
    }

    public boolean actualizarUsuario(Usuario user) throws Exception {
        iniciarOperacion();
        sesion.update(user);
        tx.commit();
        sesion.close();
        return true;
    }
}
