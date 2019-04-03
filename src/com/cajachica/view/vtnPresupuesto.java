/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajachica.view;

import com.cajachica.core.Utilitarios;
import com.cajachica.dao.MontosTotalesDao;
import com.cajachica.dao.MovimientosCajaDao;
import com.cajachica.dao.PresupuestoDao;
import com.cajachica.pojos.MovimientosCaja;
import com.cajachica.pojos.Usuario;
import com.mysql.jdbc.Connection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.Conexion;

/**
 *
 * @author Reynaldo
 */
public class vtnPresupuesto extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnPresupuesto
     */
    public static String validaVentana;//variable que realiza la validacion de ventana abierta
    public static Usuario userLogin = null;//usuario recuperado desde la pantalla principal

    public vtnPresupuesto() {
        initComponents();
        validaVentana = "x";//insertando un valor a la variable que valida a la ventana
        /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = Principal.sysMDI.getWidth() - this.getWidth();
        int b = Principal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        OpNO.setSelected(true);
        //Recuperando el usuario logueado
        userLogin = vtnLogin.user;
        //JOptionPane.showMessageDialog(null, "--> " + userLogin.getNombre(), null, JOptionPane.ERROR_MESSAGE);

//instruciones para carga de datos de roles de usuario
        // tipo de usuario 1= ADMINITRADOR DE SISTEMA
        //                 2= USUARIO GENERAL
        int tipoUsuario = userLogin.getTipoUsuario();
        if (tipoUsuario == 2) {
            pestañaModificaciones.setEnabled(false);
            cargarComboByRolUsuario(userLogin.getIdUsuario());
            listarPresupuestos();

        } else {
            cargarCombo();
            listarPresupuestos();
        }

        //prueba verificacion de registro de monto existente 
        /*MontosTotalesDao mdao = new MontosTotalesDao();
        if (mdao.verTablaVacia(1)) 
        {
            JOptionPane.showMessageDialog(null, "-->TABLA VACIA " + userLogin.getNombre(), null, JOptionPane.ERROR_MESSAGE);
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "-->TABLA CON REGISTROS " + userLogin.getNombre(), null, JOptionPane.ERROR_MESSAGE);
        }*/
        //fin prueba
    }

    public static Usuario getUserLogin() {
        return userLogin;
    }

    public static void setUserLogin(Usuario userLogin) {
        vtnPresupuesto.userLogin = userLogin;
    }

    //Metodo que realiza la carga de los combos de los proyectos 
    public void cargarCombo() {
        try {
            PresupuestoDao pdao = new PresupuestoDao();
            ResultSet consulta = pdao.listarProyectos();
            comboProyecto.addItem("Seleccione un proyecto");
            comboproyectotabla.addItem("Seleccione un proyecto");
            while (consulta.next()) {
                comboProyecto.addItem(consulta.getString("nombreProyecto"));
                comboproyectotabla.addItem(consulta.getString("nombreProyecto"));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar los proyectos " + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo que realiza la carga de los combos de los proyectos 
    public void cargarComboByRolUsuario(int idUsuario) {
        try {
            PresupuestoDao pdao = new PresupuestoDao();
            ResultSet consulta = pdao.listarRegPresupuestoByRol(idUsuario);
            comboProyecto.addItem("Seleccione un proyecto");
            comboproyectotabla.addItem("Seleccione un proyecto");
            while (consulta.next()) {
                comboProyecto.addItem(consulta.getString("nombreProyecto"));
                comboproyectotabla.addItem(consulta.getString("nombreProyecto"));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar los proyectos " + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo que realiza el listado de los usuarios asignados a los proyectos
    public void listarPresupuestos() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaMontoProyecto.getModel();//creando el modelo de la tabla de los usuarios asignados
            Utilitarios util = new Utilitarios();
            util.limpiarTabla(tablaMontoProyecto);
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

    //Metodo que realiza el listado de los usuarios asignados a los proyectos
    public void listarPresupuestosByFiltro(String nombreProyecto) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaMontoProyecto.getModel();//creando el modelo de la tabla de los usuarios asignados
            Utilitarios util = new Utilitarios();
            util.limpiarTabla(tablaMontoProyecto);
            //realizando la consulta para realizar el listado de los datos
            PresupuestoDao pDao = new PresupuestoDao();
            ResultSet lista = pDao.listPresupuestoByNombre(nombreProyecto);//recuperando el listado de los usuarios asignados a los proyectos
            //rellenando los elementos de la consulta en el Jtable
            Object[] fila = new Object[9];
            while (lista.next()) {
                for (int i = 0; i < 9; i++) {
                    fila[i] = lista.getObject(i + 1);
                }
                modelo.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void levantarReporte() throws Exception {
        //   JasperPrint jasperprint= JasperFillManager.fillReport(new File(".").getAbsolutePath()+"" , parameters)
        Connection conex = (Connection) Conexion.getConectar();
        //JOptionPane.showMessageDialog(null, "--->" + new PresupuestoDao().ultimoRegistroPresupuesto(), "Reporte compras", JOptionPane.ERROR_MESSAGE);

        try {
            int idPresupuesto = new PresupuestoDao().ultimoRegistroPresupuesto();

            Map parametro = new HashMap();
            parametro.put("IdPresupuesto", idPresupuesto);//parametro numero de compra

            JasperPrint jasperprint = JasperFillManager.fillReport(new File(".").getAbsolutePath() + "/src/com/cajachica/reportes/ReciboCajaChica.jasper", parametro, conex);
            JasperViewer jasperviewer = new JasperViewer(jasperprint, false);
            jasperviewer.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al abrir el reporte Reembolso de caja chica" + ex.getMessage(), "Reporte compras", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo que realiza la insercion o actualizacion del total de monto del priyecto
    public void registrarMontoUpdate(int idProyecto, String monto) {
        try {
            MontosTotalesDao mdao = new MontosTotalesDao();

            if (mdao.verTablaVacia(idProyecto)) {
                mdao.registrarMontoIngreso(monto, idProyecto);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizaf los montos totales de ingreso del proyecto" + ex.getMessage(), "Reporte compras", JOptionPane.ERROR_MESSAGE);
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

        opReembolso = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboProyecto = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        pestañaModificaciones = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMontoProyecto = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        comboproyectotabla = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtFinanciador = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        OpSi = new javax.swing.JRadioButton();
        OpNO = new javax.swing.JRadioButton();

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
        jLabel1.setText("INGRESO DE PRESUPUESTO");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/images/Money-icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel3.setText("Seleccione proyecto:");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel4.setText("Monto en Bs. a asignar:");

        txtMonto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtMonto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMonto.setText("0.0");
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        jScrollPane1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        tablaMontoProyecto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Monto", "Fecha Ingreso", "Proyecto", "Financiador", "Responsable", "Apellido Resp.", "Observaciones", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaMontoProyecto);
        if (tablaMontoProyecto.getColumnModel().getColumnCount() > 0) {
            tablaMontoProyecto.getColumnModel().getColumn(0).setMinWidth(50);
            tablaMontoProyecto.getColumnModel().getColumn(0).setMaxWidth(50);
            tablaMontoProyecto.getColumnModel().getColumn(1).setMinWidth(60);
            tablaMontoProyecto.getColumnModel().getColumn(1).setPreferredWidth(60);
            tablaMontoProyecto.getColumnModel().getColumn(1).setMaxWidth(60);
            tablaMontoProyecto.getColumnModel().getColumn(5).setMinWidth(90);
            tablaMontoProyecto.getColumnModel().getColumn(5).setPreferredWidth(90);
            tablaMontoProyecto.getColumnModel().getColumn(5).setMaxWidth(90);
            tablaMontoProyecto.getColumnModel().getColumn(6).setMinWidth(100);
            tablaMontoProyecto.getColumnModel().getColumn(6).setPreferredWidth(100);
            tablaMontoProyecto.getColumnModel().getColumn(6).setMaxWidth(100);
        }

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel5.setText("Seleccione proyecto:");

        jButton3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/buscar.png"))); // NOI18N
        jButton3.setText("Filtrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/print.png"))); // NOI18N
        jButton5.setText("Imprimir recibo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboproyectotabla, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(0, 391, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboproyectotabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pestañaModificaciones.addTab("Historial ", jPanel3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Monto", "Fecha", "Proyecto", "Financiador", "Responsable"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(50);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        jButton4.setText("Modificar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pestañaModificaciones.addTab("Modificaciones ", jPanel4);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cajachica/iconos/guardar.png"))); // NOI18N
        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
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
                .addGap(29, 29, 29)
                .addComponent(jButton2)
                .addGap(61, 61, 61))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel6.setText("Fuente de financiamiento:");

        txtFinanciador.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtFinanciador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFinanciadorKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel7.setText("Observaciones:");

        txtObs.setColumns(20);
        txtObs.setRows(5);
        jScrollPane3.setViewportView(txtObs);

        jLabel8.setText("Reembolsable:");

        opReembolso.add(OpSi);
        OpSi.setText("Si");
        OpSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpSiActionPerformed(evt);
            }
        });

        opReembolso.add(OpNO);
        OpNO.setText("No");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFinanciador))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMonto))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(OpSi)
                                        .addGap(18, 18, 18)
                                        .addComponent(OpNO)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pestañaModificaciones)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pestañaModificaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(comboProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtFinanciador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(OpSi)
                            .addComponent(OpNO))
                        .addGap(23, 23, 23)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        validaVentana = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
