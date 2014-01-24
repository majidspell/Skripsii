/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.view.dialog;

import com.skripsi.majid.db.DBConnection;
import com.skripsi.majid.entity.*;
import com.skripsi.majid.service.SeleksiAkhirService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author majid
 */
public class SeleksiAkhirDialog extends javax.swing.JDialog {

    /**
     * Creates new form PembobotanDialog
     */
    private List<Sapi> sapiRecords = new ArrayList<Sapi>();
    private List<Unweight> unweightRecords = new ArrayList<Unweight>();
    private List<Weight> weightRecords = new ArrayList<Weight>();
    private List<LimitingSuperMatriks> lsmRecords = new ArrayList<LimitingSuperMatriks>();
    private List<LimitingSuperMatriks> jumlahRecords = new ArrayList<LimitingSuperMatriks>();
    DBConnection conn = DBConnection.getInstance();

    public SeleksiAkhirDialog() {
        setModal(true);
        initComponents();
        loadRecordsSeleksiAkhir();
    }

    public void muncul() {
        setTitle("Seleksi Akhir");
        setLocationRelativeTo(this);
        setVisible(true);
    }

//    public void loadRecordsSapi() {
//        try {
//            SapiService service = new SapiService(conn.getCon());
//            sapiRecords = service.getAll();
//            
//
//        } catch (SQLException ex) {
//            Logger.getLogger(SeleksiAkhirDialog.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void loadRecordsSeleksiAkhir() {
        try {
            conn.getCon().setAutoCommit(false);
            SeleksiAkhirService service = new SeleksiAkhirService(conn.getCon());

            unweightRecords = service.getAllUnWeight();
            weightRecords = service.getAllWeight();
            lsmRecords = service.getAllLsm();
            jumlahRecords = service.getJumlah();

            this.setFillTableUnWeight();
            this.setFillTableWeight();
            this.setFillTableLsm();

            conn.getCon().commit();
            conn.getCon().setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                conn.getCon().rollback();
                conn.getCon().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(SeleksiAkhirDialog.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    private void setFillTableUnWeight() {
        Object data[][] = new Object[unweightRecords.size()][7];
        int x = 0;
        for (Unweight uw : unweightRecords) {
            data[x][0] = uw.getId();
            data[x][1] = uw.getSapiId();
            data[x][2] = uw.getUmur();
            data[x][3] = uw.getBerat();
            data[x][4] = uw.getKesehatan();
            data[x][5] = uw.getJk();
            ++x;
        }
        String[] judul = {"Id", "Sapi Id", "Umur", "Berat", "Kesehatan", "Jenis Kelamin"};
        jTableUnweight.setModel(new DefaultTableModel(data, judul));
        jScrollPane1.setViewportView(jTableUnweight);
    }

    private void setFillTableWeight() {
        Object data[][] = new Object[weightRecords.size()][7];
        int x = 0;
        for (Weight w : weightRecords) {
            data[x][0] = w.getId();
            data[x][1] = w.getSapiId();
            data[x][2] = w.getUmur();
            data[x][3] = w.getBerat();
            data[x][4] = w.getKesehatan();
            data[x][5] = w.getJk();
            ++x;
        }
        String[] judul = {"Id", "Sapi Id", "Umur", "Berat", "Kesehatan", "Jenis Kelamin"};
        jTableWeight.setModel(new DefaultTableModel(data, judul));
        jScrollPane2.setViewportView(jTableWeight);
    }

    private void setFillTableLsm() {
        Object data[][] = new Object[lsmRecords.size()][8];
        int x = 0;
        for (LimitingSuperMatriks lsm : lsmRecords) {
            data[x][0] = lsm.getId();
            data[x][1] = lsm.getSapiId();
            data[x][2] = lsm.getTotalUnweight();
            data[x][3] = lsm.getTotalWeight();
            data[x][4] = lsm.getJumlah();
            data[x][5] = lsm.getStatus();
            data[x][6] = x+1;
            ++x;
        }
        String[] judul = {"Id", "Sapi Id", "UnWeight", "Weight", "Jumlah", "Status", "Peringkat"};
        jTableLsm.setModel(new DefaultTableModel(data, judul));
        jScrollPane3.setViewportView(jTableLsm);
    }

    private void setFillTablePeringkat() {
        Object data[][] = new Object[jumlahRecords.size()][4];
        int x = 0;
        for (LimitingSuperMatriks lsm : lsmRecords) {
            data[x][0] = lsm.getId();
            data[x][1] = lsm.getSapiId();
            lsm.getTotalUnweight();
            data[x][2] = lsm.getTotalUnweight();
            ++x;
        }
        String[] judul = {"Id", "Sapi Id", "UnWeight", "Weight", "Jumlah"};
        jTableLsm.setModel(new DefaultTableModel(data, judul));
        jScrollPane3.setViewportView(jTableLsm);
    }

    private void peringkat() {
        for (int i = 0; i <= jTableLsm.getRowCount(); i++) {
            int nilai[] = new int[jTableLsm.getRowCount() + 1];
            nilai[i] = (int) jTableLsm.getValueAt(i, 4);
        }

    }

    private void peringkat2() {
        for (int i = 0; i <= jTableLsm.getRowCount(); i++) {
            int nilai[] = new int[jTableLsm.getRowCount() + 1];
            nilai[i] = (int) jTableLsm.getValueAt(i, 4);
        }

    }

//    private void setFillTable() {
//        Object data[][] = new Object[sapiRecords.size()][9];
//        int x = 0;
//        for (Sapi sapi : sapiRecords) {
//            Unweight unweight = new Unweight();
//            data[x][0] = unweight.getId();
//            data[x][1] = sapi.getPemilik().getId();
//            data[x][2] = unweight.getUmur();
//            data[x][3] = unweight.getBerat();
//            data[x][4] = unweight.getKesehatan();
//            data[x][5] = unweight.getJk();
//            data[x][6] = unweight.getJumlah();
//            data[x][7] = unweight.getPrioritas();
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUnweight = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableLsm = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableWeight = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Unweight"));

        jScrollPane1.setViewportView(jTableUnweight);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Limiting Super Matriks"));

        jScrollPane3.setViewportView(jTableLsm);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Weight"));

        jScrollPane2.setViewportView(jTableWeight);

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Seleksi Akhir");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(266, 266, 266))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/skripsi/majid/resource/printerbutton.png"))); // NOI18N
        jButton1.setText("Cetak Laporan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/skripsi/majid/resource/deletebutton.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int hasil = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menghapus data ini?");
        if (hasil == JOptionPane.OK_OPTION) {

            try {
                SeleksiAkhirService service = new SeleksiAkhirService(conn.getCon());
                service.kosongkanANP();
                JOptionPane.showMessageDialog(this, "Seluruh Data Telah Dihapus!");
                loadRecordsSeleksiAkhir();
            } catch (SQLException ex) {
                Logger.getLogger(PemilikDialog.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        Map map = new HashMap();
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("sapi.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, DBConnection.getInstance().getCon());
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setTitle("Laporan Hasil Pemilihan Sapi");
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableLsm;
    private javax.swing.JTable jTableUnweight;
    private javax.swing.JTable jTableWeight;
    // End of variables declaration//GEN-END:variables
}
