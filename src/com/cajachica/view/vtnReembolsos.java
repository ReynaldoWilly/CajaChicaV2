/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.view;

import com.cajachica.core.Utilitarios;
import com.cajachica.dao.PresupuestoDao;
import com.cajachica.dao.ProyectoDao;
import com.cajachica.dao.ReembolsosDao;
import com.cajachica.dao.historialPagosDao;
import com.cajachica.pojos.Usuario;
import static com.cajachica.view.Principal.sysMDI;
import com.mysql.jdbc.Connection;
import java.io.File;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import util.Conexion;
import util.Numero_a_Letra;

/**
 *
 * @author Reynaldo
 */
public class vtnReembolsos extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnReembolsos
     */
    public static String validaVentana;//variable que realiza la validacion de ventana abierta
    private static Usuario userLogin = null;//usuario recuperado desde la pantalla principal

    public vtnReembolsos() {
        initComponents();
        validaVentana = "x";//insertando un valor a la variable que valida a la ventana
        /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = Principal.sysMDI.getWidth() - this.getWidth();
        int b = Principal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        //SETEANDO EL LOGIN DE USUARIO
        userLogin = vtnLogin.user;
        

    }

    //Metodo que realiza el listado de los usuarios asignados a los proyectos
    public void listaReembolsos() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaReembolso.getModel();//creando el modelo de la tabla de los usuarios asignados
            Utilitarios util = new Utilitarios();
            util.limpiarTabla(tablaReembolso);
            //realizando la consulta para realizar el listado de los datos
            PresupuestoDao pDao = new PresupuestoDao();
            ResultSet lista = pDao.listarRegPresupuesto();//recuperando el listado de los usuarios asignados a los proyectos
            //rellenando los elementos de la consulta en el Jtable
            Object[] fila = new Object[9];
            while (lista.next()) {
                for (int i = 0; i < 9; i++) {
                    fila[i] = lista.getObject(i + 1);
                }
                modelo.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar los datos..!! " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }

    //metodo que realiza la suma de los montos a reembolsar
    public static void sumarMontosTabla(DefaultTableModel model) {
        double total = 0;
        //recorrer todas las filas de la segunda columna y va sumando las cantidades
        for (int i = 0; i < model.getRowCount(); i++) 
        {
            double numero = 0;
            try {
                //capturamos valor de celda
                numero = Double.parseDouble(model.getValueAt(i, 1).toString());
            } catch (NumberFormatException nfe) { //si existe un error se coloca 0 a la celda
                numero = 0;
                model.setValueAt(0, i, 1);
            }
            //se suma al total
            total += numero;
        }
        Numero_a_Letra numero = new Numero_a_Letra();

        String literal = Double.toString(total);

        LBLiteral.setText(numero.Convertir(literal, true));//insertando el literal de la suma
        //muestra en el componente
        txtSumaMonto.setText(String.valueOf(total));

    }

    
    //Metodo que levanta el reporte para el recibo de pago 
    public void levantarReporte() 
    {
        Connection conex = (Connection) Conexion.getConectar();
        try 
        {
            historialPagosDao hdao=new historialPagosDao();
            Map parametro = new HashMap();
            parametro.put("idPago",hdao.buscarUltimoRegPago());//parametro numero de compra
            //parametro.put("responsable_compra", "Reynaldo Rios");//Parametro responsable de la compra
            //parametro.put("monto_literal", LBLiteralListaCompra.getText());//Parametro monto literal

            JasperPrint jasperprint = JasperFillManager.fillReport(new File(".").getAbsolutePath() + "/src/com/cajachica/reportes/ReciboPago.jasper", parametro, conex);
            JasperViewer jasperviewer = new JasperViewer(jasperprint, false);
            jasperviewer.setVisible(true);

            /*String rutaReporte = System.getProperty("user.dir") + "/src/reportes/ReciboCajaChica.jasper";
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
                JasperPrint print = JasperFillManager.fillReport(jasperReport, parametro, conex);
                JasperViewer view = new JasperViewer(print, false);
                //vtnPrincipal.sysMDI.add(view);
                view.setVisible(true);*/
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Error al abrir el reporte Reembolso de caja chica--->>  " + ex.getLocalizedMessage(), "Reporte compras", JOptionPane.ERROR_MESSAGE);
        }
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReembolso = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        fechaDesde = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        fechaHasta = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtFinanciador = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        LBLiteral = new javax.swing.JLabel();
        txtSumaMonto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();

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

        jPanel1.setBackground(new java.awt.Color(7, 237, 162));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/images/Reports-icon.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel2.setText("RESTITUIR PRESUPUESTO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(31, 31, 31))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaReembolso.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        tablaReembolso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Monto", "F.ingreso", "Proyecto", "F.financiamiento", "Nombre", "Apellido", "Glosa", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaReembolso);
        if (tablaReembolso.getColumnModel().getColumnCount() > 0) {
            tablaReembolso.getColumnModel().getColumn(0).setMinWidth(50);
            tablaReembolso.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaReembolso.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Listar por fecha"));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel3.setText("Desde:");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel4.setText("Hasta:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/buscar.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(7, 7, 7)
                        .addComponent(fechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(fechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17)
                            .addComponent(fechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Listar por financiamiento"));

        txtFinanciador.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/buscar.png"))); // NOI18N
        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtFinanciador, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jButton3)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFinanciador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado general"));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/buscar.png"))); // NOI18N
        jButton4.setText("Listado general");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(54, 54, 54))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        LBLiteral.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        LBLiteral.setText("MONTO TOTAL");

        txtSumaMonto.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel5.setText("Total a devolver:");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/caja.png"))); // NOI18N
        jButton2.setText("Reembolsar selección");
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtSumaMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(LBLiteral)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 822, Short.MAX_VALUE)
                                    .addComponent(jButton2)))))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSumaMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LBLiteral)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("RESTITUIR ", jPanel6);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/modificar.png"))); // NOI18N
        jButton5.setText("Pago parcial");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(960, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(67, 67, 67))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(430, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(53, 53, 53))
        );

        jTabbedPane1.addTab("PAGO PARCIAL", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        this.dispose();
        validaVentana = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.tablaReembolso.getModel();//creando el modelo de la tabla de los usuarios asignados
            Utilitarios util = new Utilitarios();
            util.limpiarTabla(tablaReembolso);
            //realizando la consulta para realizar el listado de los datos
            ReembolsosDao rDao = new ReembolsosDao();

            if (fechaDesde.getDate() != null && fechaHasta.getDate() != null) {
                java.sql.Date desde = new java.sql.Date(fechaDesde.getDate().getTime()); //your SQL date object
                java.sql.Date hasta = new java.sql.Date(fechaHasta.getDate().getTime()); //your SQL date object

                ResultSet lista = rDao.listarRegPresupuesto(desde, hasta);//recuperando el listado de los usuarios asignados a los proyectos

                //rellenando los elementos de la consulta en el Jtable
                Object[] fila = new Object[9];
                while (lista.next()) {
                    for (int i = 0; i < 9; i++) {
                        fila[i] = lista.getObject(i + 1);
                    }
                    modelo.addRow(fila);
                }
                sumarMontosTabla(modelo);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione u rango de fecha para listar la tabla..!!", null, JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar los datos..!! " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaReembolso.getModel();//creando el modelo de la tabla de los usuarios asignados
            Utilitarios util = new Utilitarios();
            util.limpiarTabla(tablaReembolso);
            //realizando la consulta para realizar el listado de los datos
            ReembolsosDao rDao = new ReembolsosDao();

            if (txtFinanciador.getText().length() > 0) {

                ResultSet lista = rDao.listarReembolsoByFianciador(txtFinanciador.getText());//recuperando el listado de los usuarios asignados a los proyectos
                if (lista != null) {
                    //rellenando los elementos de la consulta en el Jtable
                    Object[] fila = new Object[9];
                    while (lista.next()) {
                        for (int i = 0; i < 9; i++) {
                            fila[i] = lista.getObject(i + 1);
                        }
                        modelo.addRow(fila);
                    }
                    sumarMontosTabla(modelo);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encuantran registros..!!", null, JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el financiador para realizar el listado..!!", null, JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar los datos..!! " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaReembolso.getModel();//creando el modelo de la tabla de los usuarios asignados
            Utilitarios util = new Utilitarios();
            util.limpiarTabla(tablaReembolso);
            //realizando la consulta para realizar el listado de los datos
            ReembolsosDao rDao = new ReembolsosDao();
            ResultSet lista = rDao.listadogeneral();//recuperando el listado de los usuarios asignados a los proyectos
            if (lista != null) {
                //rellenando los elementos de la consulta en el Jtable
                Object[] fila = new Object[9];
                while (lista.next()) {
                    for (int i = 0; i < 9; i++) {
                        fila[i] = lista.getObject(i + 1);
                    }
                    modelo.addRow(fila);
                }
                sumarMontosTabla(modelo);
            } else {
                JOptionPane.showMessageDialog(null, "No se encuantran registros..!!", null, JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar los datos..!! " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel tm = (DefaultTableModel) tablaReembolso.getModel();

            if (tablaReembolso.getSelectedRowCount() > 0) {

                ReembolsosDao rDao = new ReembolsosDao();
                historialPagosDao hdao = new historialPagosDao();

                ProyectoDao pdao = new ProyectoDao();

                int idPresupuesto = Integer.parseInt(String.valueOf(tm.getValueAt(tablaReembolso.getSelectedRow(), 0)));//recuperando el Id del presupuesto
                String proy = (String) tm.getValueAt(tablaReembolso.getSelectedRow(), 3);
                String monto = (String) tm.getValueAt(tablaReembolso.getSelectedRow(), 1);
                String fianciador = (String) tm.getValueAt(tablaReembolso.getSelectedRow(), 4);

                //iniciando el confirmDialog
                int x = JOptionPane.showConfirmDialog(null, "Verifique los datos antes de realizar la operación\n\n->PROYECTO: " + proy + "\n->Fuente de financiamientos:" + fianciador + "\n->Monto (Bs.): " + monto + "\n");
                if (x == JOptionPane.YES_OPTION) {
                    if (rDao.restituirPresupouesto(idPresupuesto)) {
                        if (hdao.adicionarPago("CANCELADO", Double.parseDouble(monto), pdao.buscarProyectoById(proy)) && hdao.adicionarDetallePago(Double.parseDouble(monto), 0, hdao.buscarUltimoRegPago(), userLogin.getIdUsuario()) && hdao.adicionarItemsPresupuesto(idPresupuesto, hdao.buscarUltimoRegPago())) {
                            JOptionPane.showMessageDialog(null, "Restitucion de presupuesto satisfactorio..!!", null, JOptionPane.INFORMATION_MESSAGE);
                            Utilitarios util = new Utilitarios();
                            util.limpiarTabla(tablaReembolso);
                            levantarReporte();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al registrar el pago..!!", null, JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } else if (x == JOptionPane.NO_OPTION) {

                }

            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un elemento de la lista", null, JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la restitución del presupuesto.!!" + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String varValidacion = vtnPresupuesto.validaVentana;
        if (varValidacion == null) {

            vtnPagoParcial pre = new vtnPagoParcial();
            pre.setTitle("Pago parcial");
            pre.setResizable(false);//no es redimencionable
            pre.setMaximizable(false);//no se puede maximizar
            pre.setClosable(true);//si se puede cerra la ventana
            pre.setIconifiable(true);
            sysMDI.add(pre);
            pre.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "La ventana PAGO PARCIAL \nya esa activo..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel LBLiteral;
    private com.toedter.calendar.JDateChooser fechaDesde;
    private com.toedter.calendar.JDateChooser fechaHasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tablaReembolso;
    private javax.swing.JTextField txtFinanciador;
    private static javax.swing.JTextField txtSumaMonto;
    // End of variables declaration//GEN-END:variables
}
