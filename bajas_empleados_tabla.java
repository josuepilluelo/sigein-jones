/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javax.swing.table.DefaultTableModel;
import clases.Conection_BD;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static interfaces.consultasyReportesEmpleados_tabla.id_empleado;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
public class bajas_empleados_tabla extends javax.swing.JFrame {

    private int tipo_consulta = 0;
    private String titulo = "";
    public static int id_empleado = 0;
    
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
     * Creates new form bajas_empleados
     */
    public bajas_empleados_tabla() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoa.png")).getImage());
        //cerrar();
        tipo_consulta = 1;
        titulo = "Empleados Activos";
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        try {
            Connection cn1 = Conection_BD.conexion_bd();
            PreparedStatement pst1 = cn1.prepareStatement("select "
                    + "id_empleado, "
                    + "nombre_empleado, "
                    + "area, "
                    + "ubicacion, "
                    + "estatus, "
                    + "clave_servidor_publico, "
                    + "coordinacion, "
                    + "DATE_ADD(ultima_modificacion, interval 1 day) "
                    + "from empleados "
                    + "where estatus = 'Activo' "
                    + "and nombre_empleado not in ('almacen', 'transferencia')");
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

            modelo_tabla.addColumn("Id");
            modelo_tabla.addColumn("Nombre Empleado");
            modelo_tabla.addColumn("Area");
            modelo_tabla.addColumn("Ubicacion");
            modelo_tabla.addColumn("Estatus");
            modelo_tabla.addColumn("Clave S. P.");
            modelo_tabla.addColumn("Coordinacion");
            modelo_tabla.addColumn("Ultima Modificacion");
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);//id
            tabla.getColumnModel().getColumn(1).setPreferredWidth(330);//nombre
            tabla.getColumnModel().getColumn(2).setPreferredWidth(270);//area
            tabla.getColumnModel().getColumn(3).setPreferredWidth(300);//ubicacion
            tabla.getColumnModel().getColumn(4).setPreferredWidth(120);//estatus
            tabla.getColumnModel().getColumn(5).setPreferredWidth(250);//clave
            tabla.getColumnModel().getColumn(6).setPreferredWidth(250);//coordinacion
            tabla.getColumnModel().getColumn(7).setPreferredWidth(150);//ultima_modificacion

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
            tabla.getColumnModel().getColumn(7).setResizable(false);

