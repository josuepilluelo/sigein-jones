/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conection_BD;
import static interfaces.detalles_bienesMuebles_tabla.id_bien;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
 * @author jo199
 */
public class detalles_vehiculos_tabla extends javax.swing.JFrame {

    private int tipo_consulta = 0;
    public static int id_vehiculoo = 0;
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

    public detalles_vehiculos_tabla() {
        initComponents();
        //cerrar();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoa.png")).getImage());
        tipo_consulta = 1;
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
                    + "b.estatus, "
                    + "DATE_ADD(b.ultima_modificacion, interval 1 day) "
                    + "from vehiculos b JOIN empleados e "
                    + "ON (b.id_empleado = e.id_empleado) "
                    + "WHERE b.estatus = 'ACTIVO' "
                    + "ORDER BY b.no_inventario");
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
            modelo_tabla.addColumn("Id. Vehiculo");
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
            modelo_tabla.addColumn("Ultima Modificacion");

            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);//id
            tabla.getColumnModel().getColumn(1).setPreferredWidth(200);//No Inventario
            tabla.getColumnModel().getColumn(2).setPreferredWidth(200);//Marca
            tabla.getColumnModel().getColumn(3).setPreferredWidth(200);//Modelo
            tabla.getColumnModel().getColumn(4).setPreferredWidth(250);//Kilometraje
            tabla.getColumnModel().getColumn(5).setPreferredWidth(200);//No. Motor
            tabla.getColumnModel().getColumn(6).setPreferredWidth(150);//Placas
            tabla.getColumnModel().getColumn(7).setPreferredWidth(200);//No. Cilindros
            tabla.getColumnModel().getColumn(8).setPreferredWidth(200);//Tipo
            tabla.getColumnModel().getColumn(9).setPreferredWidth(100);//Tarjeta
            tabla.getColumnModel().getColumn(10).setPreferredWidth(250);//Responsable
            tabla.getColumnModel().getColumn(11).setPreferredWidth(200);//Descripcion
            tabla.getColumnModel().getColumn(12).setPreferredWidth(100);//Estado
            tabla.getColumnModel().getColumn(13).setPreferredWidth(150);//Ultima Modificacion            

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
            tabla.getColumnModel().getColumn(7).setResizable(false);
            tabla.getColumnModel().getColumn(8).setResizable(false);
            tabla.getColumnModel().getColumn(9).setResizable(false);
            tabla.getColumnModel().getColumn(10).setResizable(false);
            tabla.getColumnModel().getColumn(11).setResizable(false);
            tabla.getColumnModel().getColumn(12).setResizable(false);
            tabla.getColumnModel().getColumn(13).setResizable(false);

            //modelo_tabla.
            while (rs.next()) {
                Object[] fila = new Object[14];//por las columnas que tengo
                for (int i = 0; i < 14; i++) {
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
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion de productos", "Error", JOptionPane.ERROR_MESSAGE);
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
        barra_busqueda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        boton_reiniciar = new javax.swing.JButton();
        boton_buscar_tipos = new javax.swing.JButton();
        barra_tipos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        boton_buscar = new javax.swing.JButton();
        boton_buscar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Detalles Vehiculos");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        barra_busqueda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_busqueda.setToolTipText("ejemplo silla, escritorio");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Buscar por  marca:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Buscar por articulo por categorias:");

        boton_reiniciar.setBackground(new java.awt.Color(0, 204, 255));
        boton_reiniciar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_reiniciar.setText("Reiniciar tabla");
        boton_reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reiniciarActionPerformed(evt);
            }
        });

        boton_buscar_tipos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar_tipos.setText("Buscar");
        boton_buscar_tipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscar_tiposActionPerformed(evt);
            }
        });

        barra_tipos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_tipos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activos ", "No Activos" }));
        barra_tipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barra_tiposActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        boton_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar.setText("Buscar");
        boton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarActionPerformed(evt);
            }
        });

        boton_buscar1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar1.setText("Regresar");
        boton_buscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(barra_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(boton_buscar)
                                .addGap(10, 10, 10)
                                .addComponent(boton_reiniciar)))
                        .addGap(143, 143, 143)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(barra_tipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(boton_buscar_tipos))
                            .addComponent(jLabel2))))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boton_buscar)
                            .addComponent(boton_reiniciar)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(barra_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(boton_buscar_tipos)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(barra_tipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
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

    private void barra_tiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barra_tiposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barra_tiposActionPerformed

    private void boton_buscar_tiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscar_tiposActionPerformed
        // TODO add your handling code here:
        barra_busqueda.setText("");
        int var_tipos = barra_tipos.getSelectedIndex();

        String consulta = "";
        if (var_tipos == 0) {
            tipo_consulta = 1;
            consulta = ("select "
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
                    + "b.estatus, "
                    + "DATE_ADD(b.ultima_modificacion, interval 1 day) "
                    + "from vehiculos b JOIN empleados e "
                    + "ON (b.id_empleado = e.id_empleado) "
                    + "WHERE b.estatus = 'ACTIVO' "
                    + "ORDER BY b.no_inventario");
        } else if (var_tipos == 1) {
            tipo_consulta = 2;
            consulta = ("select "
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
                    + "b.estatus, "
                    + "DATE_ADD(b.ultima_modificacion, interval 1 day) "
                    + "from vehiculos b JOIN empleados e "
                    + "ON (b.id_empleado = e.id_empleado) "
                    + "WHERE b.estatus = 'NO ACTIVO' "
                    + "ORDER BY b.no_inventario");
        }
        // TODO add your handling code here:
        // TODO add your handling code here:
        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        try {
            Connection cn1 = Conection_BD.conexion_bd();
            PreparedStatement pst1 = cn1.prepareStatement(consulta.toString());
            ResultSet rs1 = pst1.executeQuery();
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

            modelo_tabla.addColumn("Id. Vehiculo");
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
            modelo_tabla.addColumn("Ultima Modificacion");

            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);//id
            tabla.getColumnModel().getColumn(1).setPreferredWidth(200);//No Inventario
            tabla.getColumnModel().getColumn(2).setPreferredWidth(200);//Marca
            tabla.getColumnModel().getColumn(3).setPreferredWidth(200);//Modelo
            tabla.getColumnModel().getColumn(4).setPreferredWidth(250);//Kilometraje
            tabla.getColumnModel().getColumn(5).setPreferredWidth(200);//No. Motor
            tabla.getColumnModel().getColumn(6).setPreferredWidth(150);//Placas
            tabla.getColumnModel().getColumn(7).setPreferredWidth(200);//No. Cilindros
            tabla.getColumnModel().getColumn(8).setPreferredWidth(200);//Tipo
            tabla.getColumnModel().getColumn(9).setPreferredWidth(100);//Tarjeta
            tabla.getColumnModel().getColumn(10).setPreferredWidth(250);//Responsable
            tabla.getColumnModel().getColumn(11).setPreferredWidth(200);//Descripcion
            tabla.getColumnModel().getColumn(12).setPreferredWidth(100);//Estado
            tabla.getColumnModel().getColumn(13).setPreferredWidth(150);//Ultima Modificacion            

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
            tabla.getColumnModel().getColumn(7).setResizable(false);
            tabla.getColumnModel().getColumn(8).setResizable(false);
            tabla.getColumnModel().getColumn(9).setResizable(false);
            tabla.getColumnModel().getColumn(10).setResizable(false);
            tabla.getColumnModel().getColumn(11).setResizable(false);
            tabla.getColumnModel().getColumn(12).setResizable(false);
            tabla.getColumnModel().getColumn(13).setResizable(false);


            //modelo_tabla.
            while (rs1.next()) {
                Object[] fila = new Object[14];//por las columnas que tengo
                for (int i = 0; i < 14; i++) {
                    fila[i] = rs1.getObject(i + 1);

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

            cn1.close();
        } catch (Exception e) {
            System.out.println("Error al llenar tabla de equipos: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion de productos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        datosTabla();
        barra_busqueda.setText("");
    }//GEN-LAST:event_boton_buscar_tiposActionPerformed

    private void boton_reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reiniciarActionPerformed
        // TODO add your handling code here:
        barra_busqueda.setText("");
        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        tipo_consulta = 1;
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
                    + "b.estatus, "
                    + "DATE_ADD(b.ultima_modificacion, interval 1 day) "
                    + "from vehiculos b JOIN empleados e "
                    + "ON (b.id_empleado = e.id_empleado) "
                    + "WHERE b.estatus = 'ACTIVO' "
                    + "ORDER BY b.no_inventario");
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

            modelo_tabla.addColumn("Id. Vehiculo");
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
            modelo_tabla.addColumn("Ultima Modificacion");

            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);//id
            tabla.getColumnModel().getColumn(1).setPreferredWidth(200);//No Inventario
            tabla.getColumnModel().getColumn(2).setPreferredWidth(200);//Marca
            tabla.getColumnModel().getColumn(3).setPreferredWidth(200);//Modelo
            tabla.getColumnModel().getColumn(4).setPreferredWidth(250);//Kilometraje
            tabla.getColumnModel().getColumn(5).setPreferredWidth(200);//No. Motor
            tabla.getColumnModel().getColumn(6).setPreferredWidth(150);//Placas
            tabla.getColumnModel().getColumn(7).setPreferredWidth(200);//No. Cilindros
            tabla.getColumnModel().getColumn(8).setPreferredWidth(200);//Tipo
            tabla.getColumnModel().getColumn(9).setPreferredWidth(100);//Tarjeta
            tabla.getColumnModel().getColumn(10).setPreferredWidth(250);//Responsable
            tabla.getColumnModel().getColumn(11).setPreferredWidth(200);//Descripcion
            tabla.getColumnModel().getColumn(12).setPreferredWidth(100);//Estado
            tabla.getColumnModel().getColumn(13).setPreferredWidth(150);//Ultima Modificacion            

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
            tabla.getColumnModel().getColumn(7).setResizable(false);
            tabla.getColumnModel().getColumn(8).setResizable(false);
            tabla.getColumnModel().getColumn(9).setResizable(false);
            tabla.getColumnModel().getColumn(10).setResizable(false);
            tabla.getColumnModel().getColumn(11).setResizable(false);
            tabla.getColumnModel().getColumn(12).setResizable(false);
            tabla.getColumnModel().getColumn(13).setResizable(false);


            //modelo_tabla.
            while (rs.next()) {
                Object[] fila = new Object[14];//por las columnas que tengo
                for (int i = 0; i < 14; i++) {
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
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion de productos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        datosTabla();
    }//GEN-LAST:event_boton_reiniciarActionPerformed

    private void boton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:

        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        try {
            /*
            PreparedStatement pst = cn.prepareStatement("select "
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
                + "b.estatus, "
                + "b.ultima_modificacion "
                + "from vehiculos b JOIN empleados e "
                + "ON (b.id_empleado = e.id_empleado) "
                + "WHERE b.marca like '%" + barra_busqueda.getText().trim().toUpperCase() + "%'b.estatus = 'ACTIVO' "
                + "ORDER BY b.no_inventario");
             */
            Connection cn1 = Conection_BD.conexion_bd();
            PreparedStatement pst = cn1.prepareStatement("select "
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
                    + "b.estatus, "
                    + "DATE_ADD(b.ultima_modificacion, interval 1 day) "
                    + "from vehiculos b JOIN empleados e "
                    + "ON (b.id_empleado = e.id_empleado) "
                    + "WHERE b.marca like '%" + barra_busqueda.getText().trim().toUpperCase() + "%' and b.estatus = 'ACTIVO' "
                    + "ORDER BY b.no_inventario");
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

            modelo_tabla.addColumn("Id. Vehiculo");
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
            modelo_tabla.addColumn("Ultima Modificacion");

            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);//id
            tabla.getColumnModel().getColumn(1).setPreferredWidth(200);//No Inventario
            tabla.getColumnModel().getColumn(2).setPreferredWidth(200);//Marca
            tabla.getColumnModel().getColumn(3).setPreferredWidth(200);//Modelo
            tabla.getColumnModel().getColumn(4).setPreferredWidth(250);//Kilometraje
            tabla.getColumnModel().getColumn(5).setPreferredWidth(200);//No. Motor
            tabla.getColumnModel().getColumn(6).setPreferredWidth(150);//Placas
            tabla.getColumnModel().getColumn(7).setPreferredWidth(200);//No. Cilindros
            tabla.getColumnModel().getColumn(8).setPreferredWidth(200);//Tipo
            tabla.getColumnModel().getColumn(9).setPreferredWidth(100);//Tarjeta
            tabla.getColumnModel().getColumn(10).setPreferredWidth(250);//Responsable
            tabla.getColumnModel().getColumn(11).setPreferredWidth(200);//Descripcion
            tabla.getColumnModel().getColumn(12).setPreferredWidth(100);//Estado
            tabla.getColumnModel().getColumn(13).setPreferredWidth(150);//Ultima Modificacion            

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
            tabla.getColumnModel().getColumn(7).setResizable(false);
            tabla.getColumnModel().getColumn(8).setResizable(false);
            tabla.getColumnModel().getColumn(9).setResizable(false);
            tabla.getColumnModel().getColumn(10).setResizable(false);
            tabla.getColumnModel().getColumn(11).setResizable(false);
            tabla.getColumnModel().getColumn(12).setResizable(false);
            tabla.getColumnModel().getColumn(13).setResizable(false);


            //modelo_tabla.
            while (rs.next()) {
                Object[] fila = new Object[14];//por las columnas que tengo
                for (int i = 0; i < 14; i++) {
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

            cn1.close();
        } catch (Exception e) {
            System.out.println("Error al llenar tabla de equipos: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion de productos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        datosTabla();
        barra_busqueda.setText("");
    }//GEN-LAST:event_boton_buscarActionPerformed

    private void boton_buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscar1ActionPerformed

        menuVehiculos p = new menuVehiculos();
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
            java.util.logging.Logger.getLogger(detalles_vehiculos_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(detalles_vehiculos_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(detalles_vehiculos_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(detalles_vehiculos_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(detalles_vehiculos_tabla.class.getName()).log(Level.SEVERE, null, ex);
                }
                new detalles_vehiculos_tabla().setVisible(true);
            }
        });
    }

    public void cerrar() {
        try {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    confirmarSalida();
                }
            });
            //this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void confirmarSalida() {
        int valor = JOptionPane.showConfirmDialog(this, "¿Seguro de cerrar esta ventana?", "ADVERTENCIA", JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
            menuVehiculos p = new menuVehiculos();
            p.setVisible(true);
            this.dispose();
        }
    }

    public void datosTabla() {
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int num_fila = tabla.rowAtPoint(evt.getPoint());
                //aqui se saca el nombre el id porque es columna 0
                int num_columna = 0;
                if (num_fila > -1) {
                    //nombre = (String) modelo_tabla.getValueAt(num_fila, num_columna);
                    id_vehiculoo = (int) modelo_tabla.getValueAt(num_fila, num_columna);
                    System.out.println(modelo_tabla.getValueAt(num_fila, num_columna));
                    //nombre = (int) modelo_tabla.getValueAt(num_fila, num_columna);
                    detalles_vehiculos_datos inf = new detalles_vehiculos_datos();
                    inf.setVisible(true);
                }
                dispose();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barra_busqueda;
    private javax.swing.JComboBox<String> barra_tipos;
    private javax.swing.JButton boton_buscar;
    private javax.swing.JButton boton_buscar1;
    private javax.swing.JButton boton_buscar_tipos;
    private javax.swing.JButton boton_reiniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
