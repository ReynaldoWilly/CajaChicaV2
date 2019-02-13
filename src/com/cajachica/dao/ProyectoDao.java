/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.dao;

import com.cajachica.pojos.Proyecto;
import com.cajachica.pojos.Usuario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Reynaldo
 */
public class ProyectoDao {
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

    public boolean registarProyecto(Proyecto pro) throws Exception {
        iniciarOperacion();
        sesion.save(pro);
        tx.commit();
        sesion.close();
        return true;
    }

    public List<Proyecto> listarProyectos() throws Exception {
        iniciarOperacion();
        Query query = sesion.createQuery("From Proyecto");
        List<Proyecto> lista = query.list();
        sesion.close();
        return lista;
    }

    /*
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
    */
    
    public boolean eliminarProyecto(Proyecto pro) throws Exception {
        iniciarOperacion();
        sesion.delete(pro);
        tx.commit();
        sesion.close();
        return true;
    }
     
    
    public boolean actualizarProyecto(Proyecto pro) throws Exception {
        iniciarOperacion();
        sesion.update(pro);
        tx.commit();
        sesion.close();
        return true;
    }
}