//        if (c < '0' || c > '9') {
//            evt.consume();
//        }
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtMonto.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       String montoA=null;
       int idMonto=0;
        try {
            if (comboProyecto.getSelectedIndex() > 0) {
                PresupuestoDao pdao = new PresupuestoDao();
                MontosTotalesDao mdao = new MontosTotalesDao();
                int idProyecto = pdao.recuperarIdByNombre(String.valueOf(comboProyecto.getSelectedItem()));
                Double monto = Double.parseDouble(txtMonto.getText());
                String status;//variable que contiene el status del reembolso
                if(OpSi.isSelected()){
                    status="Devolver";
                }
                else 
                {
                    status="--";
                }
                
                if (monto > 0) {
                    if (txtFinanciador.getText().length() > 0) {
                        if (pdao.adicionarPresupuesto(txtMonto.getText(), idProyecto, txtFinanciador.getText(), txtObs.getText(), status,userLogin.getIdUsuario()));
                        {
                            ///registrando el movimiento
                            MovimientosCajaDao m=new MovimientosCajaDao();
                            MovimientosCaja movDao=new MovimientosCaja();//movientos de la caja chica
                            movDao.setIdPresupuesto(pdao.ultimoRegistroPresupuesto());
                            m.registrarMontoIngreso(movDao);
                            //fin del registro del movimientos
                            
                            JOptionPane.showMessageDialog(null, " Monto registrado correctamente..!!", null, JOptionPane.INFORMATION_MESSAGE);

                            if (mdao.verTablaVacia(idProyecto)) {
                                mdao.registrarMontoIngreso(txtMonto.getText(), idProyecto);//relizando la aactualizacion de los montos totales de ingresos 
                            } 
                            else 
                            {
                                double montoS = Double.parseDouble(txtMonto.getText());//recuperando el nuevo monto a sumar 
                                ResultSet consulta=mdao.recuperarMontoIngreso(idProyecto);
                                if (consulta.next()) 
                                {
                                    montoA = consulta.getString(1);
                                    idMonto = consulta.getInt(2);
                                }
                                
                                double totalUpdate=Double.parseDouble(montoA)+montoS;
                                mdao.updateIngresosTotales(String.valueOf(totalUpdate), idProyecto,idMonto);
                                //JOptionPane.showMessageDialog(null, "Segundo ingreso MONTO RECUPERADO-->"+totalUpdate+"--"+montoA+"--"+idMonto, null, JOptionPane.ERROR_MESSAGE);
                            }
                            txtMonto.setText("0.0");
                            txtFinanciador.setText("");
                            txtObs.setText("");
                            comboProyecto.setSelectedIndex(0);
                            listarPresupuestos();
                            levantarReporte();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, " Ingrese el financiador..!!", null, JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El monto debe ser mayor a 0", null, JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un proyecto..!!", null, JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar el monto..!! " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtFinanciadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFinanciadorKeyTyped
        // TODO add your handling code here:
        txtFinanciador.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

    }//GEN-LAST:event_txtFinanciadorKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            if (comboproyectotabla.getSelectedIndex() > 0) {
                listarPresupuestosByFiltro(comboproyectotabla.getSelectedItem().toString());
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un proyecto..!! ", null, JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al realizar la lectura de los datos..!! " + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //   JasperPrint jasperprint= JasperFillManager.fillReport(new File(".").getAbsolutePath()+"" , parameters)
        Connection conex = (Connection) Conexion.getConectar();
        try {
            if (tablaMontoProyecto.getSelectedRows().length != 0) {
                DefaultTableModel tm = (DefaultTableModel) tablaMontoProyecto.getModel();
                int idPresupuesto = Integer.parseInt(String.valueOf(tm.getValueAt(tablaMontoProyecto.getSelectedRow(), 0)));

                Map parametro = new HashMap();
                parametro.put("IdPresupuesto", idPresupuesto);//parametro numero de compra
                //parametro.put("responsable_compra", "Reynaldo Rios");//Parametro responsable de la compra
                //parametro.put("monto_literal", LBLiteralListaCompra.getText());//Parametro monto literal

                JasperPrint jasperprint = JasperFillManager.fillReport(new File(".").getAbsolutePath() + "/src/com/cajachica/reportes/ReciboCajaChica.jasper", parametro, conex);
                JasperViewer jasperviewer = new JasperViewer(jasperprint, false);
                jasperviewer.setVisible(true);

                /*String rutaReporte = System.getProperty("user.dir") + "/src/reportes/ReciboCajaChica.jasper";
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
                JasperPrint print = JasperFillManager.fillReport(jasperReport, parametro, conex);
                JasperViewer view = new JasperViewer(print, false);
                //vtnPrincipal.sysMDI.add(view);
                view.setVisible(true);*/
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un elemento de la tabla..!!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al abrir el reporte Reembolso de caja chica" + ex.getMessage(), "Reporte compras", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void OpSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpSiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OpSiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton OpNO;
    private javax.swing.JRadioButton OpSi;
    private javax.swing.JComboBox<String> comboProyecto;
    private javax.swing.JComboBox<String> comboproyectotabla;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.ButtonGroup opReembolso;
    private javax.swing.JTabbedPane pestañaModificaciones;
    private javax.swing.JTable tablaMontoProyecto;
    private javax.swing.JTextField txtFinanciador;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextArea txtObs;
    // End of variables declaration//GEN-END:variables
}
