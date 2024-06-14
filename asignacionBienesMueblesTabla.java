/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conection_BD;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author crysi
 */
public class asignacionBienesMueblesTabla extends javax.swing.JFrame {

    /**
     * Creates new form asignacionBienesMueblesTabla
     */
    public asignacionBienesMueblesTabla() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoa.png")).getImage());
        mostrarBienes();
        //cerrar();
    }

    public static String id = "";

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bienes = new javax.swing.JTable();
        boton_asignar = new javax.swing.JButton();
        barra_busqueda = new javax.swing.JTextField();
        boton_buscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        boton_buscar1 = new javax.swing.JButton();
        boton_buscar3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asignacion de Bienes Muebles");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        bienes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(bienes);

        boton_asignar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_asignar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/seleccion_30.png"))); // NOI18N
        boton_asignar.setText("Seleccionar");
        boton_asignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_asignarActionPerformed(evt);
            }
        });

        barra_busqueda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barra_busquedaActionPerformed(evt);
            }
        });

        boton_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar.setText("Buscar");
        boton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Buscar por No.Inventario");

        boton_buscar1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar1.setText("Reiniciar");
        boton_buscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscar1ActionPerformed(evt);
            }
        });

        boton_buscar3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar3.setText("Regresar");
        boton_buscar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(barra_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boton_buscar)
                        .addGap(13, 13, 13)
                        .addComponent(boton_buscar1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1025, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boton_asignar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(boton_buscar)
                        .addComponent(barra_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(boton_buscar1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(boton_asignar)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void boton_asignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_asignarActionPerformed
        int selection = bienes.getSelectedRow();
        if (selection == (-1)) {
            JOptionPane.showMessageDialog(null, "Primero debes seleccionar un bien para asignarlo");
        } else {
            id = bienes.getValueAt(selection, 0).toString();
            if (!id.equals("")) {

                asignacionBienesMuebles abm = new asignacionBienesMuebles();
                abm.setVisible(true);
                dispose();
            }
        }


    }//GEN-LAST:event_boton_asignarActionPerformed

    private void boton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarActionPerformed

        //Busca por el nombre del bien o por su No. Inventario
        String busqueda = barra_busqueda.getText().toString();

        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        //DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        //cellRenderer.setBackground(new Color(51, 102, 255));
        try {
            Connection cn1 = Conection_BD.conexion_bd();
            PreparedStatement pst1 = cn1.prepareStatement("select id_bien, no_inventario, descripcion_bien, tipo_sico_sareco, valor_estimado, nombre_empleado, b.estatus from bienes b join empleados e where b.id_empleado = e.id_empleado and upper(b.estatus)='ACTIVO' and (b.no_inventario like '" + busqueda + "%');");
            ResultSet rs = pst1.executeQuery();

            bienes = new JTable(modelo_tabla);
            //para no poder mover las columnas
            bienes.getTableHeader().setReorderingAllowed(false);
            //tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            //tamaño de alto de la tabla 
            bienes.setRowHeight(30);
            //color de fondo
            bienes.setBackground(new Color(156, 197, 152));
            //genera la fuente para los datos de la tabla
            bienes.setFont(new Font("Arial", Font.PLAIN, 16));
            //color del borde de la tabla
            bienes.setGridColor(new Color(255, 255, 255));
            //tabla.setRowMargin(10);
            //poner bordes
            //tabla_equipos.setShowGrid(true);
            bienes.setShowHorizontalLines(true);
            //tabla_equipos.setShowVerticalLines(true);
            //para que la tabla tenga un scroll
            jScrollPane1.setViewportView(bienes);

            modelo_tabla.addColumn("Id");
            modelo_tabla.addColumn("No Inventario");
            modelo_tabla.addColumn("Descripcion");
            modelo_tabla.addColumn("Tipo");
            modelo_tabla.addColumn("Valor estimado");
            modelo_tabla.addColumn("Resguardatario");
            //bienes.setAutoResizeMode(AUTO_RESIZE_OFF);
            bienes.getColumnModel().getColumn(0).setPreferredWidth(60);
            bienes.getColumnModel().getColumn(1).setPreferredWidth(100);
            bienes.getColumnModel().getColumn(2).setPreferredWidth(200);
            bienes.getColumnModel().getColumn(3).setPreferredWidth(100);

            bienes.getColumnModel().getColumn(0).setResizable(false);
            bienes.getColumnModel().getColumn(1).setResizable(false);
            bienes.getColumnModel().getColumn(2).setResizable(false);
            bienes.getColumnModel().getColumn(3).setResizable(false);

            //modelo_bienes.
            while (rs.next()) {
                Object[] fila = new Object[6];//por las columnas que tengo
                for (int i = 0; i < 6; i++) {
                    fila[i] = rs.getObject(i + 1);

                    //centra el contenido de la bienes
                    bienes.getColumnModel().getColumn(i).setCellRenderer(tcr);
                    //cambiamos el color del emcabezado y centrado
                    TableColumn column = bienes.getTableHeader().getColumnModel().getColumn(i);
                    column.setHeaderRenderer(cellRenderer);

                    cellRenderer.setFont(new Font("Arial", Font.BOLD, 11));
                    cellRenderer.setHorizontalAlignment(cellRenderer.CENTER);
                    cellRenderer.setForeground(Color.white);

                }

                bienes.getTableHeader().setBackground(new Color(255, 0, 0));
                modelo_tabla.addRow(fila);
                ((DefaultTableCellRenderer) bienes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

            }

            cn1.close();
        } catch (Exception e) {
            System.out.println("Error al llenar tabla de equipos: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion de productos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        boton_buscar.setEnabled(true);


    }//GEN-LAST:event_boton_buscarActionPerformed

    private void boton_buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscar1ActionPerformed
       mostrarBienes();
    }//GEN-LAST:event_boton_buscar1ActionPerformed

    private void barra_busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barra_busquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barra_busquedaActionPerformed

    private void boton_buscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscar3ActionPerformed

        menuBienesMuebles p = new menuBienesMuebles();
            p.setVisible(true);
            this.dispose();

    }//GEN-LAST:event_boton_buscar3ActionPerformed
    DefaultTableModel modelo_tabla = new DefaultTableModel();

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
            java.util.logging.Logger.getLogger(asignacionBienesMueblesTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(asignacionBienesMueblesTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(asignacionBienesMueblesTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(asignacionBienesMueblesTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(bajasBienesMuebles_tabla.class.getName()).log(Level.SEVERE, null, ex);
                }
                new asignacionBienesMueblesTabla().setVisible(true);
            }
        });
    }

    public void mostrarBienes() {
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));

        try {
            Connection cn = Conection_BD.conexion_bd();
            PreparedStatement pst = cn.prepareStatement("select id_bien, no_inventario, descripcion_bien, marca, estado_actual, valor_estimado, tipo_sico_sareco from bienes where id_empleado = (select id_empleado from empleados where nombre_empleado = 'Almacen') and upper(estatus) = 'ACTIVO' and tipo_info_mueble='MUEBLE'");
            ResultSet rs = pst.executeQuery();

            bienes = new JTable(modelo_tabla);
            //para no poder mover las columnas
            bienes.getTableHeader().setReorderingAllowed(false);
            //tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            //tamaño de alto de la tabla 
            bienes.setRowHeight(30);
            //color de fondo
            bienes.setBackground(new Color(156, 197, 152));
            //genera la fuente para los datos de la tabla
            bienes.setFont(new Font("Arial", Font.PLAIN, 16));
            //color del borde de la tabla
            bienes.setGridColor(new Color(255, 255, 255));
            //tabla.setRowMargin(10);
            //poner bordes
            //tabla_equipos.setShowGrid(true);
            bienes.setShowHorizontalLines(true);
            //tabla_equipos.setShowVerticalLines(true);
            //para que la tabla tenga un scroll
            jScrollPane1.setViewportView(bienes);
            modelo_tabla.addColumn("ID");
            modelo_tabla.addColumn("No Inventario");
            modelo_tabla.addColumn("Descripcion");
            modelo_tabla.addColumn("Marca");
            modelo_tabla.addColumn("Estado");
            modelo_tabla.addColumn("Valor Estimado");
            modelo_tabla.addColumn("Tipo");

            bienes.setAutoResizeMode(AUTO_RESIZE_OFF);
            bienes.getColumnModel().getColumn(0).setPreferredWidth(100);//No Inventario
            bienes.getColumnModel().getColumn(1).setPreferredWidth(200);//No Inventario
            bienes.getColumnModel().getColumn(2).setPreferredWidth(200);//Marca
            bienes.getColumnModel().getColumn(3).setPreferredWidth(200);//Modelo
            bienes.getColumnModel().getColumn(4).setPreferredWidth(250);//Kilometraje
            bienes.getColumnModel().getColumn(5).setPreferredWidth(200);//No. Motor
            bienes.getColumnModel().getColumn(6).setPreferredWidth(150);//Placas     

            bienes.getColumnModel().getColumn(0).setResizable(false);
            bienes.getColumnModel().getColumn(1).setResizable(false);
            bienes.getColumnModel().getColumn(2).setResizable(false);
            bienes.getColumnModel().getColumn(3).setResizable(false);
            bienes.getColumnModel().getColumn(4).setResizable(false);
            bienes.getColumnModel().getColumn(5).setResizable(false);
            bienes.getColumnModel().getColumn(6).setResizable(false);

            //modelo_bienes.
            while (rs.next()) {
                Object[] fila = new Object[7];//por las columnas que tengo
                for (int i = 0; i < 7; i++) {
                    fila[i] = rs.getObject(i + 1);

                    //centra el contenido de la bienes
                    bienes.getColumnModel().getColumn(i).setCellRenderer(tcr);
                    //cambiamos el color del emcabezado y centrado
                    TableColumn column = bienes.getTableHeader().getColumnModel().getColumn(i);
                    column.setHeaderRenderer(cellRenderer);

                    cellRenderer.setFont(new Font("Arial", Font.BOLD, 11));
                    cellRenderer.setHorizontalAlignment(cellRenderer.CENTER);
                    cellRenderer.setForeground(Color.white);

                }

                bienes.getTableHeader().setBackground(new Color(255, 0, 0));
                modelo_tabla.addRow(fila);
                ((DefaultTableCellRenderer) bienes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

            }

            cn.close();
        } catch (Exception e) {
            System.out.println("Error al llenar bienes de equipos: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion de productos", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
//
//    public void cerrar() {
//        try {
//            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//            addWindowListener(new WindowAdapter() {
//                public void windowClosing(WindowEvent e) {
//                    confirmarSalida();
//                }
//            });
//            //this.setVisible(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void confirmarSalida() {
//        int valor = JOptionPane.showConfirmDialog(this, "¿Seguro de cerrar esta ventana?", "ADVERTENCIA", JOptionPane.YES_NO_OPTION);
//        if (valor == JOptionPane.YES_OPTION) {
//            menuBienesMuebles p = new menuBienesMuebles();
//            p.setVisible(true);
//            this.dispose();
//        }
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barra_busqueda;
    private javax.swing.JTable bienes;
    private javax.swing.JButton boton_asignar;
    private javax.swing.JButton boton_buscar;
    private javax.swing.JButton boton_buscar1;
    private javax.swing.JButton boton_buscar2;
    private javax.swing.JButton boton_buscar3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