            while (rs1.next()) {
                Object[] fila = new Object[8];//por las columnas que tengo
                for (int i = 0; i < 8; i++) {
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
        boton_reiniciar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        barra_busqueda = new javax.swing.JTextField();
        boton_buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        barra_tipos = new javax.swing.JComboBox<>();
        boton_buscar_tipos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        boton_reporte = new javax.swing.JButton();
        boton_buscar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bajas empleados");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        boton_reiniciar.setBackground(new java.awt.Color(0, 204, 255));
        boton_reiniciar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_reiniciar.setText("Reiniciar tabla");
        boton_reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reiniciarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Buscar empleado por categorias:");

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
        barra_tipos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activos ", "Inactivos" }));
        barra_tipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barra_tiposActionPerformed(evt);
            }
        });

        boton_buscar_tipos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar_tipos.setText("Buscar");
        boton_buscar_tipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscar_tiposActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Buscar por nombre del empleado:");

        boton_reporte.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_reporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reportepdf_30.png"))); // NOI18N
        boton_reporte.setText("Generar Reporte");
        boton_reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reporteActionPerformed(evt);
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
                .addComponent(boton_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(barra_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(boton_buscar)
                                .addGap(10, 10, 10)
                                .addComponent(boton_reiniciar))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(barra_tipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(boton_buscar_tipos))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(451, 451, 451)
                        .addComponent(boton_reporte)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(barra_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_buscar)
                            .addComponent(boton_reiniciar)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(barra_tipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boton_buscar_tipos)))))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton_reporte, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void boton_reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reiniciarActionPerformed
        // TODO add your handling code here:
        barra_busqueda.setText("");
        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        tipo_consulta = 1;
        titulo = "Empleados Activos";
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        try {
            Connection cn1 = Conection_BD.conexion_bd();
            PreparedStatement pst1 = cn1.prepareStatement("select "
                + "id_empleado, "
                + "nombre_empleado, "
                + "area, "
                + "ubicacion, "
                + "estatus, "
                + "clave_servidor_publico, "
                + "coordinacion, "
                + "DATE_ADD(ultima_modificacion, interval 1 day) "
                + "from empleados "
                + "where estatus = 'Activo'");
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

            modelo_tabla.addColumn("Id");
            modelo_tabla.addColumn("Nombre Empleado");
            modelo_tabla.addColumn("Area");
            modelo_tabla.addColumn("Ubicacion");
            modelo_tabla.addColumn("Estatus");
            modelo_tabla.addColumn("Clave S. P.");
            modelo_tabla.addColumn("Coordinacion");
            modelo_tabla.addColumn("Ultima Modificacion");
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);//id
            tabla.getColumnModel().getColumn(1).setPreferredWidth(330);//nombre
            tabla.getColumnModel().getColumn(2).setPreferredWidth(270);//area
            tabla.getColumnModel().getColumn(3).setPreferredWidth(300);//ubicacion
            tabla.getColumnModel().getColumn(4).setPreferredWidth(120);//estatus
            tabla.getColumnModel().getColumn(5).setPreferredWidth(250);//clave
            tabla.getColumnModel().getColumn(6).setPreferredWidth(250);//coordinacion
            tabla.getColumnModel().getColumn(7).setPreferredWidth(150);//ultima_modificacion

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
            tabla.getColumnModel().getColumn(7).setResizable(false);

            while (rs1.next()) {
                Object[] fila = new Object[8];//por las columnas que tengo
                for (int i = 0; i < 8; i++) {
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
    }//GEN-LAST:event_boton_reiniciarActionPerformed

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
                + "id_empleado, "
                + "nombre_empleado, "
                + "area, "
                + "ubicacion, "
                + "estatus, "
                + "clave_servidor_publico, "
                + "coordinacion, "
                + "DATE_ADD(ultima_modificacion, interval 1 day) "
                + "from empleados "
                + "where estatus = 'Activo' AND nombre_empleado like '%" + barra_busqueda.getText().trim() + "%'");
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

            modelo_tabla.addColumn("Id");
            modelo_tabla.addColumn("Nombre Empleado");
            modelo_tabla.addColumn("Area");
            modelo_tabla.addColumn("Ubicacion");
            modelo_tabla.addColumn("Estatus");
            modelo_tabla.addColumn("Clave S. P.");
            modelo_tabla.addColumn("Coordinacion");
            modelo_tabla.addColumn("Ultima Modificacion");
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);//id
            tabla.getColumnModel().getColumn(1).setPreferredWidth(330);//nombre
            tabla.getColumnModel().getColumn(2).setPreferredWidth(270);//area
            tabla.getColumnModel().getColumn(3).setPreferredWidth(300);//ubicacion
            tabla.getColumnModel().getColumn(4).setPreferredWidth(120);//estatus
            tabla.getColumnModel().getColumn(5).setPreferredWidth(250);//clave
            tabla.getColumnModel().getColumn(6).setPreferredWidth(250);//coordinacion
            tabla.getColumnModel().getColumn(7).setPreferredWidth(150);//ultima_modificacion

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
            tabla.getColumnModel().getColumn(7).setResizable(false);

            while (rs1.next()) {
                Object[] fila = new Object[8];//por las columnas que tengo
                for (int i = 0; i < 8; i++) {
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
        barra_busqueda.setText("");
        datosTabla();
    }//GEN-LAST:event_boton_buscarActionPerformed

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
            titulo = "Empleados Activos";
            consulta = ("select "
                + "id_empleado, "
                + "nombre_empleado, "
                + "area, "
                + "ubicacion, "
                + "estatus, "
                + "clave_servidor_publico, "
                + "coordinacion, "
                + "DATE_ADD(ultima_modificacion, interval 1 day) "
                + "from empleados "
                + "where estatus = 'Activo'");
        } else if (var_tipos == 1) {
            tipo_consulta = 2;
            titulo = "Empleados Inactivos";
            consulta = ("select "
                + "id_empleado, "
                + "nombre_empleado, "
                + "area, "
                + "ubicacion, "
                + "estatus, "
                + "clave_servidor_publico, "
                + "coordinacion, "
                + "DATE_ADD(ultima_modificacion, interval 1 day) "
                + "from empleados "
                + "where estatus = 'Inactivo'");
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

            modelo_tabla.addColumn("Id");
            modelo_tabla.addColumn("Nombre Empleado");
            modelo_tabla.addColumn("Area");
            modelo_tabla.addColumn("Ubicacion");
            modelo_tabla.addColumn("Estatus");
            modelo_tabla.addColumn("Clave S. P.");
            modelo_tabla.addColumn("Coordinacion");
            modelo_tabla.addColumn("Ultima Modificacion");
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);//id
            tabla.getColumnModel().getColumn(1).setPreferredWidth(330);//nombre
            tabla.getColumnModel().getColumn(2).setPreferredWidth(270);//area
            tabla.getColumnModel().getColumn(3).setPreferredWidth(300);//ubicacion
            tabla.getColumnModel().getColumn(4).setPreferredWidth(120);//estatus
            tabla.getColumnModel().getColumn(5).setPreferredWidth(250);//clave
            tabla.getColumnModel().getColumn(6).setPreferredWidth(250);//coordinacion
            tabla.getColumnModel().getColumn(7).setPreferredWidth(150);//ultima_modificacion

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
            tabla.getColumnModel().getColumn(7).setResizable(false);

            while (rs1.next()) {
                Object[] fila = new Object[8];//por las columnas que tengo
                for (int i = 0; i < 8; i++) {
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

    private void boton_reporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reporteActionPerformed
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser();
        int Opcion = jfc.showSaveDialog(this);

        String consulta = "";
        if (tipo_consulta == 1) {
            consulta = "select nombre_empleado, area, ubicacion, clave_servidor_publico, coordinacion, estatus from empleados where estatus = 'Activo'";
        } else if (tipo_consulta == 2) {
            consulta = "select nombre_empleado, area, ubicacion, clave_servidor_publico, coordinacion, estatus from empleados where estatus = 'Inactivo'";
        } else {
            JOptionPane.showMessageDialog(null, "Error en consulta de informacion", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (Opcion == JFileChooser.APPROVE_OPTION) {
            File f = jfc.getSelectedFile();
            System.out.println(f);

            float[] margenCeldas = new float[]{20f, 10f, 10f, 10f, 10f, 5f};//este arreglo sirve para las medidas que tengran cada una de las columnas si son 10 columnas son 1o numero
            Document documento = new Document(PageSize.LETTER.rotate()); //el parametro es el tamaño de la hoja del documento LETTER es tamaño carta. rotate esta en horizontal
            //Documento documento = nuevo documento (PageSize.A4);(PageSize.LETTER.rotate())
            documento.setMargins(10, 10, 10, 10);//margenes del documento

            try {
                //String ruta = System.getProperty("user.home");//ruta del usuario
                PdfWriter.getInstance(documento, new FileOutputStream(f + ".pdf"));
                //insertar la imagen en el documento
                //cabecera
                com.itextpdf.text.Image imagen_cabecera = com.itextpdf.text.Image.getInstance("src/imagenes/baner_arriba_horizontal.jpg");//las imagenes se guardan en src
                //dimensiones imagens
                imagen_cabecera.scaleToFit(700, 100);
                imagen_cabecera.setAlignment(Chunk.ALIGN_CENTER);

                com.itextpdf.text.Image imagen_abajo = com.itextpdf.text.Image.getInstance("src/imagenes/baner_abjo_horizontal.jpg");//las imagenes se guardan en src
                //dimensiones imagens
                imagen_abajo.scaleToFit(750, 100);

                imagen_abajo.setAbsolutePosition(25, 15);
                imagen_abajo.setAlignment(Chunk.ALIGN_CENTER);
                //formato de texto
                Paragraph parrafo = new Paragraph();
                parrafo.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo.setFont(FontFactory.getFont("Arial", 24, Font.BOLD, BaseColor.DARK_GRAY));
                parrafo.add(titulo+"\n\n");
                //abre el documento
                documento.open();
                //agregamos imagen y parrfos
                documento.add(imagen_cabecera);
                documento.add(parrafo);
                //numero de columnas
                PdfPTable tabla = new PdfPTable(6);
                tabla.setWidthPercentage(95);//el porcentaje total de ancho del documento tomara
                tabla.setWidths(margenCeldas);//aqui va el arreglo
                PdfPCell cell = new PdfPCell(new Phrase("Empleado"));//aqui podrimos modificar las celdas
                cell.setVerticalAlignment(Element.ALIGN_CENTER);//alineados horizontales y verticales
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(183, 183, 183));//color de fondo
                cell.setFixedHeight(20);//alto de la celda que se le aplica a todas de la fila
                tabla.addCell(cell);
                PdfPCell cell_1 = new PdfPCell(new Phrase("Area"));
                cell_1.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_1.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_1);
                PdfPCell cell_2 = new PdfPCell(new Phrase("Ubicacion"));
                cell_2.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_2.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_2);
                PdfPCell cell_3 = new PdfPCell(new Phrase("Clave Servidor Publico"));
                cell_3.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_3.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_3);
                PdfPCell cell_4 = new PdfPCell(new Phrase("Coordinacion"));
                cell_4.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_4.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_4.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_4);
                PdfPCell cell_5 = new PdfPCell(new Phrase("Estatus"));
                cell_5.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_5.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_5.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_5);

                //            tabla.addCell("Id");
                //            tabla.addCell("Nombre");
                //            tabla.addCell("Precio");
                //            tabla.addCell("Descripcion");
                //            tabla.addCell("Comentarios");
                //            tabla.addCell("Ubi");
                //"ruta BD","USUARIO","CONTRASEÑA"
                //Connection coneccion = DriverManager.getConnection("jdbc:mysql://localhost/nueva_bd","root","josue");
                Connection cn = Conection_BD.conexion_bd();
                PreparedStatement pst = cn.prepareStatement(consulta);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    do {                        //columna
                        PdfPCell colu_1 = new PdfPCell(new Phrase(rs.getString(1)));
                        colu_1.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_1);
                        PdfPCell colu_2 = new PdfPCell(new Phrase(rs.getString(2)));
                        colu_2.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_2);
                        PdfPCell colu_3 = new PdfPCell(new Phrase(rs.getString(3)));
                        colu_3.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_3.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_3);
                        PdfPCell colu_4 = new PdfPCell(new Phrase(rs.getString(4)));
                        colu_4.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_4.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_4);
                        PdfPCell colu_5 = new PdfPCell(new Phrase(rs.getString(5)));
                        colu_5.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_5.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_5);
                        PdfPCell colu_6 = new PdfPCell(new Phrase(rs.getString(6)));
                        colu_6.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_6.setHorizontalAlignment(Element.ALIGN_CENTER);
                        colu_6.setFixedHeight(35);
                        tabla.addCell(colu_6);
                    } while (rs.next());
                    tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
                    documento.add(tabla);
                }
                //documento.
                documento.add(imagen_abajo);
                documento.close();
                Icon icono_correcto = new ImageIcon(getClass().getResource("/imagenes/correcto.png"));
                JOptionPane.showMessageDialog(null, "Reporte Creado", "Reporte", JOptionPane.INFORMATION_MESSAGE, icono_correcto);
            } catch (Exception e) {
                System.out.println("Error: " + e);
                JOptionPane.showMessageDialog(null, "Error al generar PDF", "Error", JOptionPane.ERROR_MESSAGE);
            }
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            datosTabla();
        }
    }//GEN-LAST:event_boton_reporteActionPerformed

    private void boton_buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscar1ActionPerformed
        menuEmpleados p = new menuEmpleados();
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
            java.util.logging.Logger.getLogger(bajas_empleados_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bajas_empleados_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bajas_empleados_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bajas_empleados_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bajas_empleados_tabla().setVisible(true);
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
                    id_empleado = (int) modelo_tabla.getValueAt(num_fila, num_columna);
                    System.out.println(modelo_tabla.getValueAt(num_fila, num_columna));
                    //nombre = (int) modelo_tabla.getValueAt(num_fila, num_columna);
                    bajas_empleados_datos inf = new bajas_empleados_datos();
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
    private javax.swing.JButton boton_reporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
