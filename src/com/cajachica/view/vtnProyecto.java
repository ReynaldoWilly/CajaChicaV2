/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.view;

import com.cajachica.core.Utilitarios;
import com.cajachica.core.Validaciones;
import com.cajachica.dao.ProyectoDao;
import com.cajachica.dao.UsuarioDao;
import com.cajachica.pojos.Proyecto;
import com.cajachica.pojos.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Reynaldo
 */
public class vtnProyecto extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnProyecto
     */
    public static String validaVentana;//variable que realiza la validacion de ventana abierta
    public Proyecto objetoProyecto = new Proyecto();

    public Proyecto getObjetoProyecto() {
        return objetoProyecto;
    }

    public void setObjetoProyecto(Proyecto objetoProyecto) {
        this.objetoProyecto = objetoProyecto;
    }

    public vtnProyecto() {
        initComponents();

        validaVentana = "x";//insertando un valor a la variable que valida a la ventana
        /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = Principal.sysMDI.getWidth() - this.getWidth();
        int b = Principal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);

        bloquearCamposFormulario();
        listarProyectos();
    }

    //Metodo que bloquea los campos de texto
    public void bloquearCamposFormulario() {
        txtNombreProyecto.setEnabled(false);
        txtDescProyecto.setEnabled(false);
        // btnActivarOp.setEnabled(true);
        btnActualizarP.setEnabled(false);
        btnEliminarP.setEnabled(false);
        btnRegP.setEnabled(false);
        btnCancelarP.setEnabled(false);
        //--------botones de accion-----
        btnHabilitarOp.setEnabled(true);
        btnHabilitarOp.setText("Habilitar Edición");

        //--------fin botones de accion---
    }

    //Metodo que bloquea los campos de texto
    public void activarCamposFormulario() {
        txtNombreProyecto.setEnabled(true);
        txtDescProyecto.setEnabled(true);

        // btnActivarOp.setEnabled(false);
        btnActualizarP.setEnabled(true);
        btnEliminarP.setEnabled(true);
        btnEliminarP.setEnabled(false);
        //--------botones de accion-----
        // btnHabilitar.setEnabled(true);
        //btnHabilitar.setText("Habilitar Edición");

        //--------fin botones de accion---
    }

    public void listarProyectos() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaProyectos.getModel();//creando el modela ára llenar los datos al JTableje
            Utilitarios util = new Utilitarios();
            util.limpiarTabla(tablaProyectos);
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

    public void limpiarCampos() {
        txtNombreProyecto.setText("");
        txtDescProyecto.setText("");
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
        jLabel3 = new javax.swing.JLabel();
        txtNombreProyecto = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnNuevoP = new javax.swing.JButton();
        btnRegP = new javax.swing.JButton();
        btnCancelarP = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtDescProyecto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProyectos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnActualizarP = new javax.swing.JButton();
        btnEliminarP = new javax.swing.JButton();
        btnHabilitarOp = new javax.swing.JToggleButton();

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

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jLabel1.setText("REGISTRO DE PROYECTO");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/images/proyecto.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel3.setText("Nombre proyecto:");

        txtNombreProyecto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnNuevoP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/agregar.png"))); // NOI18N
        btnNuevoP.setText("Nuevo");
        btnNuevoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPActionPerformed(evt);
            }
        });

        btnRegP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/guardar.png"))); // NOI18N
        btnRegP.setText("Registrar");
        btnRegP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegPActionPerformed(evt);
            }
        });

        btnCancelarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/cancelar_1.png"))); // NOI18N
        btnCancelarP.setText("Cancelar");
        btnCancelarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(btnNuevoP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegP)
                .addGap(29, 29, 29)
                .addComponent(btnCancelarP)
                .addGap(106, 106, 106))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegP)
                    .addComponent(btnCancelarP)
                    .addComponent(btnNuevoP))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel4.setText("Descripción:");

        txtDescProyecto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        tablaProyectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id proyecto", "Nombre Proyecto", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProyectos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaProyectosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProyectos);
        if (tablaProyectos.getColumnModel().getColumnCount() > 0) {
            tablaProyectos.getColumnModel().getColumn(0).setMinWidth(50);
            tablaProyectos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaProyectos.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnActualizarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/modificar.png"))); // NOI18N
        btnActualizarP.setText("Actualizar");
        btnActualizarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPActionPerformed(evt);
            }
        });

        btnEliminarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/delete.png"))); // NOI18N
        btnEliminarP.setText("Eliminar");
        btnEliminarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPActionPerformed(evt);
            }
        });

        btnHabilitarOp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/habilitar.png"))); // NOI18N
        btnHabilitarOp.setText("Habilitar opciones");
        btnHabilitarOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabilitarOpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btnHabilitarOp)
                .addGap(26, 26, 26)
                .addComponent(btnActualizarP)
                .addGap(34, 34, 34)
                .addComponent(btnEliminarP)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHabilitarOp)
                    .addComponent(btnActualizarP)
                    .addComponent(btnEliminarP))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreProyecto)
                            .addComponent(txtDescProyecto)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDescProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        validaVentana = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnNuevoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPActionPerformed
        // TODO add your handling code here:
        activarCamposFormulario();
        limpiarCampos();
        btnHabilitarOp.setEnabled(false);
        btnActualizarP.setEnabled(false);
        btnEliminarP.setEnabled(false);

        btnNuevoP.setEnabled(false);
        btnRegP.setEnabled(true);
        btnCancelarP.setEnabled(true);
    }//GEN-LAST:event_btnNuevoPActionPerformed

    private void btnCancelarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPActionPerformed
        // TODO add your handling code here:
        bloquearCamposFormulario();
        btnNuevoP.setEnabled(true);
    }//GEN-LAST:event_btnCancelarPActionPerformed

    private void btnRegPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegPActionPerformed
        // TODO add your handling code here:
        try {
            List<String> validar = new ArrayList<>();
            validar.add(txtNombreProyecto.getText());
            validar.add(txtDescProyecto.getText());

            if (new Validaciones().validarCampos(validar)) {
                Proyecto pro = new Proyecto(txtNombreProyecto.getText(), txtDescProyecto.getText());
                ProyectoDao proDao = new ProyectoDao();
                if (proDao.registarProyecto(pro)) {
                    JOptionPane.showMessageDialog(this, "Registro de usuario correcto..!!", null, JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                    bloquearCamposFormulario();
                    listarProyectos();
                    btnNuevoP.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", null, JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception E) {
            JOptionPane.showMessageDialog(this, E.getMessage(), null, JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRegPActionPerformed

    private void tablaProyectosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProyectosMousePressed
        // TODO add your handling code here:
        DefaultTableModel tm = (DefaultTableModel) tablaProyectos.getModel();
        String dato = String.valueOf(tm.getValueAt(tablaProyectos.getSelectedRow(), 0));
        UsuarioDao userDao = new UsuarioDao();
        try {
            Proyecto pro = new Proyecto();
            pro.setIdProyecto(Integer.parseInt(dato));
            pro.setNombreProyecto(String.valueOf(tm.getValueAt(tablaProyectos.getSelectedRow(), 1)));
            pro.setDescripcion(String.valueOf(tm.getValueAt(tablaProyectos.getSelectedRow(), 2)));

            this.setObjetoProyecto(pro);//insertando el
            txtNombreProyecto.setText(pro.getNombreProyecto());
            txtDescProyecto.setText(pro.getDescripcion());

            //JOptionPane.showMessageDialog(null, "Precionado" + cat.getNombre());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error" + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tablaProyectosMousePressed

    private void btnHabilitarOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabilitarOpActionPerformed
        // TODO add your handling code here:
        if (btnHabilitarOp.isSelected()) {
            activarCamposFormulario();
            btnRegP.setEnabled(false);
            btnCancelarP.setEnabled(false);
            btnNuevoP.setEnabled(false);
            btnActualizarP.setEnabled(true);
            btnEliminarP.setEnabled(true);
            btnHabilitarOp.setText("Cancelar");
        } else {
            limpiarCampos();
            bloquearCamposFormulario();
            btnHabilitarOp.setText("Habilitar Edición");
            btnNuevoP.setEnabled(true);
        }
    }//GEN-LAST:event_btnHabilitarOpActionPerformed

    private void btnActualizarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPActionPerformed
        // TODO add your handling code here:
        try {
            List<String> validar = new ArrayList<>();
            validar.add(txtNombreProyecto.getText());
            validar.add(txtDescProyecto.getText());

            if (!txtNombreProyecto.getText().equals("")) {
                if (new Validaciones().validarCampos(validar)) {

                    Proyecto pro = new Proyecto(this.getObjetoProyecto().getIdProyecto(), txtNombreProyecto.getText(), txtDescProyecto.getText());
                    ProyectoDao proDao = new ProyectoDao();
                    if (proDao.actualizarProyecto(pro)) {
                        JOptionPane.showMessageDialog(this, "Actualización de proyecto correcto..!!", null, JOptionPane.INFORMATION_MESSAGE);
                        limpiarCampos();
                        bloquearCamposFormulario();
                        listarProyectos();
                        btnNuevoP.setEnabled(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", null, JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un elemento  para actualizar!!", null, JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception E) {
            JOptionPane.showMessageDialog(this, E.getMessage(), null, JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarPActionPerformed

    private void btnEliminarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPActionPerformed
        // TODO add your handling code here:
        try 
        {
            if (!txtNombreProyecto.getText().equals("")) 
            {
                int y = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el proyecto?");
                if (y == JOptionPane.YES_OPTION) 
                {
                    ProyectoDao proDao = new ProyectoDao();
                    if (proDao.eliminarProyecto(this.getObjetoProyecto())) 
                    {
                        JOptionPane.showMessageDialog(this, "Eliminacion correcta", "Mensaje..!!", JOptionPane.INFORMATION_MESSAGE);
                        limpiarCampos();
                        bloquearCamposFormulario();
                        listarProyectos();
                        btnNuevoP.setEnabled(true);
                        btnHabilitarOp.setEnabled(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un Proyecto a eliminar..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hay proyectos asociados a este usuario..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarP;
    private javax.swing.JButton btnCancelarP;
    private javax.swing.JButton btnEliminarP;
    private javax.swing.JToggleButton btnHabilitarOp;
    private javax.swing.JButton btnNuevoP;
    private javax.swing.JButton btnRegP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProyectos;
    private javax.swing.JTextField txtDescProyecto;
    private javax.swing.JTextField txtNombreProyecto;
    // End of variables declaration//GEN-END:variables
}
