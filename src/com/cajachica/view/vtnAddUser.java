/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.view;

import com.cajachica.core.Utilitarios;
import com.cajachica.dao.ProyectoDao;
import com.cajachica.dao.UsuarioDao;
import com.cajachica.dao.addUsuarioProyecto;
import com.cajachica.pojos.Proyecto;
import com.cajachica.pojos.Usuario;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ing. Reynaldo Rios
 */
public class vtnAddUser extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnAddUser
     */
    private Usuario objetoUsuario;

    public static String validaVentana;//variable que realiza la validacion de ventana abierta

    public Usuario getObjetoUsuario() {
        return objetoUsuario;
    }

    public void setObjetoUsuario(Usuario objetoUsuario) {
        this.objetoUsuario = objetoUsuario;
    }

    public vtnAddUser() {
        initComponents();
        validaVentana = "x";//insertando un valor a la variable que valida a la ventana
        /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = Principal.sysMDI.getWidth() - this.getWidth();
        int b = Principal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        listarUsuarios();
        listarProyectos();
        listarUsuariosAsignados();

    }

    //Metodo que realiza el listado de los usuarios registrados
    public void listarUsuarios() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaUsuarioProyecto.getModel();//creando el modela ára llenar los datos al JTableje
            Utilitarios util = new Utilitarios();
            util.limpiarTabla(tablaUsuarioProyecto);
            //realizando la consulta para realizar el listado de los datos
            UsuarioDao almDao = new UsuarioDao();
            List<Object[]> lista = almDao.listarUsuario();
            if (lista.size() > 0) {
                Object[] fila = new Object[modelo.getColumnCount()];

                for (int i = 0; i < lista.size(); i++) {
                    fila[0] = lista.get(i)[0];//id
                    fila[1] = lista.get(i)[1];//nombre
                    fila[2] = lista.get(i)[2];//apellido;
                    if (Integer.parseInt((lista.get(i)[6]).toString()) == 1)//tipo de usuario
                    {
                        fila[3] = "Administrador";
                    } else {
                        fila[3] = "Asistente";
                    }

                    modelo.addRow(fila);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e, null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listarProyectos() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaProyectoUsuario.getModel();//creando el modela ára llenar los datos al JTableje
            Utilitarios util = new Utilitarios();
            util.limpiarTabla(tablaProyectoUsuario);
            //realizando la consulta para realizar el listado de los datos
            ProyectoDao proDao = new ProyectoDao();
            List<Proyecto> lista = proDao.listarProyectos();
            Object[] fila = new Object[modelo.getColumnCount()];
            for (int i = 0; i < lista.size(); i++) {
                fila[0] = lista.get(i).getIdProyecto();
                fila[1] = lista.get(i).getNombreProyecto();
                fila[2] = lista.get(i).getDescripcion();
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e, null, JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo que realiza el listado de los usuarios asignados a los proyectos
    public void listarUsuariosAsignados() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaAsignacionUsuario.getModel();//creando el modelo de la tabla de los usuarios asignados
            Utilitarios util = new Utilitarios();
            util.limpiarTabla(tablaAsignacionUsuario);
            //realizando la consulta para realizar el listado de los datos
            addUsuarioProyecto userproDao = new addUsuarioProyecto();
            ResultSet lista = userproDao.listarUsuarioProyecto();//recuperando el listado de los usuarios asignados a los proyectos
            //rellenando los elementos de la consulta en el Jtable
            Object[] fila = new Object[4];
            while (lista.next()) {
                for (int i = 0; i < 4; i++) {
                    fila[i] = lista.getObject(i + 1);
                }
                modelo.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo que verifica si el item seleccionado esta repetido en la tabla
    public boolean buscarRepetidos(String nombre, String ape, String proyecto) {
        DefaultTableModel modelo = (DefaultTableModel) tablaAsignacionUsuario.getModel();//recuperando la tabla OC
        for (int i = 0; i < modelo.getRowCount(); i++) {
            
           // JOptionPane.showMessageDialog(null, "--> "+nombre+ape+proyecto, null, JOptionPane.ERROR_MESSAGE);
            if (String.valueOf(tablaAsignacionUsuario.getValueAt(i, 1)).equals(nombre) && String.valueOf(tablaAsignacionUsuario.getValueAt(i, 2)).equals(ape) && String.valueOf(tablaAsignacionUsuario.getValueAt(i, 3)).equals(proyecto)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProyectoUsuario = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaUsuarioProyecto = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAsignacionUsuario = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(122, 171, 242));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jLabel1.setText("ADICIONAR USUARIO A PROYECTO");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/images/adduser.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(187, 187, 187))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaProyectoUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nombre ", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaProyectoUsuario);
        if (tablaProyectoUsuario.getColumnModel().getColumnCount() > 0) {
            tablaProyectoUsuario.getColumnModel().getColumn(0).setMinWidth(50);
            tablaProyectoUsuario.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaProyectoUsuario.getColumnModel().getColumn(0).setMaxWidth(70);
        }

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N
        jLabel3.setText("PROYECTOS");

        tablaUsuarioProyecto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nombre ", "Apellido", "Tipo usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaUsuarioProyecto);
        if (tablaUsuarioProyecto.getColumnModel().getColumnCount() > 0) {
            tablaUsuarioProyecto.getColumnModel().getColumn(0).setMinWidth(50);
            tablaUsuarioProyecto.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaUsuarioProyecto.getColumnModel().getColumn(0).setMaxWidth(70);
        }

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N
        jLabel4.setText("USUARIOS DEL SISTEMA");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setText("----->");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/guardar.png"))); // NOI18N
        jButton1.setText("Adicionar Usuario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/cancelar.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(262, 262, 262))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        tablaAsignacionUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nombre ", "Apellido", "Proyecto Asignado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaAsignacionUsuario);
        if (tablaAsignacionUsuario.getColumnModel().getColumnCount() > 0) {
            tablaAsignacionUsuario.getColumnModel().getColumn(0).setMinWidth(70);
            tablaAsignacionUsuario.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaAsignacionUsuario.getColumnModel().getColumn(0).setMaxWidth(70);
        }

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N
        jLabel6.setText("LISTADO DE PROYECTOS Y ASIGNACIONES DE USUARIO");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/delete.png"))); // NOI18N
        jButton3.setText("Eliminar asignación");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(127, 127, 127))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(252, 252, 252))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        validaVentana = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //tabla Usuario
        try {
            // String idUsuario = null;
            DefaultTableModel tm = (DefaultTableModel) tablaUsuarioProyecto.getModel();
            DefaultTableModel tm2 = (DefaultTableModel) tablaProyectoUsuario.getModel();

            if (tablaUsuarioProyecto.getSelectedRowCount() > 0) {
                if (tablaProyectoUsuario.getSelectedRowCount() > 0) {
                    int idUsuario = Integer.parseInt(String.valueOf(tm.getValueAt(tablaUsuarioProyecto.getSelectedRow(), 0)));//recuperando el Id del usuario
                    int idProyecto = Integer.parseInt(String.valueOf(tm2.getValueAt(tablaProyectoUsuario.getSelectedRow(), 0)));//recuperando el ID del proyecto
                    addUsuarioProyecto addUp = new addUsuarioProyecto();

                    //verificando que el usuario no este asignado a un proyecto
                    String nombre = String.valueOf(tm.getValueAt(tablaUsuarioProyecto.getSelectedRow(), 1));
                    String apellido = String.valueOf(tm.getValueAt(tablaUsuarioProyecto.getSelectedRow(), 2));
                    String proyecto = String.valueOf(tm2.getValueAt(tablaProyectoUsuario.getSelectedRow(), 1));
                   
                   // JOptionPane.showMessageDialog(this, "-++"+nombre+apellido+proyecto, null, JOptionPane.INFORMATION_MESSAGE);
                            
                    if (!buscarRepetidos(nombre, apellido, proyecto)) 
                    {
                        //fin de la recuperacion de los datos
                        if (addUp.adicionarUsuarioProyecto(idUsuario, idProyecto)) {
                            JOptionPane.showMessageDialog(this, "Asignación exitosa..!!", null, JOptionPane.INFORMATION_MESSAGE);
                            listarUsuariosAsignados();
                        } else {
                            JOptionPane.showMessageDialog(this, "Error en el registro de datos", null, JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "El usuario ya tiene asignado el proyecto..!!", null, JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un proyecto", null, JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un usuario", null, JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error-->" + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        validaVentana = null;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tm = (DefaultTableModel) tablaAsignacionUsuario.getModel();
        //Recuperando el id el proyecto
        try {
            if (tablaAsignacionUsuario.getSelectedRowCount() > 0) {
                int idAsignacionPU = Integer.parseInt(String.valueOf(tm.getValueAt(tablaAsignacionUsuario.getSelectedRow(), 0)));
                if (new addUsuarioProyecto().eliminarAsignacion(idAsignacionPU)) {
                    JOptionPane.showMessageDialog(this, "Eliminación de la asiganción correcta ", null, JOptionPane.INFORMATION_MESSAGE);
                    listarUsuariosAsignados();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una asiganción a eliminar..!! ", null, JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error" + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaAsignacionUsuario;
    private javax.swing.JTable tablaProyectoUsuario;
    private javax.swing.JTable tablaUsuarioProyecto;
    // End of variables declaration//GEN-END:variables
}
