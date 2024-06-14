/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conection_BD;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author jo199
 */
public class consultaVehiculosMantenimiento_tabla2 extends javax.swing.JFrame {

    public int id_vehi = 0, id = 0;
    DefaultTableModel modelo_tabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            //ya no seran editadas
            if (columnas > 0 && filas > 0) {
                return false;
            }
            return false;

        }

    };

    /**
     * Creates new form consultaVehiculosMantenimiento_tabla2
     */
    public consultaVehiculosMantenimiento_tabla2() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoa.png")).getImage());
        //boton_reporte.setEnabled(false);//desabilidatmos el botn de reporte
        //cerrar();
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        //cerrar();
        id_vehi = consultaVehiculosMantenimiento_tabla1.in_vehiculo_mant;
        try {
            Connection cn = Conection_BD.conexion_bd();
            PreparedStatement pst = cn.prepareStatement("select "
                    + "b.id_vehiculo, "
                    + "b.no_inventario, "
                    + "b.marca, "
                    + "b.modelo, "
                    + "b.kilometraje, "
                    + "b.no_motor, "
                    + "b.no_placa, "
                    + "b.cilindraje, "
                    + "b.tipo_vehiculo, "
                    + "b.tarjeta, "
                    + "e.nombre_empleado, "
                    + "b.descripcion_vehiculo, "
                    + "b.estatus "
                    + "from vehiculos b JOIN empleados e "
                    + "ON (b.id_empleado = e.id_empleado) "
                    + "where b.id_vehiculo ='" + id_vehi + "' and b.estatus = 'ACTIVO' ");

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_vehiculo");
                etiqueta_no_inventario.setText(rs.getString("no_inventario"));
                etiqueta_marca.setText(rs.getString("marca"));
                etiqueta_modelo.setText(rs.getString("modelo"));
                etiqueta_kilometraje_actual.setText(rs.getString("kilometraje"));
                etiqueta_no_motor.setText(rs.getString("no_motor"));
                etiqueta_placas.setText(rs.getString("no_placa"));//.setSelectedItem(rs.getString("tipo_sico_sareco"));
                etiqueta_cilindraje.setText(rs.getString("cilindraje"));
                etiqueta_tipo.setText(rs.getString("tipo_vehiculo"));
                etiqueta_tarjeta.setText(rs.getString("tarjeta"));
                etiqueta_descripcion.setText(rs.getString("descripcion_vehiculo"));

                //fecha.setText(rs.getString("ultima_modificacion"));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al cargar usuario: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al cargar informacion", "Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            Connection cn = Conection_BD.conexion_bd();
            PreparedStatement pst = cn.prepareStatement("select "
                    + "tipo_servicio,"
                    + "servicios, "
                    + "descripcion, "
                    + "DATE_ADD(fecha, interval 1 day) "
                    + "from mantenimiento_vehiculos "
                    + "where id_vehiculo = '" + id_vehi + "' "
                    + "ORDER BY fecha");
            ResultSet rs = pst.executeQuery();
            tabla = new JTable(modelo_tabla);
            //para no poder mover las columnas
            tabla.getTableHeader().setReorderingAllowed(false);
            //tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            //tamaño de alto de la tabla 
            tabla.setRowHeight(30);
            //color de fondo
            tabla.setBackground(new Color(156, 197, 152));
            //genera la fuente para los datos de la tabla
            tabla.setFont(new Font("Arial", Font.PLAIN, 16));
            //color del borde de la tabla
            tabla.setGridColor(new Color(255, 255, 255));
            //tabla.setRowMargin(10);
            //poner bordes
            //tabla_equipos.setShowGrid(true);
            tabla.setShowHorizontalLines(true);
            //tabla_equipos.setShowVerticalLines(true);
            //para que la tabla tenga un scroll
            jScrollPane1.setViewportView(tabla);
            modelo_tabla.addColumn("Tipo Servicio");
            modelo_tabla.addColumn("Servicios");            
            modelo_tabla.addColumn("Descripcion");
            modelo_tabla.addColumn("Fecha de Servicio");

            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(300);//tipo servicio
            tabla.getColumnModel().getColumn(1).setPreferredWidth(700);//servicios
            tabla.getColumnModel().getColumn(2).setPreferredWidth(250);//desc  
            tabla.getColumnModel().getColumn(3).setPreferredWidth(200);//fecha 

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);

            //modelo_tabla.
            while (rs.next()) {
                Object[] fila = new Object[4];//por las columnas que tengo
                for (int i = 0; i < 4; i++) {
                    fila[i] = rs.getObject(i + 1);

                    //centra el contenido de la tabla
                    tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
                    //cambiamos el color del emcabezado y centrado
                    TableColumn column = tabla.getTableHeader().getColumnModel().getColumn(i);
                    column.setHeaderRenderer(cellRenderer);

                    cellRenderer.setFont(new Font("Arial", Font.BOLD, 11));
                    cellRenderer.setHorizontalAlignment(cellRenderer.CENTER);
                    cellRenderer.setForeground(Color.white);

                }

                tabla.getTableHeader().setBackground(new Color(255, 0, 0));
                modelo_tabla.addRow(fila);
                ((DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

            }

            cn.close();
        } catch (Exception e) {
            System.out.println("Error al llenar tabla de equipos: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion ", "Error", JOptionPane.ERROR_MESSAGE);
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
        jLabel6 = new javax.swing.JLabel();
        etiqueta_no_motor = new javax.swing.JLabel();
        etiqueta_tipo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        etiqueta_placas = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        etiqueta_descripcion = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        etiqueta_no_inventario = new javax.swing.JLabel();
        etiqueta_tarjeta = new javax.swing.JLabel();
        etiqueta_marca = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        etiqueta_cilindraje = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        etiqueta_kilometraje_actual = new javax.swing.JLabel();
        etiqueta_modelo = new javax.swing.JLabel();
        boton_buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consulta Mantenimientos");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("No. Placas:");

        etiqueta_no_motor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_no_motor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_no_motor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        etiqueta_tipo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_tipo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_tipo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Cilindraje: ");

        etiqueta_placas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_placas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_placas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("No. Inventario:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Modelo:");

        etiqueta_descripcion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_descripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_descripcion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Tipo de vehiculo:");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Descripcion: ");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Marca:");

        etiqueta_no_inventario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_no_inventario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_no_inventario.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        etiqueta_tarjeta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_tarjeta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_tarjeta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        etiqueta_marca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_marca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_marca.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Terminacion de tarjeta asignada:");

        etiqueta_cilindraje.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_cilindraje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_cilindraje.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("No. Motor:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Kilometraje actual:");

        etiqueta_kilometraje_actual.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_kilometraje_actual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_kilometraje_actual.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        etiqueta_modelo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_modelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_modelo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        boton_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar.setText("Regresar");
        boton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boton_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(etiqueta_no_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(etiqueta_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(etiqueta_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(14, 14, 14)
                                .addComponent(etiqueta_kilometraje_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(etiqueta_no_motor, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(etiqueta_placas, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(etiqueta_cilindraje, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel8)
                                .addGap(12, 12, 12)
                                .addComponent(etiqueta_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel10)
                                .addGap(6, 6, 6)
                                .addComponent(etiqueta_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(12, 12, 12)
                                .addComponent(etiqueta_tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(42, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqueta_no_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqueta_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqueta_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqueta_kilometraje_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqueta_no_motor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqueta_placas, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqueta_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiqueta_cilindraje, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiqueta_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqueta_tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void boton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarActionPerformed

        consultaVehiculosMantenimiento_tabla1 p = new consultaVehiculosMantenimiento_tabla1();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_buscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(consultaVehiculosMantenimiento_tabla2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consultaVehiculosMantenimiento_tabla2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consultaVehiculosMantenimiento_tabla2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consultaVehiculosMantenimiento_tabla2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(consultaVehiculosMantenimiento_tabla2.class.getName()).log(Level.SEVERE, null, ex);
                }
                new consultaVehiculosMantenimiento_tabla2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_buscar;
    private javax.swing.JLabel etiqueta_cilindraje;
    private javax.swing.JLabel etiqueta_descripcion;
    private javax.swing.JLabel etiqueta_kilometraje_actual;
    private javax.swing.JLabel etiqueta_marca;
    private javax.swing.JLabel etiqueta_modelo;
    private javax.swing.JLabel etiqueta_no_inventario;
    private javax.swing.JLabel etiqueta_no_motor;
    private javax.swing.JLabel etiqueta_placas;
    private javax.swing.JLabel etiqueta_tarjeta;
    private javax.swing.JLabel etiqueta_tipo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
