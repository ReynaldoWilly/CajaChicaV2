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
    private int idPresupuesto;
    private int idFactura;

    public MovimientosCaja() {
    }

    public MovimientosCaja(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public int getIdMovimientos() {
        return idMovimientos;
    }

    public void setIdMovimientos(int idMovimientos) {
        this.idMovimientos = idMovimientos;
    }

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

}
