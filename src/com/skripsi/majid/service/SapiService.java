/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.service;

import com.skripsi.majid.db.DBConnection;
import com.skripsi.majid.entity.Pemilik;
import com.skripsi.majid.entity.Sapi;
import com.skripsi.majid.entity.SapiTerpilih;
import com.skripsi.majid.entity.TableAwal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author majid
 */
public class SapiService {

    private Connection conn = null;

    public SapiService(Connection conn) {
        this.conn = conn;
    }

    public String getAutoNumber() throws SQLException {
        String no = "";
        try {
            String sql = "SELECT MAX(id) FROM sapi";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if (rs.first() == false) {
                    no = "1";
                } else {
                    rs.last();
                    int noSapi = rs.getInt(1);
                    no = String.valueOf(noSapi + 1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return no;
    }

    public int getMax() throws SQLException {
        int no = 0;
        try {
            String sql = "SELECT MAX(right(id,1)) AS no FROM sapi";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if (rs.first() == false) {
                    System.out.println("data kosong");
                } else {
                    rs.last();
                    no = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return no;
    }

    public int getMaxSapiTerpilih() throws SQLException {
        int no = 0;
        try {
            String sql = "SELECT MAX(id) FROM sapiterpilih";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if (rs.first() == false) {
                    System.out.println("data kosong");
                } else {
                    rs.last();
                    no = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return no;
    }

    public int cekData(int sapiId) {
        int no = 0;
        try {
            String ids = String.valueOf(sapiId + 1);
            String sql = "SELECT sapiid AS no FROM sapiterpilih WHERE sapiid='" + ids + "' ";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                no = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return no;
    }

    public int getBeratSapi(int sapiId) {
        int no = 0;
        try {
            String ids = String.valueOf(sapiId);
            String sql = "SELECT berat FROM sapi WHERE id='" + ids + "' ";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                no = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return no;
    }

    public List<Sapi> getBeratSapiAll() {
        List<Sapi> list = new ArrayList<Sapi>();
        for (int i=0;i<list.size();i++) {
            String sql = "SELECT berat FROM sapi WHERE id="+i+"";
            try {
                PreparedStatement st = conn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Sapi sapi = new Sapi();
                    sapi.setBerat(rs.getFloat("berat"));
                    list.add(sapi);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public void insert(Sapi sapi) {
        String sql = "INSERT INTO sapi(id, pemilikid, umur, berat, kesehatan, jk) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, sapi.getId());
            st.setInt(2, sapi.getPemilik().getId());
            st.setString(3, sapi.getUmur());
            st.setFloat(4, sapi.getBerat());
            st.setString(5, sapi.getKesehatan());
            st.setString(6, sapi.getJk());
            st.setFloat(7, sapi.getHarga());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertSapiTerpilih(SapiTerpilih sapiTerpilih) {
        String sql = "INSERT INTO sapiterpilih(id, sapiid, umur, berat, kesehatan, jk) VALUES(null,?,?,?,?,?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, sapiTerpilih.getSapiid());
            st.setFloat(2, sapiTerpilih.getUmur());
            st.setFloat(3, sapiTerpilih.getBerat());
            st.setFloat(4, sapiTerpilih.getKesehatan());
            st.setFloat(5, sapiTerpilih.getJk());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertTawal(TableAwal ta) {
        String sql = "INSERT INTO tableawal(id, sapiid, umur, berat, kesehatan, jk, jumlah, prioritas) VALUES(null,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, ta.getSapiId());
            st.setFloat(2, ta.getUmur());
            st.setFloat(3, ta.getBerat());
            st.setFloat(4, ta.getKesehatan());
            st.setFloat(5, ta.getJk());
            st.setFloat(6, ta.getJumlah());
            st.setFloat(7, ta.getPrioritas());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Sapi> getAll() {
        List<Sapi> list = new ArrayList<Sapi>();
        String sql = "SELECT * FROM sapi";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Sapi sapi = new Sapi();
                sapi.setId(rs.getInt("id"));

                int idPemilik = rs.getInt("pemilikid");
                Pemilik pemilik = DBConnection.getPemilikService().selectById(idPemilik);
                sapi.setPemilik(pemilik);

                sapi.setUmur(rs.getString("umur"));
                sapi.setBerat(rs.getFloat("berat"));
                sapi.setKesehatan(rs.getString("kesehatan"));
                sapi.setJk(rs.getString("jk"));
                list.add(sapi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<SapiTerpilih> getAllSapiTerpilih() {
        List<SapiTerpilih> list = new ArrayList<SapiTerpilih>();
        String sql = "SELECT * FROM sapiterpilih";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SapiTerpilih sapiTerpilih = new SapiTerpilih();
                sapiTerpilih.setId(rs.getInt("id"));
                sapiTerpilih.setSapiid(rs.getInt("sapiid"));
                sapiTerpilih.setUmur(rs.getFloat("umur"));
                sapiTerpilih.setBerat(rs.getFloat("berat"));
                sapiTerpilih.setKesehatan(rs.getFloat("kesehatan"));
                sapiTerpilih.setJk(rs.getFloat("jk"));
                list.add(sapiTerpilih);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Float jumlahUmurTerpilih() {
        float jmlUmur = 0;
        String sql = "SELECT SUM(umur) FROM sapiterpilih";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                jmlUmur = rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PemilikService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jmlUmur;
    }

    public Float jumlahBeratTerpilih() {
        float jmlBerat = 0;
        String sql = "SELECT SUM(berat) FROM sapiterpilih";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                jmlBerat = rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PemilikService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jmlBerat;
    }

    public Float jumlahKesehatanTerpilih() {
        float jmlKesehatan = 0;
        String sql = "SELECT SUM(kesehatan) FROM sapiterpilih";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                jmlKesehatan = rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PemilikService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jmlKesehatan;
    }

    public Float jumlahJkTerpilih() {
        float jmlJk = 0;
        String sql = "SELECT SUM(jk) FROM sapiterpilih";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                jmlJk = rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PemilikService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jmlJk;
    }

    public List<Sapi> getKesehatan() {
        List<Sapi> list = new ArrayList<Sapi>();
        String sql = "SELECT kesehatan FROM sapi";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Sapi sapi = new Sapi();
                sapi.setKesehatan(rs.getString("kesehatan"));
                list.add(sapi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void delete(int id) {
        String sql = "DELETE FROM sapi WHERE id=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void kosongkan() {
        String sql = "TRUNCATE TABLE sapiterpilih";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//    public List<Sapi> getKesehatan() {
//        List<Sapi> list = new ArrayList<Sapi>();
//        String sql = "SELECT kesehatan FROM sapi";
//        try {
//            PreparedStatement st = conn.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                Sapi sapi = new Sapi();
//                sapi.setKesehatan(rs.getFloat("kesehatan"));
//                list.add(sapi);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
//    
    //    public void update(Pemilik pemilik) {
//        String sql = "UPDATE pemilik SET namapemilik = ?, alamat = ?, tanggal = ?, telepon = ? WHERE id=?";
//        try {
//            PreparedStatement st = conn.prepareStatement(sql);
//            st.setString(1, pemilik.getNamaPemilik());
//            st.setString(2, pemilik.getAlamat());
//            st.setDate(3, new java.sql.Date(pemilik.getTanggal().getTime()));
//            st.setString(4, pemilik.getTelepon());
//            st.setInt(5, pemilik.getId());
//            st.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(SapiService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
}
