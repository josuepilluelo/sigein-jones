/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conection_BD;
import static interfaces.bajas_empleados_tabla.id_empleado;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
 * @author jo199
 */
public class bajasBienesPersonales_datos extends javax.swing.JFrame {

    public int id_em = 0, id = 0,variable_bienesper = 0;
    public static int id_bienpersonal=0;

    /**
     * Creates new form bajasBienesPersonales_datos
     */
    DefaultTableModel modelo_tabla1 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            //ya no seran editadas
            if (columnas > 0 && filas > 0) {
                return true;
            }
            return true;

        }
    };

    public bajasBienesPersonales_datos() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoa.png")).getImage());
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
       // boton_transparente(boton_aceptar);
        //cerrar();
        id_em = bajasBienesPersonales_tabla.id_empleado;
        try {
            Connection cn = Conection_BD.conexion_bd();
            PreparedStatement pst = cn.prepareStatement("select "
                    + "id_empleado, "
                    + "nombre_empleado "
                    + "from empleados "
                    + "where id_empleado = '" + id_em + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_empleado");
                etiqueta_empleado.setText(rs.getString("nombre_empleado"));

                //fecha.setText(rs.getString("ultima_modificacion"));
            }
            try {
                Connection cn1 = Conection_BD.conexion_bd();
                PreparedStatement pst1 = cn1.prepareStatement("select "
                        + "id_bien_personal, "
                        + "b.bien_personal "
                        + "from bienes_personales b JOIN empleados e "
                        + "ON (b.id_empleado = e.id_empleado) "
                        + "where e.id_empleado = '" + id_em + "' "
                        + "ORDER BY b.id_bien_personal");

                ResultSet rs1 = pst1.executeQuery();
                tabla_1 = new JTable(modelo_tabla1);
                //para no poder mover las columnas
                tabla_1.getTableHeader().setReorderingAllowed(false);
                //tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

                DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.CENTER);
                //tamaño de alto de la tabla 
                tabla_1.setRowHeight(30);
                //color de fondo
                tabla_1.setBackground(new Color(156, 197, 152));
                //genera la fuente para los datos de la tabla
                tabla_1.setFont(new Font("Arial", Font.PLAIN, 16));
                //color del borde de la tabla
                tabla_1.setGridColor(new Color(255, 255, 255));
                //tabla.setRowMargin(10);
                //poner bordes
                //tabla_equipos.setShowGrid(true);
                tabla_1.setShowHorizontalLines(true);
                //tabla_equipos.setShowVerticalLines(true);
                //para que la tabla tenga un scroll
                jScrollPane2.setViewportView(tabla_1);
                modelo_tabla1.addColumn("NP");
                modelo_tabla1.addColumn("Bien personal");

                tabla_1.setAutoResizeMode(AUTO_RESIZE_OFF);
                tabla_1.getColumnModel().getColumn(0).setPreferredWidth(100);//np
                tabla_1.getColumnModel().getColumn(1).setPreferredWidth(600);//bien_personal

                tabla_1.getColumnModel().getColumn(0).setResizable(false);
                tabla_1.getColumnModel().getColumn(1).setResizable(false);

                //modelo_tabla.
                while (rs1.next()) {
                    variable_bienesper = 1;
                    Object[] fila = new Object[2];//por las columnas que tengo
                    for (int i = 0; i < 2; i++) {
                        fila[i] = rs1.getObject(i + 1);

                        //centra el contenido de la tabla
                        tabla_1.getColumnModel().getColumn(i).setCellRenderer(tcr);
                        //cambiamos el color del emcabezado y centrado
                        TableColumn column = tabla_1.getTableHeader().getColumnModel().getColumn(i);
                        column.setHeaderRenderer(cellRenderer);

                        cellRenderer.setFont(new Font("Arial", Font.BOLD, 11));
                        cellRenderer.setHorizontalAlignment(cellRenderer.CENTER);
                        cellRenderer.setForeground(Color.white);

                    }

                    tabla_1.getTableHeader().setBackground(new Color(255, 0, 0));
                    modelo_tabla1.addRow(fila);
                    ((DefaultTableCellRenderer) tabla_1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

                }

                cn1.close();
            } catch (Exception e) {
                System.out.println("Error al llenar tabla de equipos: " + e);
                //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
                JOptionPane.showMessageDialog(null, "Error al mostrar informacion de bienes personales", "Error", JOptionPane.ERROR_MESSAGE);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al cargar usuario: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al cargar informacion", "Error", JOptionPane.ERROR_MESSAGE);
        }
        datosTabla();
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
        etiqueta_empleado = new javax.swing.JLabel();
        boton_buscar1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bajas Bienes personales");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Empleado:");

        etiqueta_empleado.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        etiqueta_empleado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        boton_buscar1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar1.setText("Regresar");
        boton_buscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscar1ActionPerformed(evt);
            }
        });

        tabla_1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabla_1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiqueta_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiqueta_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void boton_buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscar1ActionPerformed

        bajasBienesPersonales_tabla p = new bajasBienesPersonales_tabla();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_buscar1ActionPerformed

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
            java.util.logging.Logger.getLogger(bajasBienesPersonales_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bajasBienesPersonales_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bajasBienesPersonales_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bajasBienesPersonales_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bajasBienesPersonales_datos().setVisible(true);
            }
        }); 
    }

    public void boton_transparente(JButton boton) {
        boton.setOpaque(true);
        boton.setContentAreaFilled(false);
    }

    public void limpiarCampos() {
        Icon icono_correcto = new ImageIcon(getClass().getResource("/imagenes/correcto.png"));
        int valor = JOptionPane.showConfirmDialog(null, "Se ha actualizado la informacion  correctamente", "Baja Bien Mueble", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, icono_correcto);
        if (valor == JOptionPane.YES_OPTION) {

        }

        bajasBienesPersonales_tabla p = new bajasBienesPersonales_tabla();
        p.setVisible(true);
        this.dispose();

    }
        public void datosTabla() {
        tabla_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int num_fila = tabla_1.rowAtPoint(evt.getPoint());
                //aqui se saca el nombre el id porque es columna 0
                int num_columna = 0;
                if (num_fila > -1) {
                    //nombre = (String) modelo_tabla.getValueAt(num_fila, num_columna);
                    id_bienpersonal = (int) modelo_tabla1.getValueAt(num_fila, num_columna);
                    System.out.println(modelo_tabla1.getValueAt(num_fila, num_columna));
                    //nombre = (int) modelo_tabla.getValueAt(num_fila, num_columna);
                    bajasBienesPersonales_confirmacion inf = new bajasBienesPersonales_confirmacion();
                    inf.setVisible(true);
                }
                dispose();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_buscar1;
    private javax.swing.JLabel etiqueta_empleado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla_1;
    // End of variables declaration//GEN-END:variables
}
