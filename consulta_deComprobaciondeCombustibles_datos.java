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
import static interfaces.detalles_bienesMuebles_tabla.id_bien;
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
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class consulta_deComprobaciondeCombustibles_datos extends javax.swing.JFrame {

    public int id_vehi = 0, id = 0;
    private String fecha = "", fecha_2 = "";
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

    /**
     * Creates new form consulta_deComprobaciondeCombustibles_datos
     */
    public consulta_deComprobaciondeCombustibles_datos() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoa.png")).getImage());
        boton_reporte.setEnabled(false);//desabilidatmos el botn de reporte
        //cerrar();
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        //cerrar();
        id_vehi = consulta_deComprobaciondeCombustibles_tabla.id_vehiculo;
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
                    + "ROW_NUMBER() OVER( ORDER BY id_carga) as NP,"
                    + "c.id_carga, "
                    + "DATE_ADD(fecha_carga, interval 1 day), "
                    + "concat('$  ',c.importe), "
                    + "c.recibo_entregado, "
                    + "c.comision, "
                    + "c.kilometraje_inicial, "
                    + "c.kilometraje_final "
                    + "from comprobacion_combustibles c "
                    + "where c.id_vehiculo = '" + id_vehi + "' "
                    + "ORDER BY c.fecha_carga");
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
            modelo_tabla.addColumn("Id Carga");
            modelo_tabla.addColumn("Fecha de carga");
            modelo_tabla.addColumn("Importe");
            modelo_tabla.addColumn("Recibo entregado");
            modelo_tabla.addColumn("Comision");
            modelo_tabla.addColumn("Kilometraje inicial");
            modelo_tabla.addColumn("Kilometraje final");

            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);//id
            tabla.getColumnModel().getColumn(1).setPreferredWidth(100);//id
            tabla.getColumnModel().getColumn(2).setPreferredWidth(200);//fecha
            tabla.getColumnModel().getColumn(3).setPreferredWidth(200);//importe
            tabla.getColumnModel().getColumn(4).setPreferredWidth(100);//recibo
            tabla.getColumnModel().getColumn(5).setPreferredWidth(200);//comision
            tabla.getColumnModel().getColumn(6).setPreferredWidth(200);//kilometraje inicial
            tabla.getColumnModel().getColumn(7).setPreferredWidth(200);//kilometraje final      

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
            tabla.getColumnModel().getColumn(7).setResizable(false);

            //modelo_tabla.
            while (rs.next()) {
                Object[] fila = new Object[8];//por las columnas que tengo
                for (int i = 0; i < 8; i++) {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        boton_reporte = new javax.swing.JButton();
        barra_fecha_1 = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        barra_fecha_2 = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        boton_consultar = new javax.swing.JButton();
        boton_buscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consulta de Comprobacion de combustibles y lubricantes");

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

        boton_reporte.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_reporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reportepdf_30.png"))); // NOI18N
        boton_reporte.setText("Generar Reporte");
        boton_reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reporteActionPerformed(evt);
            }
        });

        barra_fecha_1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Fecha Inicial:");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("*en caso de que la fecha se HOY no selecciones nada");

        barra_fecha_2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Fecha de Termino:");

        boton_consultar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_consultar.setText("Consultar");
        boton_consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_consultarActionPerformed(evt);
            }
        });

        boton_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar.setText("Regresar");
        boton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(barra_fecha_1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(barra_fecha_2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(etiqueta_no_inventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiqueta_cilindraje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiqueta_kilometraje_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiqueta_tipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(etiqueta_marca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(etiqueta_no_motor, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiqueta_placas, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(etiqueta_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(boton_consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(etiqueta_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(12, 12, 12)
                        .addComponent(etiqueta_tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boton_reporte)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(etiqueta_no_inventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiqueta_marca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiqueta_modelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(etiqueta_kilometraje_actual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiqueta_no_motor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiqueta_placas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etiqueta_cilindraje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiqueta_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)))
                    .addComponent(etiqueta_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiqueta_tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boton_consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(barra_fecha_1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(barra_fecha_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boton_reporte, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
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

    private void boton_consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_consultarActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        boton_reporte.setEnabled(true);//habilidatmos el botn de reporte
        java.util.Date date = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_1 = new Date();

        if (barra_fecha_1.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Error debes elegir una fecha inicial ", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (barra_fecha_2.getDate() == null) {
                fecha_2 = f.format(fecha_1);
            } else {
                fecha_2 = f.format(barra_fecha_2.getDate());
            }
            fecha = f.format(barra_fecha_1.getDate());

            modelo_tabla.setRowCount(0);
            modelo_tabla.setColumnCount(0);
            DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
            cellRenderer.setBackground(new Color(51, 102, 255));
            try {
                Connection cn1 = Conection_BD.conexion_bd();//bienes informaticos
                PreparedStatement pst1 = cn1.prepareStatement("select "
                        + "ROW_NUMBER() OVER( ORDER BY id_carga) as NP,"
                        + "c.id_carga, "
                        + "DATE_ADD(fecha_carga, interval 1 day), "
                        + "concat('$  ',c.importe), "
                        + "c.recibo_entregado, "
                        + "c.comision, "
                        + "c.kilometraje_inicial, "
                        + "c.kilometraje_final "
                        + "from comprobacion_combustibles c "
                        + "where (c.id_vehiculo = '" + id_vehi + "') and (fecha_carga BETWEEN '" + fecha + "' AND '" + fecha_2 + "') "
                        + "ORDER BY fecha_carga");
                System.out.println(barra_fecha_1.getDate());
                System.out.println(barra_fecha_2.getDate());
                // + "where descripcion_bien like '%" + barra_busqueda.getText().trim() + "%' AND b.estatus = 'NO ACTIVO' "
                // + "ORDER BY b.no_inventario");
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
                //modelo_tabla.addColumn("NP");
                modelo_tabla.addColumn("NP");
                modelo_tabla.addColumn("Id Carga");
                modelo_tabla.addColumn("Fecha de carga");
                modelo_tabla.addColumn("Importe");
                modelo_tabla.addColumn("Recibo entregado");
                modelo_tabla.addColumn("Comision");
                modelo_tabla.addColumn("Kilometraje inicial");
                modelo_tabla.addColumn("Kilometraje final");

                tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
                tabla.getColumnModel().getColumn(0).setPreferredWidth(100);//id
                tabla.getColumnModel().getColumn(1).setPreferredWidth(100);//id
                tabla.getColumnModel().getColumn(2).setPreferredWidth(200);//fecha
                tabla.getColumnModel().getColumn(3).setPreferredWidth(200);//importe
                tabla.getColumnModel().getColumn(4).setPreferredWidth(100);//recibo
                tabla.getColumnModel().getColumn(5).setPreferredWidth(200);//comision
                tabla.getColumnModel().getColumn(6).setPreferredWidth(200);//kilometraje inicial
                tabla.getColumnModel().getColumn(7).setPreferredWidth(200);//kilometraje final      

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
                System.out.println("Error al llenar tabla: " + e);
                //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
                JOptionPane.showMessageDialog(null, "Error al mostrar informacion ", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

        /*
        select
        ROW_NUMBER() OVER( ORDER BY id_bien) as NP,
        no_inventario,
        descripcion_bien,
        estado_actual, marca,
        concat('$ ',valor_estimado)
        from bienes
        where ultima_modificacion BETWEEN '2021-02-01' AND '2022-02-10'
        and tipo_info_mueble = 'MUEBLE'
        and estatus = 'NO ACTIVO'
         */

    }//GEN-LAST:event_boton_consultarActionPerformed

    private void boton_reporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reporteActionPerformed
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser();
        int Opcion = jfc.showSaveDialog(this);

        String consulta = "Select no_inventario, marca, modelo, kilometraje, no_motor, no_placa, cilindraje, tipo_vehiculo, tarjeta, estatus from vehiculos b where b.id_vehiculo ='" + id_vehi + "' and b.estatus = 'ACTIVO'  ORDER BY no_inventario;";
        String consulta_2 = "select fecha_carga, concat('$  ',c.importe), c.comision, c.kilometraje_inicial, c.kilometraje_final from comprobacion_combustibles c where (c.id_vehiculo = '" + id_vehi + "') and (fecha_carga BETWEEN '" + fecha + "' AND '" + fecha_2 + "') ORDER BY fecha_carga";
        if (Opcion == JFileChooser.APPROVE_OPTION) {
            File f = jfc.getSelectedFile();
            System.out.println(f);

            float[] margenCeldas = new float[]{10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f};//este arreglo sirve para las medidas que tengran cada una de las columnas si son 10 columnas son 1o numero
            float[] margenCeldas_2 = new float[]{10f, 10f, 20f, 10f, 10f};//este arreglo sirve para las medidas que tengran cada una de las columnas si son 10 columnas son 1o numero
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
                parrafo.add("COMPROBACION DE COMBUSTIBLES Y LUBRICANTES\n\n");

                Paragraph parrafo_2 = new Paragraph();
                parrafo_2.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo_2.setFont(FontFactory.getFont("Arial", 24, Font.BOLD, BaseColor.DARK_GRAY));
                parrafo_2.add("\n");
                //abre el documento
                documento.open();
                //agregamos imagen y parrfos
                documento.add(imagen_cabecera);
                documento.add(parrafo);
                //numero de columnas
                PdfPTable tabla = new PdfPTable(10);
                tabla.setWidthPercentage(95);//el porcentaje total de ancho del documento tomara
                tabla.setWidths(margenCeldas);//aqui va el arreglo            
                PdfPCell cell = new PdfPCell(new Phrase("No. Inventario"));//aqui podrimos modificar las celdas
                cell.setVerticalAlignment(Element.ALIGN_CENTER);//alineados horizontales y verticales
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(183, 183, 183));//color de fondo
                cell.setFixedHeight(20);//alto de la celda que se le aplica a todas de la fila
                tabla.addCell(cell);
                PdfPCell cell_1 = new PdfPCell(new Phrase("Marca"));
                cell_1.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_1.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_1);
                PdfPCell cell_2 = new PdfPCell(new Phrase("Modelo"));
                cell_2.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_2.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_2);
                PdfPCell cell_3 = new PdfPCell(new Phrase("Kilometraje"));
                cell_3.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_3.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_3);
                PdfPCell cell_4 = new PdfPCell(new Phrase("No. Motor"));
                cell_4.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_4.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_4.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_4);
                PdfPCell cell_5 = new PdfPCell(new Phrase("Placas"));
                cell_5.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_5.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_5.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_5);
                tabla.setWidths(margenCeldas);//aqui va el arreglo            
                PdfPCell cell_6 = new PdfPCell(new Phrase("No. Cilindros"));//aqui podrimos modificar las celdas
                cell_6.setVerticalAlignment(Element.ALIGN_CENTER);//alineados horizontales y verticales
                cell_6.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_6.setBackgroundColor(new BaseColor(183, 183, 183));//color de fondo
                cell_6.setFixedHeight(20);//alto de la celda que se le aplica a todas de la fila
                tabla.addCell(cell_6);
                PdfPCell cell_7 = new PdfPCell(new Phrase("Tipo"));
                cell_7.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_7.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_7.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_7);
                PdfPCell cell_8 = new PdfPCell(new Phrase("Tarjeta"));
                cell_8.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_8.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_8.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_8);
                PdfPCell cell_10 = new PdfPCell(new Phrase("Estatus"));
                cell_10.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_10.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_10.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_10);

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
                        PdfPCell colu_7 = new PdfPCell(new Phrase(rs.getString(7)));
                        colu_7.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_7.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_7);
                        PdfPCell colu_8 = new PdfPCell(new Phrase(rs.getString(8)));
                        colu_8.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_8.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_8);
                        PdfPCell colu_9 = new PdfPCell(new Phrase(rs.getString(9)));
                        colu_9.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_9.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_9);
                        PdfPCell colu_10 = new PdfPCell(new Phrase(rs.getString(10)));
                        colu_10.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_10.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_10);
                    } while (rs.next());
                    tabla.setHorizontalAlignment(Element.ALIGN_CENTER);

                    documento.add(tabla);
                }
                documento.add(parrafo_2);
                ///////////////////////////////////////////////////////////////s e g u n d a    t a b l a //////////////////////////////////////////////////////////////////////////////////
                PdfPTable tabla_2 = new PdfPTable(5);
                tabla_2.setWidthPercentage(95);//el porcentaje total de ancho del documento tomara
                tabla_2.setWidths(margenCeldas_2);//aqui va el arreglo            
                PdfPCell cell_22 = new PdfPCell(new Phrase("Fecha Carga"));//aqui podrimos modificar las celdas
                cell_22.setVerticalAlignment(Element.ALIGN_CENTER);//alineados horizontales y verticales
                cell_22.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_22.setBackgroundColor(new BaseColor(183, 183, 183));//color de fondo
                cell_22.setFixedHeight(20);//alto de la celda que se le aplica a todas de la fila
                tabla_2.addCell(cell_22);
                PdfPCell cell_1_2 = new PdfPCell(new Phrase("Importe"));
                cell_1_2.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_1_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_1_2.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla_2.addCell(cell_1_2);
                PdfPCell cell_3_2 = new PdfPCell(new Phrase("Comision"));
                cell_3_2.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_3_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_3_2.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla_2.addCell(cell_3_2);
                PdfPCell cell_4_2 = new PdfPCell(new Phrase("Kilometraje Inicial"));
                cell_4_2.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_4_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_4_2.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla_2.addCell(cell_4_2);
                PdfPCell cell_5_2 = new PdfPCell(new Phrase("Kilometraje Final"));
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

                    } while (rs1.next());
                    tabla_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    documento.add(tabla_2);
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

        }
    }//GEN-LAST:event_boton_reporteActionPerformed

    private void boton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarActionPerformed

        consulta_deComprobaciondeCombustibles_tabla p = new consulta_deComprobaciondeCombustibles_tabla();
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
            java.util.logging.Logger.getLogger(consulta_deComprobaciondeCombustibles_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consulta_deComprobaciondeCombustibles_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consulta_deComprobaciondeCombustibles_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consulta_deComprobaciondeCombustibles_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(consulta_deComprobaciondeCombustibles_datos.class.getName()).log(Level.SEVERE, null, ex);
                }
                new consulta_deComprobaciondeCombustibles_datos().setVisible(true);
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
            consulta_deComprobaciondeCombustibles_tabla p = new consulta_deComprobaciondeCombustibles_tabla();
            p.setVisible(true);
            this.dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser barra_fecha_1;
    private com.toedter.calendar.JDateChooser barra_fecha_2;
    private javax.swing.JButton boton_buscar;
    private javax.swing.JButton boton_consultar;
    private javax.swing.JButton boton_reporte;
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
