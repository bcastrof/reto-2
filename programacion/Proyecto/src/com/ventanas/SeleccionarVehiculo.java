/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ventanas;

import com.clases.Logistica;
import com.clases.Conducen;
import com.clases.Parte;
import com.clases.Trabajador;
import com.clases.Vehiculo;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SeleccionarVehiculo extends javax.swing.JFrame {

    /**
     * Variable que almacena la id del trabajador, el valor es obtenido desde
     * login.
     */
    private BigDecimal idT = Login.idt;
    /**
     * Moelado de la tabla vehiculos.
     */
    private DefaultTableModel vehiculos;
    /**
     * Array que va a contener la informacion de los vehiculos recuperados de la
     * base datos.
     */
    private List<Vehiculo> vehiculo;
    /**
     * Obejeto de la clase vehiculo que va a contener los datos del vehiculo un
     * vez aplicado el filtro.
     */
    private Vehiculo v;
    /**
     * Obejeto tipo Viajes(ventana).
     */
    private Viajes viaje;
    /**
     * Variable que va almacenar la matricula del vehiculo.
     */
    private String docI;

    public SeleccionarVehiculo() {
        initComponents();
        listarVehiculos();
    }

    /**
     * Metodo que recibe un array list que es el encargado de volcar los
     * diferentes resultados en la tabla de la ventana.
     */
    private void listarVehiculos() {
        vehiculos = (DefaultTableModel) jTable1.getModel();
        vehiculo = Vehiculo.listarVehiculos();
        vehiculo.forEach((v) -> {
            vehiculos.insertRow(vehiculos.getRowCount(), new Object[]{v.getIdVehiculo(), v.getMarca(),
                v.getModelo(), v.getMatricula()});
        });
    }

    /**
     * Metodo que recibe un objeto de la clase vehiculo que es que se va enseñar
     * en la tabla una vez aplicado el filtro, el filtrado se realiza utilzando
     * la variable docI
     */
    private void filtrarVehiculo() {
        vehiculos.setRowCount(0);
        vehiculos = (DefaultTableModel) jTable1.getModel();
        v = Vehiculo.filtrarVehiculo(docI);

        vehiculos.insertRow(vehiculos.getRowCount(), new Object[]{v.getIdVehiculo(),
            v.getMarca(), v.getModelo(), v.getMatricula()});

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        uIdV = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        seleccionar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Selección de Vehiculo");

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 24)); // NOI18N
        jLabel1.setText("SELECCIONE EL VEHICULO QUE VA UTILIZAR");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MARCA", "MODELO", "MATRICULA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        buscar.setText("BUSCAR");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        seleccionar.setText("SELECCIONAR");
        seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarActionPerformed(evt);
            }
        });

        jLabel2.setText("Id Vehiculo: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(uIdV, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(seleccionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uIdV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscar)
                    .addComponent(seleccionar)
                    .addComponent(jLabel2))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Envento de seleccion de vehiculos, con el cual selecciono la posicion del
     * vehiculo y extraigo los datos que necesita bien para su busqueda, como
     * para la insecion de los datos en la tabla conducen.
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int index = jTable1.getSelectedRow();
        uIdV.setText(vehiculo.get(index).getIdVehiculo().toString());

    }//GEN-LAST:event_jTable1MouseClicked
    /**
     *
     * Evento que recoge el valor de uIdV y lo almacena en doCI.
     */
    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed

        docI = uIdV.getText();
        filtrarVehiculo();
    }//GEN-LAST:event_buscarActionPerformed
    /**
     *
     * Evento que se ocupa de insertar en la tabla conducen el vehiculo, fecha,
     * id trabajador en la tabla conducen de la base datos.
     */

    private void seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarActionPerformed

        SimpleDateFormat cfecha = new SimpleDateFormat("dd/MM/yyyy");
        Date ccfecha = new Date();
        String fechacc = cfecha.format(ccfecha);
        Conducen conducen = new Conducen(new BigDecimal(uIdV.getText()), fechacc, idT);

        if (conducen.insertarConducen() == true) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = new Date();
            String fechaParte = dateFormat.format(fecha);
            Parte p = new Parte(fechaParte, idT);
            p.iniciarParte();
            Logistica t = (Logistica) Trabajador.filtrarTrabajador2(idT);
            conducen.añadirLogistica(t);
            t.añadirVehiculo(conducen);
            viaje = new Viajes();
            viaje.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Vehiculo incorrecto, por favor vuelva a seleccionar", "Atención", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_seleccionarActionPerformed

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
            java.util.logging.Logger.getLogger(SeleccionarVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionarVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionarVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionarVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeleccionarVehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton seleccionar;
    private javax.swing.JTextField uIdV;
    // End of variables declaration//GEN-END:variables
}
