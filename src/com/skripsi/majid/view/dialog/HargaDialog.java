/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.view.dialog;

import com.skripsi.majid.db.DBConnection;
import com.skripsi.majid.entity.Harga;
import com.skripsi.majid.entity.Pemilik;
import com.skripsi.majid.service.HargaService;
import com.skripsi.majid.service.PemilikService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author majid
 */
public class HargaDialog extends javax.swing.JDialog {

    private Harga harga;
    private List<Harga> hargaRecords = new ArrayList<Harga>();

    public HargaDialog() {
        setModal(true);
        initComponents();
        loadRecords();

         jTableHarga.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jTableHarga.getSelectedRow() != -1) {
                    int index = jTableHarga.getSelectedRow();
                    String harga = String.valueOf(jTableHarga.getValueAt(index, 1));
                    
                    jTextFieldHarga.setText(harga);
                }
            }
        });
    }

    public void muncul() {
        setTitle("Harga PerKg");
        setLocationRelativeTo(this);
        setVisible(true);
    }

    public void loadRecords() {
        try {
            DBConnection conn = DBConnection.getInstance();
            HargaService service = new HargaService(conn.getCon());
            hargaRecords = service.getAll();
            setClearTextField();
            this.setFillTable();
        } catch (SQLException ex) {
            Logger.getLogger(PemilikService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   private void setFillTable() {
        Object data[][] = new Object[hargaRecords.size()][3];
        int x = 0;
        for (Harga harga : hargaRecords) {
            data[x][0] = harga.getId();
            data[x][1] = harga.getHargapasaran();;
            ++x;
        }
        String[] judul = {"Id", "HargaPasaran"};
        jTableHarga.setModel(new DefaultTableModel(data, judul));
        jScrollPane1.setViewportView(jTableHarga);
    }

    private void setClearTextField() {
        jTextFieldHarga.setText("");
    }

    private void jButtonUbahActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTableHarga.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih salah satu baris");
        } else {
            int index = jTableHarga.getSelectedRow();
            int id = (int) jTableHarga.getValueAt(index, 0);

            Harga harga = new Harga();
            harga.setId(id);
            harga.setHargapasaran(Integer.valueOf(jTextFieldHarga.getText()));

            try {
                DBConnection conn = DBConnection.getInstance();
                HargaService service = new HargaService(conn.getCon());
                service.update(harga);
                JOptionPane.showMessageDialog(this, "Data Telah Diubah!");
                loadRecords();
            } catch (SQLException ex) {
                Logger.getLogger(PemilikDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldHarga = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHarga = new javax.swing.JTable();
        jButtonUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Harga Pasaran");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(29, 29, 29))
        );

        jLabel2.setText("Harga :");

        jTableHarga.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableHarga);

        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldHarga)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jButtonUpdate))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonUpdate)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        if (jTableHarga.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih salah satu baris");
        } else {
            int index = jTableHarga.getSelectedRow();
            int id = (int) jTableHarga.getValueAt(index, 0);

            Harga harga = new Harga();
            harga.setId(id);
            harga.setHargapasaran(Integer.valueOf(jTextFieldHarga.getText()));

            try {
                DBConnection conn = DBConnection.getInstance();
                HargaService service = new HargaService(conn.getCon());
                service.update(harga);
                JOptionPane.showMessageDialog(this, "Data Telah Diubah!");
                loadRecords();
            } catch (SQLException ex) {
                Logger.getLogger(PemilikDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButtonUpdateActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableHarga;
    private javax.swing.JTextField jTextFieldHarga;
    // End of variables declaration//GEN-END:variables
}
