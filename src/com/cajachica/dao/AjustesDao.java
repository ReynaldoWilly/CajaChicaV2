/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.dao;

import com.cajachica.pojos.Ajustes;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Reynaldo
 */
public class AjustesDao {

    Session sesion;
    Transaction tx;

    //Metodo que inicia la sesion 
    public void iniciarOperacion() {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public boolean registarInformacion(Ajustes info) throws Exception {
        iniciarOperacion();
        sesion.save(info);
        tx.commit();
        sesion.close();
        return true;
    }

    public boolean actualizarInformacion(Ajustes info) throws Exception {
        iniciarOperacion();
        sesion.update(info);
        tx.commit();
        sesion.close();
        return true;
    }

    public Ajustes buscarInformacion(int id) throws Exception {
        Ajustes info = null;
        iniciarOperacion();
        Query query = sesion.createQuery("From Ajustes Where idAjustes=?");
        query.setInteger(0, id);
        info = (Ajustes) query.uniqueResult();
        sesion.close();
        return info;
    }

    public List<Ajustes> listarInformacion() throws Exception {
        iniciarOperacion();
        Query query = sesion.createQuery("From Ajustes");
        List<Ajustes> lista = query.list();
        sesion.close();
        return lista;
    }
}
