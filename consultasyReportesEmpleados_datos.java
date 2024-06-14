/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

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
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
public class consultasyReportesEmpleados_datos extends javax.swing.JFrame {

    public int id_emp = 0, id = 0, variable_vehiculo = 0, variable_bienes = 0, variable_bienesper = 0;
    DefaultTableModel modelo_tabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            //ya no seran editadas
            if (columnas > 0 && filas > 0) {
                return true;
            }
            return true;

        }

    };
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
    DefaultTableModel modelo_tabla2 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            //ya no seran editadas
            if (columnas > 0 && filas > 0) {
                return true;
            }
            return true;

        }

    };

    /**
     * Creates new form consultasyReportesEmpleados_datos
     */
    public consultasyReportesEmpleados_datos() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoa.png")).getImage());
        //cerrar();
        id_emp = consultasyReportesEmpleados_tabla.id_empleado;
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        try {
            Connection cn = Conection_BD.conexion_bd();
            PreparedStatement pst = cn.prepareStatement("select "
                    + "id_empleado, "
                    + "nombre_empleado, "
                    + "area, "
                    + "ubicacion, "
                    + "clave_servidor_publico, "
                    + "coordinacion "
                    + "from empleados "
                    + "where id_empleado ='" + id_emp + "' and estatus = 'Activo'");

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_empleado");
                etiqueta_nombre_empleado.setText(rs.getString("nombre_empleado"));
                etiqueta_area.setText(rs.getString("area"));
                etiqueta_ubicacion.setText(rs.getString("ubicacion"));
                etiqueta_clave_s_p.setText(rs.getString("clave_servidor_publico"));
                etiqueta_coordinacion.setText(rs.getString("coordinacion"));

                //fecha.setText(rs.getString("ultima_modificacion"));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al cargar usuario: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al cargar informacion de empleado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        ///////////////////////////////////////////////////////////////////////bienes //////////////////////////////////////////////////////////////////////////////////////
        try {
            Connection cn = Conection_BD.conexion_bd();
            PreparedStatement pst = cn.prepareStatement("select "
                    + "ROW_NUMBER() OVER( ORDER BY no_inventario) as NP, "
                    + "b.descripcion_bien, "
                    + "b.marca, "
                    + "b.no_inventario, "
                    + "b.tipo_sico_sareco, "
                    + "e.ubicacion "
                    + "from bienes b JOIN empleados e "
                    + "ON (b.id_empleado = e.id_empleado) "
                    + "where e.id_empleado = '" + id_emp + "' and (b.estatus ='ACTIVO' OR b.estatus ='FUNCIONA')"
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
            modelo_tabla.addColumn("NP");
            modelo_tabla.addColumn("Articulo");
            modelo_tabla.addColumn("Marca");
            modelo_tabla.addColumn("Inventario");
            modelo_tabla.addColumn("Serie");
            modelo_tabla.addColumn("OBS");

            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);//np
            tabla.getColumnModel().getColumn(1).setPreferredWidth(350);//descripcion_bien
            tabla.getColumnModel().getColumn(2).setPreferredWidth(200);//marca
            tabla.getColumnModel().getColumn(3).setPreferredWidth(200);//no_inventario
            tabla.getColumnModel().getColumn(4).setPreferredWidth(100);//tipo_sico_sareco
            tabla.getColumnModel().getColumn(5).setPreferredWidth(200);//ubicacion     

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);

            //modelo_tabla.
            while (rs.next()) {
                variable_bienes = 1;
                Object[] fila = new Object[6];//por las columnas que tengo
                for (int i = 0; i < 6; i++) {
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
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion de bienes", "Error", JOptionPane.ERROR_MESSAGE);
        }
        ///////////////////////////////////////////////////////////////////////b i e n e s    p e r s o n a l e s//////////////////////////////////////////////////////////////////////////////////////
        try {
            Connection cn1 = Conection_BD.conexion_bd();
            PreparedStatement pst1 = cn1.prepareStatement("select "
                    + "ROW_NUMBER() OVER( ORDER BY id_bien_personal) as NP, "
                    + "b.bien_personal "
                    + "from bienes_personales b JOIN empleados e "
                    + "ON (b.id_empleado = e.id_empleado) "
                    + "where e.id_empleado = '" + id_emp + "' "
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

        ///////////////////////////////////////////////////////////////////////vehiculos//////////////////////////////////////////////////////////////////////////////////////
        try {
            Connection cn11 = Conection_BD.conexion_bd();
            PreparedStatement pst11 = cn11.prepareStatement("select "
                    + "b.no_inventario, "
                    + "b.marca, "
                    + "b.modelo, "
                    + "b.no_placa, "
                    + "b.cilindraje, "
                    + "b.tarjeta, "
                    + "b.descripcion_vehiculo "
                    + "from vehiculos b "
                    + "WHERE b.id_empleado = '" + id_emp + "' and b.estatus = 'ACTIVO' "
                    + "ORDER BY b.no_inventario");

            ResultSet rs11 = pst11.executeQuery();

            tabla_2 = new JTable(modelo_tabla2);
            //para no poder mover las columnas
            tabla_2.getTableHeader().setReorderingAllowed(false);
            //tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

            DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
            tcr1.setHorizontalAlignment(SwingConstants.CENTER);
            //tamaño de alto de la tabla 
            tabla_2.setRowHeight(30);
            //color de fondo
            tabla_2.setBackground(new Color(156, 197, 152));
            //genera la fuente para los datos de la tabla
            tabla_2.setFont(new Font("Arial", Font.PLAIN, 16));
            //color del borde de la tabla
            tabla_2.setGridColor(new Color(255, 255, 255));
            //tabla.setRowMargin(10);
            //poner bordes
            //tabla_equipos.setShowGrid(true);
            tabla_2.setShowHorizontalLines(true);
            //tabla_equipos.setShowVerticalLines(true);
            //para que la tabla tenga un scroll
            jScrollPane3.setViewportView(tabla_2);

            modelo_tabla2.addColumn("No Inventario");
            modelo_tabla2.addColumn("Marca");
            modelo_tabla2.addColumn("Modelo");
            modelo_tabla2.addColumn("Placas");
            modelo_tabla2.addColumn("No. Cilindros");
            modelo_tabla2.addColumn("Tarjeta");
            modelo_tabla2.addColumn("Descripcion");

            tabla_2.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla_2.getColumnModel().getColumn(0).setPreferredWidth(200);//No Inventario
            tabla_2.getColumnModel().getColumn(1).setPreferredWidth(200);//Marca
            tabla_2.getColumnModel().getColumn(2).setPreferredWidth(200);//Modelo
            tabla_2.getColumnModel().getColumn(3).setPreferredWidth(150);//Placas
            tabla_2.getColumnModel().getColumn(4).setPreferredWidth(200);//No. Cilindros
            tabla_2.getColumnModel().getColumn(5).setPreferredWidth(100);//Tarjeta
            tabla_2.getColumnModel().getColumn(6).setPreferredWidth(200);//Descripcion          

            tabla_2.getColumnModel().getColumn(0).setResizable(false);
            tabla_2.getColumnModel().getColumn(1).setResizable(false);
            tabla_2.getColumnModel().getColumn(2).setResizable(false);
            tabla_2.getColumnModel().getColumn(3).setResizable(false);
            tabla_2.getColumnModel().getColumn(4).setResizable(false);
            tabla_2.getColumnModel().getColumn(5).setResizable(false);
            tabla_2.getColumnModel().getColumn(6).setResizable(false);

            //modelo_tabla.
            while (rs11.next()) {
                variable_vehiculo = 1;
                Object[] fila = new Object[7];//por las columnas que tengo
                for (int i = 0; i < 7; i++) {
                    fila[i] = rs11.getObject(i + 1);

                    //centra el contenido de la tabla
                    tabla_2.getColumnModel().getColumn(i).setCellRenderer(tcr1);
                    //cambiamos el color del emcabezado y centrado
                    TableColumn column = tabla_2.getTableHeader().getColumnModel().getColumn(i);
                    column.setHeaderRenderer(cellRenderer);

                    cellRenderer.setFont(new Font("Arial", Font.BOLD, 11));
                    cellRenderer.setHorizontalAlignment(cellRenderer.CENTER);
                    cellRenderer.setForeground(Color.white);

                }

                tabla_2.getTableHeader().setBackground(new Color(255, 0, 0));
                modelo_tabla2.addRow(fila);
                ((DefaultTableCellRenderer) tabla_2.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

            }

            cn11.close();

        } catch (Exception e) {
            System.out.println("Error al llenar tabla de equipos: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion de bienes personales", "Error", JOptionPane.ERROR_MESSAGE);
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
        etiqueta_area = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        etiqueta_nombre_empleado = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        etiqueta_coordinacion = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        etiqueta_clave_s_p = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        etiqueta_ubicacion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_1 = new javax.swing.JTable();
        boton_reporte = new javax.swing.JButton();
        boton_buscar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consultas y Reportes Empleados");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        etiqueta_area.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_area.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_area.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Area:");

        etiqueta_nombre_empleado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_nombre_empleado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_nombre_empleado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Ubicacion:");

        etiqueta_coordinacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_coordinacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_coordinacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Clave de Servidor Publico:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Coordinacion:");

        etiqueta_clave_s_p.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_clave_s_p.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_clave_s_p.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Nombre del empleado:");

        etiqueta_ubicacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_ubicacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta_ubicacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        boton_reporte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boton_reporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reportepdf_30.png"))); // NOI18N
        boton_reporte.setText("Generar Ficha Tecnica de Resguardo");
        boton_reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reporteActionPerformed(evt);
            }
        });

        boton_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar.setText("Regresar");
        boton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarActionPerformed(evt);
            }
        });

        tabla_2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tabla_2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boton_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(etiqueta_nombre_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(etiqueta_area, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(etiqueta_ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel4)
                                .addGap(10, 10, 10)
                                .addComponent(etiqueta_clave_s_p, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5)
                                .addGap(10, 10, 10)
                                .addComponent(etiqueta_coordinacion, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(182, 182, 182)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boton_reporte)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(etiqueta_nombre_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(etiqueta_area, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(etiqueta_ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiqueta_clave_s_p, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqueta_coordinacion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(boton_reporte, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
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

    private void boton_reporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reporteActionPerformed
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser();
        int Opcion = jfc.showSaveDialog(this);
        
        java.util.Date date = new java.util.Date();
        SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha_1 = new Date();
        String fecha_pdf = "";
        fecha_pdf = fecha.format(fecha_1);

        String consulta = "select nombre_empleado, area, ubicacion from empleados where id_empleado ='" + id_emp + "' and estatus = 'Activo'";//datos empleado
        String consulta_2 = "select ROW_NUMBER() OVER( ORDER BY no_inventario) as NP, b.descripcion_bien, b.marca, b.no_inventario, b.no_serie, b.tipo_sico_sareco from bienes b JOIN empleados e ON (b.id_empleado = e.id_empleado) where e.id_empleado = '" + id_emp + "' and (b.estatus ='ACTIVO' OR b.estatus ='FUNCIONA')";//bienes
        String consulta_3 = "select ROW_NUMBER() OVER( ORDER BY id_bien_personal) as NP, b.bien_personal from bienes_personales b JOIN empleados e ON (b.id_empleado = e.id_empleado) where e.id_empleado = '" + id_emp + "' ORDER BY b.id_bien_personal";//bienes personales
        String consulta_veh = "select b.no_inventario from vehiculos b WHERE b.id_empleado = '" + id_emp + "' and b.estatus = 'ACTIVO' ORDER BY b.no_inventario";//bienes personales

        if (Opcion == JFileChooser.APPROVE_OPTION) {
            File f = jfc.getSelectedFile();

            System.out.println(f);

            float[] margenCeldas = new float[]{20f, 15f, 15f};//este arreglo sirve para las medidas que tengran cada una de las columnas si son 10 columnas son 1o numero
            float[] margenCeldas_2 = new float[]{5f, 25f, 10f, 15f, 15f, 10f};//este arreglo sirve para las medidas que tengran cada una de las columnas si son 10 columnas son 1o numero
            float[] margenCeldas_3 = new float[]{5f, 30f};//este arreglo sirve para las medidas que tengran cada una de las columnas si son 10 columnas son 1o numero
            float[] margenceldas_ve = new float[]{30f};
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
                parrafo.add("Ficha Tecnica de Resguardo\n\n");

                Paragraph parrafo_2 = new Paragraph();
                parrafo_2.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo_2.setFont(FontFactory.getFont("Arial", 16, Font.BOLD, BaseColor.DARK_GRAY));
                parrafo_2.add("Bienes\n\n");
                Paragraph parrafo_3 = new Paragraph();
                parrafo_3.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo_3.setFont(FontFactory.getFont("Arial", 16, Font.BOLD, BaseColor.DARK_GRAY));
                parrafo_3.add("Bienes Personales\n\n");
                Paragraph parrafo_ve = new Paragraph();
                parrafo_ve.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo_ve.setFont(FontFactory.getFont("Arial", 16, Font.BOLD, BaseColor.DARK_GRAY));
                parrafo_ve.add("Vehiculos\n\n");

                Paragraph espacio = new Paragraph();
                espacio.setAlignment(Paragraph.ALIGN_CENTER);
                espacio.setFont(FontFactory.getFont("Arial", 16, Font.BOLD, BaseColor.DARK_GRAY));
                espacio.add("\n");

                Paragraph firma_fecha = new Paragraph();
                firma_fecha.setAlignment(Paragraph.ALIGN_CENTER);
                firma_fecha.setFont(FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.DARK_GRAY));
                firma_fecha.add("NOMBRE Y FIRMA                                                              FECHA DE RESGUARDO\n\n"
                        + "                                                                                                "+fecha_pdf+"\n"
                        + "____________________________                                                                                                    ");

                Paragraph espaciofinal = new Paragraph();
                espaciofinal.setAlignment(Paragraph.ALIGN_CENTER);
                espaciofinal.setFont(FontFactory.getFont("Arial", 16, Font.BOLD, BaseColor.DARK_GRAY));
                espaciofinal.add("\n\n\n\n");
                //abre el documento
                documento.open();
                //agregamos imagen y parrfos
                documento.add(imagen_cabecera);
                documento.add(parrafo);
                //numero de columnas
                PdfPTable tabla = new PdfPTable(3);
                tabla.setWidthPercentage(70);//el porcentaje total de ancho del documento tomara
                tabla.setWidths(margenCeldas);//aqui va el arreglo            
                PdfPCell cell = new PdfPCell(new Phrase("Usuario"));//aqui podrimos modificar las celdas
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
                    } while (rs.next());
                    tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
                    documento.add(tabla);
                    documento.add(espacio);
                }

                ///////////////////////////////////////////////////////////////bienes//////////////////////////////////////////////////////////////////////////////////
                if (variable_bienes == 1) {
                    documento.add(parrafo_2);
                    PdfPTable tabla_2 = new PdfPTable(6);
                    tabla_2.setWidthPercentage(95);//el porcentaje total de ancho del documento tomara
                    tabla_2.setWidths(margenCeldas_2);//aqui va el arreglo            
                    PdfPCell cell_22 = new PdfPCell(new Phrase("NP"));//aqui podrimos modificar las celdas
                    cell_22.setVerticalAlignment(Element.ALIGN_CENTER);//alineados horizontales y verticales
                    cell_22.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell_22.setBackgroundColor(new BaseColor(183, 183, 183));//color de fondo
                    cell_22.setFixedHeight(20);//alto de la celda que se le aplica a todas de la fila
                    tabla_2.addCell(cell_22);
                    PdfPCell cell_1_2 = new PdfPCell(new Phrase("Articulo"));
                    cell_1_2.setVerticalAlignment(Element.ALIGN_CENTER);
                    cell_1_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell_1_2.setBackgroundColor(new BaseColor(183, 183, 183));
                    tabla_2.addCell(cell_1_2);
                    PdfPCell cell_2_2 = new PdfPCell(new Phrase("Marca"));
                    cell_2_2.setVerticalAlignment(Element.ALIGN_CENTER);
                    cell_2_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell_2_2.setBackgroundColor(new BaseColor(183, 183, 183));
                    tabla_2.addCell(cell_2_2);
                    PdfPCell cell_3_2 = new PdfPCell(new Phrase("Inventario"));
                    cell_3_2.setVerticalAlignment(Element.ALIGN_CENTER);
                    cell_3_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell_3_2.setBackgroundColor(new BaseColor(183, 183, 183));
                    tabla_2.addCell(cell_3_2);
                    PdfPCell cell_4_2 = new PdfPCell(new Phrase("Serie"));
                    cell_4_2.setVerticalAlignment(Element.ALIGN_CENTER);
                    cell_4_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell_4_2.setBackgroundColor(new BaseColor(183, 183, 183));
                    tabla_2.addCell(cell_4_2);
                    PdfPCell cell_5_2 = new PdfPCell(new Phrase("OBS"));
                    cell_5_2.setVerticalAlignment(Element.ALIGN_CENTER);
                    cell_5_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell_5_2.setBackgroundColor(new BaseColor(183, 183, 183));
                    tabla_2.addCell(cell_5_2);

                    Connection cn1 = Conection_BD.conexion_bd();
                    PreparedStatement pst1 = cn1.prepareStatement(consulta_2);
                    ResultSet rs1 = pst1.executeQuery();

                    if (rs1.next()) {
                        do {                        //columna
                            PdfPCell colu_1 = new PdfPCell(new Phrase(rs1.getString(1)));
                            colu_1.setVerticalAlignment(Element.ALIGN_CENTER);
                            colu_1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tabla_2.addCell(colu_1);
                            PdfPCell colu_2 = new PdfPCell(new Phrase(rs1.getString(2)));
                            colu_2.setVerticalAlignment(Element.ALIGN_CENTER);
                            colu_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tabla_2.addCell(colu_2);
                            PdfPCell colu_3 = new PdfPCell(new Phrase(rs1.getString(3)));
                            colu_3.setVerticalAlignment(Element.ALIGN_CENTER);
                            colu_3.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tabla_2.addCell(colu_3);
                            PdfPCell colu_4 = new PdfPCell(new Phrase(rs1.getString(4)));
                            colu_4.setVerticalAlignment(Element.ALIGN_CENTER);
                            colu_4.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tabla_2.addCell(colu_4);
                            PdfPCell colu_5 = new PdfPCell(new Phrase(rs1.getString(5)));
                            colu_5.setVerticalAlignment(Element.ALIGN_CENTER);
                            colu_5.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tabla_2.addCell(colu_5);
                            PdfPCell colu_6 = new PdfPCell(new Phrase(rs1.getString(6)));
                            colu_6.setVerticalAlignment(Element.ALIGN_CENTER);
                            colu_6.setHorizontalAlignment(Element.ALIGN_CENTER);
                            colu_6.setFixedHeight(35);
                            tabla_2.addCell(colu_6);
                        } while (rs1.next());
                        tabla_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        documento.add(tabla_2);
                        documento.add(espacio);
                    }
                }

                ///////////////////////////////////////////////////////////////bienespersonales //////////////////////////////////////////////////////////////////////////////////
                if (variable_bienesper == 1) {
                    documento.add(parrafo_3);
                    PdfPTable tabla_3 = new PdfPTable(2);
                    tabla_3.setWidthPercentage(60);//el porcentaje total de ancho del documento tomara
                    tabla_3.setWidths(margenCeldas_3);//aqui va el arreglo            
                    PdfPCell cell_2_3 = new PdfPCell(new Phrase("NP"));//aqui podrimos modificar las celdas
                    cell_2_3.setVerticalAlignment(Element.ALIGN_CENTER);//alineados horizontales y verticales
                    cell_2_3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell_2_3.setBackgroundColor(new BaseColor(183, 183, 183));//color de fondo
                    cell_2_3.setFixedHeight(20);//alto de la celda que se le aplica a todas de la fila
                    tabla_3.addCell(cell_2_3);
                    PdfPCell cell_133 = new PdfPCell(new Phrase("Bien Personal"));
                    cell_133.setVerticalAlignment(Element.ALIGN_CENTER);
                    cell_133.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell_133.setBackgroundColor(new BaseColor(183, 183, 183));
                    tabla_3.addCell(cell_133);

                    Connection cn3 = Conection_BD.conexion_bd();
                    PreparedStatement pst3 = cn3.prepareStatement(consulta_3);
                    ResultSet rs3 = pst3.executeQuery();

                    if (rs3.next()) {
                        do {                        //columna
                            PdfPCell colu_1 = new PdfPCell(new Phrase(rs3.getString(1)));
                            colu_1.setVerticalAlignment(Element.ALIGN_CENTER);
                            colu_1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tabla_3.addCell(colu_1);
                            PdfPCell colu_2 = new PdfPCell(new Phrase(rs3.getString(2)));
                            colu_2.setVerticalAlignment(Element.ALIGN_CENTER);
                            colu_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tabla_3.addCell(colu_2);
                        } while (rs3.next());
                        tabla_3.setHorizontalAlignment(Element.ALIGN_CENTER);
                        documento.add(tabla_3);
                        documento.add(espacio);
                    }
                }
                //documento.

                /////////////////////////////////////////////////////////tabla vehiculos/////////////////////////////////////////////////////////////////////////////
                if (variable_vehiculo == 1) {
                    documento.add(parrafo_ve);
                    PdfPTable tabla_ve = new PdfPTable(1);
                    tabla_ve.setWidthPercentage(95);//el porcentaje total de ancho del documento tomara
                    tabla_ve.setWidths(margenceldas_ve);//aqui va el arreglo            
                    PdfPCell celda_1v = new PdfPCell(new Phrase("NP"));//aqui podrimos modificar las celdas
                    celda_1v.setVerticalAlignment(Element.ALIGN_CENTER);//alineados horizontales y verticales
                    celda_1v.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celda_1v.setBackgroundColor(new BaseColor(183, 183, 183));//color de fondo
                    celda_1v.setFixedHeight(20);//alto de la celda que se le aplica a todas de la fila
                    tabla_ve.addCell(celda_1v);
//                    PdfPCell celda_2v = new PdfPCell(new Phrase("Articulo"));
//                    celda_2v.setVerticalAlignment(Element.ALIGN_CENTER);
//                    celda_2v.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    celda_2v.setBackgroundColor(new BaseColor(183, 183, 183));
//                    tabla_ve.addCell(celda_2v);
//                    PdfPCell celda_3v = new PdfPCell(new Phrase("Inventario"));
//                    celda_3v.setVerticalAlignment(Element.ALIGN_CENTER);
//                    celda_3v.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    celda_3v.setBackgroundColor(new BaseColor(183, 183, 183));
//                    tabla_ve.addCell(celda_3v);
//                    PdfPCell celda_4v = new PdfPCell(new Phrase("Serie"));
//                    celda_4v.setVerticalAlignment(Element.ALIGN_CENTER);
//                    celda_4v.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    celda_4v.setBackgroundColor(new BaseColor(183, 183, 183));
//                    tabla_ve.addCell(celda_4v);
//                    PdfPCell celda_5v = new PdfPCell(new Phrase("OBS"));
//                    celda_5v.setVerticalAlignment(Element.ALIGN_CENTER);
//                    celda_5v.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    celda_5v.setBackgroundColor(new BaseColor(183, 183, 183));
//                    tabla_ve.addCell(celda_5v);

                    Connection con_ve = Conection_BD.conexion_bd();
                    PreparedStatement pst1_ve = con_ve.prepareStatement(consulta_veh);
                    ResultSet rs1_ve = pst1_ve.executeQuery();

                    if (rs1_ve.next()) {
                        do {                        //columna
                            PdfPCell colu_1v = new PdfPCell(new Phrase(rs1_ve.getString(1)));
                            colu_1v.setVerticalAlignment(Element.ALIGN_CENTER);
                            colu_1v.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tabla_ve.addCell(colu_1v);
//                            PdfPCell colu_2v = new PdfPCell(new Phrase(rs1.getString(2)));
//                            colu_2v.setVerticalAlignment(Element.ALIGN_CENTER);
//                            colu_2v.setHorizontalAlignment(Element.ALIGN_CENTER);
//                            tabla_ve.addCell(colu_2v);
//                            PdfPCell colu_3v = new PdfPCell(new Phrase(rs1.getString(3)));
//                            colu_3v.setVerticalAlignment(Element.ALIGN_CENTER);
//                            colu_3v.setHorizontalAlignment(Element.ALIGN_CENTER);
//                            tabla_ve.addCell(colu_3v);
//                            PdfPCell colu_4v = new PdfPCell(new Phrase(rs1.getString(4)));
//                            colu_4v.setVerticalAlignment(Element.ALIGN_CENTER);
//                            colu_4v.setHorizontalAlignment(Element.ALIGN_CENTER);
//                            tabla_ve.addCell(colu_4v);
//                            PdfPCell colu_5v = new PdfPCell(new Phrase(rs1.getString(5)));
//                            colu_5v.setVerticalAlignment(Element.ALIGN_CENTER);
//                            colu_5v.setHorizontalAlignment(Element.ALIGN_CENTER);
//                            tabla_ve.addCell(colu_5v);
                        } while (rs1_ve.next());
                        tabla_ve.setHorizontalAlignment(Element.ALIGN_CENTER);
                        documento.add(tabla_ve);
                        documento.add(espacio);
                    }

                    System.out.println("hay vehiculos");
                }
                documento.add(firma_fecha);
                documento.add(espaciofinal);
                documento.add(imagen_abajo);
                documento.close();
                Icon icono_correcto = new ImageIcon(getClass().getResource("/imagenes/correcto.png"));
                JOptionPane.showMessageDialog(null, "Reporte Creado", "Reporte", JOptionPane.INFORMATION_MESSAGE, icono_correcto);
            } catch (Exception e) {
                System.out.println("Error: " + e);
                JOptionPane.showMessageDialog(null, "Error al generar PDF", "Error", JOptionPane.ERROR_MESSAGE);
            }
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);

        }
    }//GEN-LAST:event_boton_reporteActionPerformed

    private void boton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarActionPerformed

        consultasyReportesEmpleados_tabla p = new consultasyReportesEmpleados_tabla();
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
            java.util.logging.Logger.getLogger(consultasyReportesEmpleados_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consultasyReportesEmpleados_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consultasyReportesEmpleados_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consultasyReportesEmpleados_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(consultasyReportesEmpleados_datos.class.getName()).log(Level.SEVERE, null, ex);
                }
                new consultasyReportesEmpleados_datos().setVisible(true);
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
            consultasyReportesEmpleados_tabla p = new consultasyReportesEmpleados_tabla();
            p.setVisible(true);
            this.dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_buscar;
    private javax.swing.JButton boton_reporte;
    private javax.swing.JLabel etiqueta_area;
    private javax.swing.JLabel etiqueta_clave_s_p;
    private javax.swing.JLabel etiqueta_coordinacion;
    private javax.swing.JLabel etiqueta_nombre_empleado;
    private javax.swing.JLabel etiqueta_ubicacion;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla_1;
    private javax.swing.JTable tabla_2;
    // End of variables declaration//GEN-END:variables
}
