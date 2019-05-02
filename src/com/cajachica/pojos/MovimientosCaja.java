/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.pojos;

/**
 *
 * @author Reynaldo
 */
public class MovimientosCaja {

    private int idMovimientos;
    private String ingreso;
    private String egreso;
    private String saldo;
    private int idProyecto;
    private String glosa;
    private int nroDocumento;
    private int idUsuario;

    public MovimientosCaja() {
    }

    public int getIdMovimientos() {
        return idMovimientos;
    }

    public void setIdMovimientos(int idMovimientos) {
        this.idMovimientos = idMovimientos;
    }

    public String getIngreso() {
        return ingreso;
    }

    public int getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(int nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getEgreso() {
        return egreso;
    }

    public void setEgreso(String egreso) {
        this.egreso = egreso;
    }

  
    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}
