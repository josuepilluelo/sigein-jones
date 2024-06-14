/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import clases.Conection_BD;
import static interfaces.bajasBienesMuebles_tabla.id_bien;
import static interfaces.modificarInformacionBienesMuebles_tabla.id_bien;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
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
import static javax.swing.JTable.*;
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
public class borrarBienesMuebles_tabla extends javax.swing.JFrame {
    public static int id_bien = 0;

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
     * Creates new form borrarBienesMuebles_tabla
     */
    public borrarBienesMuebles_tabla() {
        initComponents();
                setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoa.png")).getImage());
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));

        try {
            Connection cn = Conection_BD.conexion_bd();
            PreparedStatement pst = cn.prepareStatement("select "
                    + "b.id_bien, "
                    + "b.no_inventario, "
                    + "b.descripcion_bien, "
                    + "b.marca, "
                    + "b.estado_actual, "
                    + "concat('$  ',valor_estimado), "
                    + "b.estatus, "
                    + "b.tipo_sico_sareco, "
                    + "b.tipo_info_mueble, "
                    + "e.nombre_empleado, "
                    + "DATE_ADD(b.ultima_modificacion, interval 1 day) "
                    + "from bienes b JOIN empleados e "
                    + "ON (b.id_empleado = e.id_empleado) "
                    + "WHERE b.estatus = 'ACTIVO' AND tipo_info_mueble = 'MUEBLE' "
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
            modelo_tabla.addColumn("ID");
            modelo_tabla.addColumn("No Inventario");
            modelo_tabla.addColumn("Descripcion");
            modelo_tabla.addColumn("Marca");
            modelo_tabla.addColumn("Estado Actual");
            modelo_tabla.addColumn("Valor");
            modelo_tabla.addColumn("Estatus");
            modelo_tabla.addColumn("Tipo");
            modelo_tabla.addColumn("Tipo");
            modelo_tabla.addColumn("Resguardatario");
            modelo_tabla.addColumn("Ultima modificacion");
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(150);

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

            //modelo_tabla.
            while (rs.next()) {
                Object[] fila = new Object[11];//por las columnas que tengo
                for (int i = 0; i < 11; i++) {
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
        boton_buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        barra_tipos = new javax.swing.JComboBox<>();
        boton_buscar_tipos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        boton_reiniciar = new javax.swing.JButton();
        boton_buscar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Borrar Bienes Muebles");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        barra_busqueda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_busqueda.setToolTipText("ejemplo silla, escritorio");

        boton_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar.setText("Buscar");
        boton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarActionPerformed(evt);
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

        barra_tipos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_tipos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SICOPA", "SARECO" }));

        boton_buscar_tipos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar_tipos.setText("Buscar");
        boton_buscar_tipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscar_tiposActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Buscar por descripcion:");

        boton_reiniciar.setBackground(new java.awt.Color(0, 204, 255));
        boton_reiniciar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_reiniciar.setText("Reiniciar tabla");
        boton_reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reiniciarActionPerformed(evt);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(barra_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boton_buscar)
                        .addGap(10, 10, 10)
                        .addComponent(boton_reiniciar)
                        .addGap(419, 419, 419)
                        .addComponent(barra_tipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boton_buscar_tipos))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boton_buscar)
                    .addComponent(boton_reiniciar)
                    .addComponent(boton_buscar_tipos)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(barra_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(barra_tipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        // TODO add your handling code here:
        // TODO add your handling code here:

        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        try {
            Connection cn1 = Conection_BD.conexion_bd();
            PreparedStatement pst1 = cn1.prepareStatement("select "
                + "b.id_bien, "
                + "b.no_inventario, "
                + "b.descripcion_bien, "
                + "b.marca, "
                + "b.estado_actual, "
                + "concat('$  ',valor_estimado), "
                + "b.estatus, "
                + "b.tipo_sico_sareco, "
                + "b.tipo_info_mueble, "
                + "e.nombre_empleado, "
                + "DATE_ADD(b.ultima_modificacion, interval 1 day) "
                + "from bienes b JOIN empleados e "
                + "ON (b.id_empleado = e.id_empleado) "
                + "where descripcion_bien like '%" + barra_busqueda.getText().trim() + "%' AND b.estatus = 'ACTIVO' AND tipo_info_mueble = 'MUEBLE' "
                + "ORDER BY b.no_inventario");
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

            modelo_tabla.addColumn("ID");
            modelo_tabla.addColumn("No Inventario");
            modelo_tabla.addColumn("Descripcion");
            modelo_tabla.addColumn("Marca");
            modelo_tabla.addColumn("Estado Actual");
            modelo_tabla.addColumn("Valor");
            modelo_tabla.addColumn("Estatus");
            modelo_tabla.addColumn("Tipo");
            modelo_tabla.addColumn("Tipo");
            modelo_tabla.addColumn("Resguardatario");
            modelo_tabla.addColumn("Ultima modificacion");
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(150);

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

            while (rs1.next()) {
                Object[] fila = new Object[11];//por las columnas que tengo
                for (int i = 0; i < 11; i++) {
                    fila[i] = rs1.getObject(i + 1);

                    //centra el contenido de la tabla
                    tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
                    //cambiamos el color del emcabezado y centrado
                    TableColumn column = tabla.getTableHeader().getColumnModel().getColumn(i);
                    column.setHeaderRenderer(cellRenderer);
                    //formato encabezado
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
            System.out.println("Error al llenar tabla: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        barra_busqueda.setText("");
        datosTabla();
    }//GEN-LAST:event_boton_buscarActionPerformed

    private void boton_buscar_tiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscar_tiposActionPerformed
        // TODO add your handling code here:
        barra_busqueda.setText("");
        int var_tipos = barra_tipos.getSelectedIndex();
        String consulta = "";
        if (var_tipos == 0) {
            consulta = ("select "
                + "b.id_bien, "
                + "b.no_inventario, "
                + "b.descripcion_bien, "
                + "b.marca, "
                + "b.estado_actual, "
                + "concat('$  ',valor_estimado), "
                + "b.estatus, "
                + "b.tipo_sico_sareco, "
                + "b.tipo_info_mueble, "
                + "e.nombre_empleado, "
                + "DATE_ADD(b.ultima_modificacion, interval 1 day) "
                + "from bienes b JOIN empleados e "
                + "ON (b.id_empleado = e.id_empleado) "
                + "WHERE b.tipo_sico_sareco = 'SICOPA' AND b.estatus = 'ACTIVO' AND tipo_info_mueble = 'MUEBLE'");
        } else if (var_tipos == 1) {
            consulta = ("select "
                + "b.id_bien, "
                + "b.no_inventario, "
                + "b.descripcion_bien, "
                + "b.marca, "
                + "b.estado_actual, "
                + "concat('$  ',valor_estimado), "
                + "b.estatus, "
                + "b.tipo_sico_sareco, "
                + "b.tipo_info_mueble, "
                + "e.nombre_empleado, "
                + "DATE_ADD(b.ultima_modificacion, interval 1 day) "
                + "from bienes b JOIN empleados e "
                + "ON (b.id_empleado = e.id_empleado) "
                + "WHERE b.tipo_sico_sareco = 'SARECO' AND b.estatus = 'ACTIVO' AND tipo_info_mueble = 'MUEBLE'");
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

            modelo_tabla.addColumn("ID");
            modelo_tabla.addColumn("No Inventario");
            modelo_tabla.addColumn("Descripcion");
            modelo_tabla.addColumn("Marca");
            modelo_tabla.addColumn("Estado Actual");
            modelo_tabla.addColumn("Valor");
            modelo_tabla.addColumn("Estatus");
            modelo_tabla.addColumn("Tipo");
            modelo_tabla.addColumn("Tipo");
            modelo_tabla.addColumn("Resguardatario");
            modelo_tabla.addColumn("Ultima modificacion");
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(150);

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

            while (rs1.next()) {
                Object[] fila = new Object[11];//por las columnas que tengo
                for (int i = 0; i < 11; i++) {
                    fila[i] = rs1.getObject(i + 1);

                    //centra el contenido de la tabla
                    tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
                    //cambiamos el color del emcabezado y centrado
                    TableColumn column = tabla.getTableHeader().getColumnModel().getColumn(i);
                    column.setHeaderRenderer(cellRenderer);
                    //formato encabezado
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
    }//GEN-LAST:event_boton_buscar_tiposActionPerformed

    private void boton_reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reiniciarActionPerformed
        // TODO add your handling code here:
        barra_busqueda.setText("");
        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));

        try {
            Connection cn = Conection_BD.conexion_bd();
            PreparedStatement pst = cn.prepareStatement("select "
                + "b.id_bien, "
                + "b.no_inventario, "
                + "b.descripcion_bien, "
                + "b.marca, "
                + "b.estado_actual, "
                + "concat('$  ',valor_estimado), "
                + "b.estatus, "
                + "b.tipo_sico_sareco, "
                + "b.tipo_info_mueble, "
                + "e.nombre_empleado, "
                + "DATE_ADD(b.ultima_modificacion, interval 1 day) "
                + "from bienes b JOIN empleados e "
                + "ON (b.id_empleado = e.id_empleado) "
                + "WHERE b.estatus = 'ACTIVO' AND tipo_info_mueble = 'MUEBLE' "
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
            modelo_tabla.addColumn("ID");
            modelo_tabla.addColumn("No Inventario");
            modelo_tabla.addColumn("Descripcion");
            modelo_tabla.addColumn("Marca");
            modelo_tabla.addColumn("Estado Actual");
            modelo_tabla.addColumn("Valor");
            modelo_tabla.addColumn("Estatus");
            modelo_tabla.addColumn("Tipo");
            modelo_tabla.addColumn("Tipo");
            modelo_tabla.addColumn("Resguardatario");
            modelo_tabla.addColumn("Ultima modificacion");
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(150);

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

            //modelo_tabla.
            while (rs.next()) {
                Object[] fila = new Object[11];//por las columnas que tengo
                for (int i = 0; i < 11; i++) {
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

    private void boton_buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscar1ActionPerformed

        menuBienesMuebles p = new menuBienesMuebles();
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
            java.util.logging.Logger.getLogger(borrarBienesMuebles_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(borrarBienesMuebles_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(borrarBienesMuebles_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(borrarBienesMuebles_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new borrarBienesMuebles_tabla().setVisible(true);
            }
        });
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
                    id_bien = (int) modelo_tabla.getValueAt(num_fila, num_columna);
                    System.out.println(modelo_tabla.getValueAt(num_fila, num_columna));
                    //nombre = (int) modelo_tabla.getValueAt(num_fila, num_columna);
                    borrarBienesMuebles_datos inf = new borrarBienesMuebles_datos();
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
