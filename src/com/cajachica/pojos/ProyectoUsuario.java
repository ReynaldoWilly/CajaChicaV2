package com.cajachica.pojos;
// Generated Feb 3, 2019 10:43:40 PM by Hibernate Tools 4.3.1



/**
 * ProyectoUsuario generated by hbm2java
 */
public class ProyectoUsuario  implements java.io.Serializable {


     private Integer idProyectoUsuario;
     private int idUsuario;
     private int idProyecto;

    public ProyectoUsuario() {
    }

    public ProyectoUsuario(int idUsuario, int idProyecto) {
       this.idUsuario = idUsuario;
       this.idProyecto = idProyecto;
    }
   
    public Integer getIdProyectoUsuario() {
        return this.idProyectoUsuario;
    }
    
    public void setIdProyectoUsuario(Integer idProyectoUsuario) {
        this.idProyectoUsuario = idProyectoUsuario;
    }
    public int getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getIdProyecto() {
        return this.idProyecto;
    }
    
    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }




}


