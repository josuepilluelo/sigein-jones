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
 * @author crysi
 */
public class asignacionVehiculosTabla extends javax.swing.JFrame {

    /**
     * Creates new form asignacionBienesInformaticosTabla
     */
    public asignacionVehiculosTabla() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoa.png")).getImage());
        mostrarBienes();
    }

        public void mostrarBienes(){
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));

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
                    + "WHERE b.estatus = 'ACTIVO' "
                    + "ORDER BY b.no_inventario");
            ResultSet rs = pst.executeQuery();
            tablaVehiculos = new JTable(modelo_tabla);
            //para no poder mover las columnas
            tablaVehiculos.getTableHeader().setReorderingAllowed(false);
            //tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            //tamaño de alto de la tabla 
            tablaVehiculos.setRowHeight(30);
            //color de fondo
            tablaVehiculos.setBackground(new Color(156, 197, 152));
            //genera la fuente para los datos de la tabla
            tablaVehiculos.setFont(new Font("Arial", Font.PLAIN, 16));
            //color del borde de la tabla
            tablaVehiculos.setGridColor(new Color(255, 255, 255));
            //tabla.setRowMargin(10);
            //poner bordes
            //tabla_equipos.setShowGrid(true);
            tablaVehiculos.setShowHorizontalLines(true);
            //tabla_equipos.setShowVerticalLines(true);
            //para que la tabla tenga un scroll
            jScrollPane1.setViewportView(tablaVehiculos);
            modelo_tabla.addColumn("Id Vehiculo");
            modelo_tabla.addColumn("No Inventario");
            modelo_tabla.addColumn("Marca");
            modelo_tabla.addColumn("Modelo");
            modelo_tabla.addColumn("Kilometraje");
            modelo_tabla.addColumn("No. Motor");
            modelo_tabla.addColumn("Placas");
            modelo_tabla.addColumn("No. Cilindros");
            modelo_tabla.addColumn("Tipo");
            modelo_tabla.addColumn("Tarjeta");
            modelo_tabla.addColumn("Responsable");
            modelo_tabla.addColumn("Descripcion");
            modelo_tabla.addColumn("Estado");

            tablaVehiculos.setAutoResizeMode(AUTO_RESIZE_OFF);
            tablaVehiculos.getColumnModel().getColumn(0).setPreferredWidth(100);//No Inventario
            tablaVehiculos.getColumnModel().getColumn(1).setPreferredWidth(200);//No Inventario
            tablaVehiculos.getColumnModel().getColumn(2).setPreferredWidth(200);//Marca
            tablaVehiculos.getColumnModel().getColumn(3).setPreferredWidth(200);//Modelo
            tablaVehiculos.getColumnModel().getColumn(4).setPreferredWidth(250);//Kilometraje
            tablaVehiculos.getColumnModel().getColumn(5).setPreferredWidth(200);//No. Motor
            tablaVehiculos.getColumnModel().getColumn(6).setPreferredWidth(150);//Placas
            tablaVehiculos.getColumnModel().getColumn(7).setPreferredWidth(200);//No. Cilindros
            tablaVehiculos.getColumnModel().getColumn(8).setPreferredWidth(200);//Tipo
            tablaVehiculos.getColumnModel().getColumn(9).setPreferredWidth(100);//Tarjeta
            tablaVehiculos.getColumnModel().getColumn(10).setPreferredWidth(250);//Responsable
            tablaVehiculos.getColumnModel().getColumn(11).setPreferredWidth(200);//Descripcion
            tablaVehiculos.getColumnModel().getColumn(12).setPreferredWidth(100);//Estado         

            tablaVehiculos.getColumnModel().getColumn(0).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(1).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(2).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(3).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(4).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(5).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(6).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(7).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(8).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(9).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(10).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(11).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(12).setResizable(false);

            //modelo_tabla.
            while (rs.next()) {
                Object[] fila = new Object[13];//por las columnas que tengo
                for (int i = 0; i < 13; i++) {
                    fila[i] = rs.getObject(i + 1);

                    //centra el contenido de la tabla
                    tablaVehiculos.getColumnModel().getColumn(i).setCellRenderer(tcr);
                    //cambiamos el color del emcabezado y centrado
                    TableColumn column = tablaVehiculos.getTableHeader().getColumnModel().getColumn(i);
                    column.setHeaderRenderer(cellRenderer);

                    cellRenderer.setFont(new Font("Arial", Font.BOLD, 11));
                    cellRenderer.setHorizontalAlignment(cellRenderer.CENTER);
                    cellRenderer.setForeground(Color.white);

                }

                tablaVehiculos.getTableHeader().setBackground(new Color(255, 0, 0));
                modelo_tabla.addRow(fila);
                ((DefaultTableCellRenderer) tablaVehiculos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

            }
            

            cn.close();
        } catch (Exception e) {
            System.out.println("Error al llenar bienes de equipos: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion de productos", "Error", JOptionPane.ERROR_MESSAGE);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVehiculos = new javax.swing.JTable();
        boton_asignar = new javax.swing.JButton();
        barra_busqueda = new javax.swing.JTextField();
        boton_buscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        boton_buscar2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asignacion de Vehiculos");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        tablaVehiculos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaVehiculos);

        boton_asignar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_asignar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/seleccion_30.png"))); // NOI18N
        boton_asignar.setText("Asignar");
        boton_asignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_asignarActionPerformed(evt);
            }
        });

        barra_busqueda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        boton_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar.setText("Buscar");
        boton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Buscar por No.Inventario:");

        boton_buscar2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar2.setText("Regresar");
        boton_buscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(barra_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(boton_buscar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(438, 438, 438)
                        .addComponent(boton_asignar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(barra_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(boton_buscar))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(boton_asignar)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static String id_vehiculo = "";
    
    private void boton_asignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_asignarActionPerformed
        int selection = tablaVehiculos.getSelectedRow();
        if(selection == (-1)){
            JOptionPane.showMessageDialog(null, "Primero debes seleccionar un bien para asignarlo");
        }
        else{
            id_vehiculo = tablaVehiculos.getValueAt(selection,0).toString();
            if(!id_vehiculo.equals("")){
                asignacionVehiculos av = new asignacionVehiculos();
                av.setVisible(true);
                dispose();
            }
        }

    }//GEN-LAST:event_boton_asignarActionPerformed

    DefaultTableModel modelo_tabla = new DefaultTableModel();
    
    private void boton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarActionPerformed

        //Busca por el nombre del bien o por su No. Inventario
        String busqueda = barra_busqueda.getText().toString();

        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));

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
                    + "WHERE b.estatus = 'ACTIVO' and marca like ('%"+busqueda+"%') "
                    + "ORDER BY b.no_inventario");
            ResultSet rs = pst.executeQuery();
            tablaVehiculos = new JTable(modelo_tabla);
            //para no poder mover las columnas
            tablaVehiculos.getTableHeader().setReorderingAllowed(false);
            //tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            //tamaño de alto de la tabla 
            tablaVehiculos.setRowHeight(30);
            //color de fondo
            tablaVehiculos.setBackground(new Color(156, 197, 152));
            //genera la fuente para los datos de la tabla
            tablaVehiculos.setFont(new Font("Arial", Font.PLAIN, 16));
            //color del borde de la tabla
            tablaVehiculos.setGridColor(new Color(255, 255, 255));
            //tabla.setRowMargin(10);
            //poner bordes
            //tabla_equipos.setShowGrid(true);
            tablaVehiculos.setShowHorizontalLines(true);
            //tabla_equipos.setShowVerticalLines(true);
            //para que la tabla tenga un scroll
            jScrollPane1.setViewportView(tablaVehiculos);
            modelo_tabla.addColumn("Id Vehiculo");
            modelo_tabla.addColumn("No Inventario");
            modelo_tabla.addColumn("Marca");
            modelo_tabla.addColumn("Modelo");
            modelo_tabla.addColumn("Kilometraje");
            modelo_tabla.addColumn("No. Motor");
            modelo_tabla.addColumn("Placas");
            modelo_tabla.addColumn("No. Cilindros");
            modelo_tabla.addColumn("Tipo");
            modelo_tabla.addColumn("Tarjeta");
            modelo_tabla.addColumn("Responsable");
            modelo_tabla.addColumn("Descripcion");
            modelo_tabla.addColumn("Estado");

            tablaVehiculos.setAutoResizeMode(AUTO_RESIZE_OFF);
            tablaVehiculos.getColumnModel().getColumn(0).setPreferredWidth(100);//No Inventario
            tablaVehiculos.getColumnModel().getColumn(1).setPreferredWidth(200);//No Inventario
            tablaVehiculos.getColumnModel().getColumn(2).setPreferredWidth(200);//Marca
            tablaVehiculos.getColumnModel().getColumn(3).setPreferredWidth(200);//Modelo
            tablaVehiculos.getColumnModel().getColumn(4).setPreferredWidth(250);//Kilometraje
            tablaVehiculos.getColumnModel().getColumn(5).setPreferredWidth(200);//No. Motor
            tablaVehiculos.getColumnModel().getColumn(6).setPreferredWidth(150);//Placas
            tablaVehiculos.getColumnModel().getColumn(7).setPreferredWidth(200);//No. Cilindros
            tablaVehiculos.getColumnModel().getColumn(8).setPreferredWidth(200);//Tipo
            tablaVehiculos.getColumnModel().getColumn(9).setPreferredWidth(100);//Tarjeta
            tablaVehiculos.getColumnModel().getColumn(10).setPreferredWidth(250);//Responsable
            tablaVehiculos.getColumnModel().getColumn(11).setPreferredWidth(200);//Descripcion
            tablaVehiculos.getColumnModel().getColumn(12).setPreferredWidth(100);//Estado         

            tablaVehiculos.getColumnModel().getColumn(0).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(1).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(2).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(3).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(4).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(5).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(6).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(7).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(8).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(9).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(10).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(11).setResizable(false);
            tablaVehiculos.getColumnModel().getColumn(12).setResizable(false);

            //modelo_tabla.
            while (rs.next()) {
                Object[] fila = new Object[13];//por las columnas que tengo
                for (int i = 0; i < 13; i++) {
                    fila[i] = rs.getObject(i + 1);

                    //centra el contenido de la tabla
                    tablaVehiculos.getColumnModel().getColumn(i).setCellRenderer(tcr);
                    //cambiamos el color del emcabezado y centrado
                    TableColumn column = tablaVehiculos.getTableHeader().getColumnModel().getColumn(i);
                    column.setHeaderRenderer(cellRenderer);

                    cellRenderer.setFont(new Font("Arial", Font.BOLD, 11));
                    cellRenderer.setHorizontalAlignment(cellRenderer.CENTER);
                    cellRenderer.setForeground(Color.white);

                }

                tablaVehiculos.getTableHeader().setBackground(new Color(255, 0, 0));
                modelo_tabla.addRow(fila);
                ((DefaultTableCellRenderer) tablaVehiculos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

            }

            cn.close();
        } catch (Exception e) {
            System.out.println("Error al llenar tabla de equipos: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion de productos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        boton_buscar.setEnabled(true);

    }//GEN-LAST:event_boton_buscarActionPerformed

    private void boton_buscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscar2ActionPerformed

        menuVehiculos p = new menuVehiculos();
        p.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_boton_buscar2ActionPerformed

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
            java.util.logging.Logger.getLogger(asignacionVehiculosTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(asignacionVehiculosTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(asignacionVehiculosTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(asignacionVehiculosTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(bajasBienesMuebles_tabla.class.getName()).log(Level.SEVERE, null, ex);
                }
                new asignacionVehiculosTabla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barra_busqueda;
    private javax.swing.JButton boton_asignar;
    private javax.swing.JButton boton_buscar;
    private javax.swing.JButton boton_buscar2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaVehiculos;
    // End of variables declaration//GEN-END:variables
}
