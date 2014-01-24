/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.view.dialog;

import com.skripsi.majid.db.DBConnection;
import com.skripsi.majid.entity.NilaiVa;
import com.skripsi.majid.entity.NilaiVbCiCr;
import com.skripsi.majid.entity.Sapi;
import com.skripsi.majid.entity.TableAwal;
import com.skripsi.majid.service.SapiService;
import com.skripsi.majid.service.TableAwalService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author majid
 */
public class PembobotanDialog extends javax.swing.JDialog {

    /**
     * Creates new form PembobotanDialog
     */
    private List<TableAwal> tableAwalRecords = new ArrayList<TableAwal>();
    private List<NilaiVa> vaRecords = new ArrayList<NilaiVa>();
    private List<NilaiVbCiCr> vbcicrRecords = new ArrayList<NilaiVbCiCr>();
    DBConnection conn = DBConnection.getInstance();

    public PembobotanDialog() {
        setModal(true);
        initComponents();
        loadRecordsTableAwal();
    }

    public void muncul() {
        setTitle("Konsep Matriks");
        setLocationRelativeTo(this);
        setVisible(true);
    }

    public void loadRecordsTableAwal() {
        try {
            conn.getCon().setAutoCommit(false);
            TableAwalService service = new TableAwalService(conn.getCon());


            tableAwalRecords = service.getAllTawal();
            vaRecords = service.getAllVa();
            vbcicrRecords = service.getAllVbCiCr();

            this.setFillTablePembobotan();
            this.setFillTableVa();
            this.setFillTableVbCiCr();

            conn.getCon().commit();
            conn.getCon().setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                conn.getCon().rollback();
                conn.getCon().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(PembobotanDialog.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    private void setFillTablePembobotan() {
        Object data[][] = new Object[tableAwalRecords.size()][9];
        int x = 0;
        for (TableAwal ta : tableAwalRecords) {
            data[x][0] = ta.getId();
            data[x][1] = ta.getSapiId();
            data[x][2] = ta.getUmur();
            data[x][3] = ta.getBerat();
            data[x][4] = ta.getKesehatan();
            data[x][5] = ta.getJk();
            data[x][6] = ta.getJumlah();
            data[x][7] = ta.getPrioritas();
            ++x;
        }
        String[] judul = {"Id", "Sapi Id", "Umur", "Berat", "Kesehatan", "Jenis Kelamin", "Jumlah", "Prioritas"};
        jTablePembobotan.setModel(new DefaultTableModel(data, judul));
        jScrollPane1.setViewportView(jTablePembobotan);
    }

    private void setFillTableVa() {
        Object data[][] = new Object[vaRecords.size()][7];
        int x = 0;
        for (NilaiVa va : vaRecords) {
            data[x][0] = va.getId();
            data[x][1] = va.getSapiId();
            data[x][2] = va.getUmur();
            data[x][3] = va.getBerat();
            data[x][4] = va.getKesehatan();
            data[x][5] = va.getJk();
            ++x;
        }
        String[] judul = {"Id", "Sapi Id", "Umur", "Berat", "Kesehatan", "Jenis Kelamin"};
        jTableVa.setModel(new DefaultTableModel(data, judul));
        jScrollPane2.setViewportView(jTableVa);
    }

    private void setFillTableVbCiCr() {
        Object data[][] = new Object[vbcicrRecords.size()][10];
        int x = 0;
        for (NilaiVbCiCr nilaiVbCiCr : vbcicrRecords) {
            data[x][0] = nilaiVbCiCr.getId();
            data[x][1] = nilaiVbCiCr.getSapiId();
            data[x][2] = nilaiVbCiCr.getUmur();
            data[x][3] = nilaiVbCiCr.getBerat();
            data[x][4] = nilaiVbCiCr.getKesehatan();
            data[x][5] = nilaiVbCiCr.getJk();
            data[x][6] = nilaiVbCiCr.getJumlah();
            data[x][7] = nilaiVbCiCr.getCi();
            data[x][8] = nilaiVbCiCr.getCr();
            ++x;
        }
        String[] judul = {"Id", "Sapi Id", "Umur", "Berat", "Kesehatan", "Jenis Kelamin", "Jumlah", "CI", "CR"};
        jTableVbCICr.setModel(new DefaultTableModel(data, judul));
        jScrollPane3.setViewportView(jTableVbCICr);
    }

//    private void setFillTable() {
//        Object data[][] = new Object[sapiRecords.size()][9];
//        int x = 0;
//        for (Sapi sapi : sapiRecords) {
//            TableAwal tableAwal = new TableAwal();
//            data[x][0] = tableAwal.getId();
//            data[x][1] = sapi.getPemilik().getId();
//            data[x][2] = tableAwal.getUmur();
//            data[x][3] = tableAwal.getBerat();
//            data[x][4] = tableAwal.getKesehatan();
//            data[x][5] = tableAwal.getJk();
//            data[x][6] = tableAwal.getJumlah();
//            data[x][7] = tableAwal.getPrioritas();
//
//            ++x;
//        }
//        String[] judul = {"Id", "Sapi Id", "Umur", "Berat", "Kesehatan", "Jenis Kelamin", "Jumlah", "Prioritas"};
//        jTablePembobotan.setModel(new DefaultTableModel(data, judul));
//        jScrollPane1.setViewportView(jTablePembobotan);
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePembobotan = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVbCICr = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVa = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonNext = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pembobotan"));

        jScrollPane1.setViewportView(jTablePembobotan);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(217, 217, 217))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Nilai VB"));

        jScrollPane3.setViewportView(jTableVbCICr);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Nilai VA"));

        jScrollPane2.setViewportView(jTableVa);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Konsep Matriks");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jButtonNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/skripsi/majid/resource/nextbutton.png"))); // NOI18N
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        // TODO add your handling code here:
        dispose();
        SeleksiAkhirDialog dialog = new SeleksiAkhirDialog();
        dialog.muncul();
    }//GEN-LAST:event_jButtonNextActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTablePembobotan;
    private javax.swing.JTable jTableVa;
    private javax.swing.JTable jTableVbCICr;
    // End of variables declaration//GEN-END:variables
}
