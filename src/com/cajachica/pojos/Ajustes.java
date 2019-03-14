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
public class Ajustes {

    private Integer idAjustes;
    private String razonsocial;
    private String nit;
    private String direccion;
    private String telefono;
    private String email;
    private byte[] logo;
    private String web;

    public Ajustes() {
    }

    public Ajustes(String razonsocial, String nit, String direccion, String telefono, String email, byte[] logo, String web) {
        this.razonsocial = razonsocial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.logo = logo;
        this.web = web;
    }

    public Integer getIdAjustes() {
        return idAjustes;
    }

    public void setIdAjustes(Integer idAjustes) {
        this.idAjustes = idAjustes;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
