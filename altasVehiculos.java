/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conection_BD;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author jo199
 */
public class altasVehiculos extends javax.swing.JFrame {

    /**
     * Creates new form altasVehiculos
     */
    public altasVehiculos() {
        initComponents();
        //cerrar();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoa.png")).getImage());
        boton_transparente(boton_aceptar);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        barra_descripcion = new javax.swing.JTextField();
        barra_terminacion_tarjeta = new javax.swing.JTextField();
        barra_tipo_vehiculo = new javax.swing.JTextField();
        barra_placas = new javax.swing.JTextField();
        barra_motor = new javax.swing.JTextField();
        barra_kilometraje = new javax.swing.JTextField();
        barra_modelo = new javax.swing.JTextField();
        barra_marca = new javax.swing.JTextField();
        barra_no_inventario = new javax.swing.JTextField();
        barra_cilindraje = new javax.swing.JComboBox<>();
        boton_aceptar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        barra_fecha = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        boton_buscar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Alta de vehiculos");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Marca:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Modelo:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Kilometraje actual:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("No. Motor:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("No. Placas:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Cilindraje: ");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Tipo de vehiculo:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Terminacion de tarjeta asignada:");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Descripcion: ");

        barra_descripcion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_descripcion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        barra_terminacion_tarjeta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_terminacion_tarjeta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        barra_terminacion_tarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                barra_terminacion_tarjetaKeyTyped(evt);
            }
        });

        barra_tipo_vehiculo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_tipo_vehiculo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        barra_placas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_placas.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        barra_motor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_motor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        barra_kilometraje.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_kilometraje.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        barra_modelo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_modelo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        barra_marca.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_marca.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        barra_no_inventario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_no_inventario.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        barra_cilindraje.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_cilindraje.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4 CILINDROS", "6 CILINDROS", "12 CILINDROS", "16 CILINDROS" }));

        boton_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar_100.png"))); // NOI18N
        boton_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_aceptarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Almacen");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("No. Inventario:");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Responsable:");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Fecha:");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("*en caso de que la fecha se HOY no selecciones nada");

        barra_fecha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Dar de alta");

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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(barra_placas)
                    .addComponent(barra_cilindraje, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barra_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barra_descripcion)
                    .addComponent(barra_tipo_vehiculo)
                    .addComponent(barra_terminacion_tarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(barra_kilometraje, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(barra_marca, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(barra_modelo, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(barra_no_inventario, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(barra_motor, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(boton_aceptar, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(barra_no_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(barra_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(barra_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel4))
                                    .addComponent(barra_kilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(barra_motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(barra_placas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(barra_cilindraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(barra_tipo_vehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(barra_terminacion_tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel11)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boton_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(barra_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addComponent(jLabel14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15))))
                    .addComponent(barra_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
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

    private void boton_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_aceptarActionPerformed
        // TODO add your handling code here:
        java.util.Date date = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_1 = new Date();
        int validacion = 0;
        String numero_inventario, marca, modelo, kilometraje, no_motor, no_placas, cilindraje, tipo_vehiculo, tarjeta, id_empleado, descripcion_vehiculo;
        String fecha = "";
        numero_inventario = barra_no_inventario.getText().trim();
        marca = barra_marca.getText().trim().toUpperCase();
        modelo = barra_modelo.getText().trim().toUpperCase();
        kilometraje = barra_kilometraje.getText().trim().toUpperCase();
        no_motor = barra_motor.getText().trim().toUpperCase();
        no_placas = barra_placas.getText().trim().toUpperCase();
        cilindraje = barra_cilindraje.getSelectedItem().toString().trim().toUpperCase();
        tipo_vehiculo = barra_tipo_vehiculo.getText().trim().toUpperCase();
        tarjeta = barra_terminacion_tarjeta.getText().trim().toUpperCase();
        id_empleado = "4";
        descripcion_vehiculo = barra_descripcion.getText().trim();
        if (barra_fecha.getDate() == null) {
            fecha = f.format(fecha_1);
        } else {
            fecha = f.format(barra_fecha.getDate());
        }
//        if (barra_verificacion.getDate() == null) {
//            fecha = f.format(fecha_1);
//        } else {
//            fecha = f.format(barra_verificacion.getDate());
//        }

        if (numero_inventario.equals("")) {
            barra_no_inventario.setBackground(Color.red);
            validacion++;
        }
        if (marca.equals("")) {
            barra_marca.setBackground(Color.red);
            validacion++;

        }
        if (modelo.equals("")) {
            barra_modelo.setBackground(Color.red);
            validacion++;
        }
        if (kilometraje.equals("")) {
            barra_kilometraje.setBackground(Color.red);
            validacion++;
        }
        if (no_motor.equals("")) {
            barra_motor.setBackground(Color.red);
            validacion++;
        }
        if (no_placas.equals("")) {
            barra_placas.setBackground(Color.red);
            validacion++;
        }
        if (cilindraje.equals("")) {
            barra_cilindraje.setBackground(Color.red);
            validacion++;
        }
        if (tipo_vehiculo.equals("")) {
            barra_tipo_vehiculo.setBackground(Color.red);
            validacion++;
        }
        if (tarjeta.equals("")) {
            barra_terminacion_tarjeta.setBackground(Color.red);
            validacion++;
        }
        if (descripcion_vehiculo.equals("")) {
            barra_descripcion.setBackground(Color.red);
            validacion++;
        }
        if (validacion == 0) {
            try {
                Connection cn = Conection_BD.conexion_bd();
                PreparedStatement pst = cn.prepareStatement("insert into vehiculos (id_vehiculo, "
                        + "no_inventario, "
                        + "marca, "
                        + "modelo, "
                        + "kilometraje, "
                        + "no_motor, "
                        + "no_placa, "
                        + "cilindraje, "
                        + "tipo_vehiculo, "
                        + "tarjeta, "
                        + "id_empleado, "
                        + "descripcion_vehiculo, "
                        + "estatus, "
                        + "ultima_modificacion) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setInt(1, 0);
                pst.setString(2, numero_inventario);
                pst.setString(3, marca);
                pst.setString(4, modelo);
                pst.setString(5, kilometraje);
                pst.setString(6, no_motor);
                pst.setString(7, no_placas);
                pst.setString(8, cilindraje);
                pst.setString(9, tipo_vehiculo);
                pst.setString(10, tarjeta);
                pst.setString(11, id_empleado);
                pst.setString(12, descripcion_vehiculo);
                pst.setString(13, "ACTIVO");
                pst.setString(14, fecha);
                //pst.setInt(15, null);
                pst.executeUpdate();

                pst = cn.prepareStatement("insert into movimientos (`tipo_movimiento`, `fecha`, `accion`, `tabla_afectada`) values (?,?,?,?)");
                pst.setString(1, "Alta de vehiculo");
                pst.setString(2, fecha);
                pst.setString(3, "insert vehiculos");
                pst.setString(4, "Vehiculos");
                pst.executeUpdate();

                cn.close();
            } catch (Exception e) {
                System.out.println("Error al guardar: " + e);
                //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
                JOptionPane.showMessageDialog(null, "Error al guardar ", "Error", JOptionPane.ERROR_MESSAGE);
            }
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_boton_aceptarActionPerformed

    private void barra_terminacion_tarjetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barra_terminacion_tarjetaKeyTyped
        // TODO add your handling code here:
        soloNumeros(evt);
    }//GEN-LAST:event_barra_terminacion_tarjetaKeyTyped

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
            java.util.logging.Logger.getLogger(altasVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(altasVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(altasVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(altasVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(altasVehiculos.class.getName()).log(Level.SEVERE, null, ex);
                }
                new altasVehiculos().setVisible(true);
            }
        });
    }

    public void soloNumeros(java.awt.event.KeyEvent evt)// se validaran segun el codigo ascii no permite ingresar
    {
        char numero = evt.getKeyChar();
        if (Character.isLetter(numero) && numero != '.') {
            getToolkit().beep();
            evt.consume();
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(this, "No se permiten letras en este campo", "Atencion", JOptionPane.WARNING_MESSAGE);
        }
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

    public void limpiarCampos() {

        barra_no_inventario.setBackground(Color.GREEN);
        barra_marca.setBackground(Color.GREEN);
        barra_modelo.setBackground(Color.GREEN);
        barra_kilometraje.setBackground(Color.GREEN);
        barra_motor.setBackground(Color.GREEN);
        barra_placas.setBackground(Color.GREEN);
        barra_modelo.setBackground(Color.GREEN);
        barra_cilindraje.setSelectedIndex(0);
        barra_tipo_vehiculo.setBackground(Color.GREEN);
        barra_terminacion_tarjeta.setBackground(Color.GREEN);
        barra_descripcion.setBackground(Color.GREEN);
        Icon icono_correcto = new ImageIcon(getClass().getResource("/imagenes/correcto.png"));
        int valor = JOptionPane.showConfirmDialog(null, "Se ha guardado correctamente", "Vehiculo Registrado", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, icono_correcto);
        if (valor == JOptionPane.YES_OPTION) {
            barra_no_inventario.setText("");
            barra_marca.setText("");
            barra_modelo.setText("");
            barra_kilometraje.setText("");
            barra_motor.setText("");
            barra_placas.setText("");
            barra_modelo.setText("");
            barra_cilindraje.setSelectedIndex(0);
            barra_tipo_vehiculo.setText("");
            barra_terminacion_tarjeta.setText("");
            barra_descripcion.setText("");
            barra_fecha.setCalendar(null);
        }
        barra_no_inventario.setText("");
        barra_marca.setText("");
        barra_modelo.setText("");
        barra_kilometraje.setText("");
        barra_motor.setText("");
        barra_placas.setText("");
        barra_modelo.setText("");
        barra_cilindraje.setSelectedIndex(0);
        barra_tipo_vehiculo.setText("");
        barra_terminacion_tarjeta.setText("");
        barra_descripcion.setText("");
        barra_fecha.setCalendar(null);
        barra_no_inventario.setBackground(Color.WHITE);
        barra_marca.setBackground(Color.WHITE);
        barra_modelo.setBackground(Color.WHITE);
        barra_kilometraje.setBackground(Color.WHITE);
        barra_motor.setBackground(Color.WHITE);
        barra_placas.setBackground(Color.WHITE);
        barra_modelo.setBackground(Color.WHITE);
        barra_cilindraje.setSelectedIndex(0);
        barra_tipo_vehiculo.setBackground(Color.WHITE);
        barra_terminacion_tarjeta.setBackground(Color.WHITE);
        barra_descripcion.setBackground(Color.WHITE);

    }

    public void boton_transparente(JButton boton) {
        boton.setOpaque(true);
        boton.setContentAreaFilled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> barra_cilindraje;
    private javax.swing.JTextField barra_descripcion;
    private com.toedter.calendar.JDateChooser barra_fecha;
    private javax.swing.JTextField barra_kilometraje;
    private javax.swing.JTextField barra_marca;
    private javax.swing.JTextField barra_modelo;
    private javax.swing.JTextField barra_motor;
    private javax.swing.JTextField barra_no_inventario;
    private javax.swing.JTextField barra_placas;
    private javax.swing.JTextField barra_terminacion_tarjeta;
    private javax.swing.JTextField barra_tipo_vehiculo;
    private javax.swing.JButton boton_aceptar;
    private javax.swing.JButton boton_buscar1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}