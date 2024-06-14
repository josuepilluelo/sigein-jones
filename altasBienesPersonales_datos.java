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
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class altasBienesPersonales_datos extends javax.swing.JFrame {

    public int id_em = 0, id = 0;

    /**
     * Creates new form altasBienesPersonales_datos
     */
    public altasBienesPersonales_datos() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoa.png")).getImage());
        boton_transparente(boton_aceptar);
        //cerrar();
        id_em = altasBienesPersonales_tabla.id_empleado;
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
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al cargar usuario: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al cargar informacion", "Error", JOptionPane.ERROR_MESSAGE);
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
        jLabel1 = new javax.swing.JLabel();
        etiqueta_empleado = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        barra_bienpersonal = new javax.swing.JTextField();
        boton_aceptar = new javax.swing.JButton();
        boton_buscar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Alta de Bienes Personales");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Empleado:");

        etiqueta_empleado.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        etiqueta_empleado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Bien personal:");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Dar de alta");

        barra_bienpersonal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_bienpersonal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        boton_aceptar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar_100.png"))); // NOI18N
        boton_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_aceptarActionPerformed(evt);
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
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(barra_bienpersonal)
                    .addComponent(etiqueta_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(boton_aceptar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(boton_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiqueta_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(barra_bienpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
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
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void boton_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_aceptarActionPerformed
        // TODO add your handling code here:
        java.util.Date date = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_1 = new Date();
        String fecha = "";
        fecha = f.format(fecha_1);
        int validacion = 0;
        String bien_personal;
        id_em = altasBienesPersonales_tabla.id_empleado;
        bien_personal = barra_bienpersonal.getText().trim();
        if (bien_personal.equals("")) {
            barra_bienpersonal.setBackground(Color.red);
            validacion++;
        }
        if (validacion == 0) {
            try {
                Connection cn = Conection_BD.conexion_bd();
                PreparedStatement pst = cn.prepareStatement("insert into bienes_personales values(?,?,?)");
                pst.setInt(1, 0);
                pst.setInt(2, id_em);
                pst.setString(3, bien_personal);
                pst.executeUpdate();

                pst = cn.prepareStatement("insert into movimientos (`tipo_movimiento`, `fecha`, `accion`, `tabla_afectada`) values (?,?,?,?)");
                pst.setString(1, "Alta bien personal");
                pst.setString(2, fecha);
                pst.setString(3, "insert bien personal id empleado: "+id_em);
                pst.setString(4, "Bienes Personales");
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

    private void boton_buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscar1ActionPerformed

        altasBienesPersonales_tabla p = new altasBienesPersonales_tabla();
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
            java.util.logging.Logger.getLogger(altasBienesPersonales_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(altasBienesPersonales_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(altasBienesPersonales_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(altasBienesPersonales_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(altasBienesPersonales_datos.class.getName()).log(Level.SEVERE, null, ex);
                }
                new altasBienesPersonales_datos().setVisible(true);
            }
        });
    }

    public void limpiarCampos() {

        barra_bienpersonal.setBackground(Color.GREEN);

        Icon icono_correcto = new ImageIcon(getClass().getResource("/imagenes/correcto.png"));
        int valor = JOptionPane.showConfirmDialog(null, "Se ha guardado correctamente", "Bien Personal Registrado", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, icono_correcto);
        if (valor == JOptionPane.YES_OPTION) {
            barra_bienpersonal.setText("");
        }
        barra_bienpersonal.setText("");
        barra_bienpersonal.setBackground(Color.white);

    }

    public void boton_transparente(JButton boton) {
        boton.setOpaque(true);
        boton.setContentAreaFilled(false);
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
            altasBienesPersonales_tabla p = new altasBienesPersonales_tabla();
            p.setVisible(true);
            this.dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barra_bienpersonal;
    private javax.swing.JButton boton_aceptar;
    private javax.swing.JButton boton_buscar1;
    private javax.swing.JLabel etiqueta_empleado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
