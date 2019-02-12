/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import com.cajachica.pojos.Usuario;
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

    public List<Usuario> listarUsuario() throws Exception {
        iniciarOperacion();
        Query query = sesion.createQuery("From Usuario");
        List<Usuario> lista = query.list();
        sesion.close();
        return lista;
    }

    
    //Usuario= email
    public Usuario buscarUsuario(String usuario, String password) throws Exception
    {
        Usuario user=null;
        iniciarOperacion();
        Query query = sesion.createQuery("From Usuario where email=? and password=?");
        query.setString(0, usuario);
        query.setString(1, password);
        user =(Usuario) query.uniqueResult();
        tx.commit();
        sesion.close();
        return  user;
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
