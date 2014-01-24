/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.service;

import com.skripsi.majid.entity.LimitingSuperMatriks;
import com.skripsi.majid.entity.Unweight;
import com.skripsi.majid.entity.Weight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author majid
 */
public class SeleksiAkhirService {

    private Connection conn = null;

    public SeleksiAkhirService(Connection conn) {
        this.conn = conn;
    }

    public List<Unweight> getAllUnWeight() {
        List<Unweight> list = new ArrayList<Unweight>();
        String sql = "SELECT * FROM unweight";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Unweight unweight = new Unweight();
                unweight.setId(rs.getInt("id"));
                unweight.setSapiId(rs.getInt("sapiid"));
                unweight.setUmur(rs.getFloat("umur"));
                unweight.setBerat(rs.getFloat("berat"));
                unweight.setKesehatan(rs.getFloat("kesehatan"));
                unweight.setJk(rs.getFloat("jk"));
                list.add(unweight);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeleksiAkhirService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Weight> getAllWeight() {
        List<Weight> list = new ArrayList<Weight>();
        String sql = "SELECT * FROM weight";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Weight weight = new Weight();
                weight.setId(rs.getInt("id"));
                weight.setSapiId(rs.getInt("sapiid"));
                weight.setUmur(rs.getFloat("umur"));
                weight.setBerat(rs.getFloat("berat"));
                weight.setKesehatan(rs.getFloat("kesehatan"));
                weight.setJk(rs.getFloat("jk"));
                list.add(weight);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeleksiAkhirService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<LimitingSuperMatriks> getAllLsm() {
        List<LimitingSuperMatriks> list = new ArrayList<LimitingSuperMatriks>();
        String sql = "SELECT id, sapiid, totalunwe, totalwe, jumlah, status FROM lsm ORDER BY jumlah DESC";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LimitingSuperMatriks lsm = new LimitingSuperMatriks();
                lsm.setId(rs.getInt("id"));
                lsm.setSapiId(rs.getInt("sapiid"));
                lsm.setTotalUnweight(rs.getFloat("totalunwe"));
                lsm.setTotalWeight(rs.getFloat("totalwe"));
                lsm.setJumlah(rs.getFloat("jumlah"));
                lsm.setStatus(rs.getString("status"));
                list.add(lsm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeleksiAkhirService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public List<LimitingSuperMatriks> getJumlah() {
        List<LimitingSuperMatriks> list = new ArrayList<LimitingSuperMatriks>();
        String sql = "SELECT id, sapiid, jumlah FROM lsm";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LimitingSuperMatriks lsm = new LimitingSuperMatriks();
                lsm.setId(rs.getInt("id"));
                lsm.setSapiId(rs.getInt("sapiid"));
                lsm.setJumlah(rs.getFloat("jumlah"));
                list.add(lsm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeleksiAkhirService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void kosongkanANP() {
        String sql1 = "TRUNCATE TABLE sapiterpilih";
        String sql2 = "TRUNCATE TABLE tableawal";
        String sql3 = "TRUNCATE TABLE va";
        String sql4 = "TRUNCATE TABLE vbcicr";
        String sql5 = "TRUNCATE TABLE unweight";
        String sql6 = "TRUNCATE TABLE weight";
        String sql7 = "TRUNCATE TABLE lsm";

        try {
            conn.setAutoCommit(false);
            PreparedStatement st1 = conn.prepareStatement(sql1);
            PreparedStatement st2 = conn.prepareStatement(sql2);
            PreparedStatement st3 = conn.prepareStatement(sql3);
            PreparedStatement st4 = conn.prepareStatement(sql4);
            PreparedStatement st5 = conn.prepareStatement(sql5);
            PreparedStatement st6 = conn.prepareStatement(sql6);
            PreparedStatement st7 = conn.prepareStatement(sql7);

            st1.executeUpdate();
            st2.executeUpdate();
            st3.executeUpdate();
            st4.executeUpdate();
            st5.executeUpdate();
            st6.executeUpdate();
            st7.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(SeleksiAkhirService.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }
//    public void insertVa(NilaiVa va) {
//        String sql = "INSERT INTO va(id, sapiid, umur, berat, kesehatan, jk) VALUES(null,?,?,?,?,?)";
//        try {
//            PreparedStatement st = conn.prepareStatement(sql);
//            st.setInt(1, va.getSapiId());
//            st.setFloat(2, va.getUmur());
//            st.setFloat(3, va.getBerat());
//            st.setFloat(4, va.getKesehatan());
//            st.setFloat(5, va.getJk());
//            st.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(SeleksiAkhirService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void insertVbCiCr(NilaiVbCiCr nilaiVbCiCr) {
//        String sql = "INSERT INTO vbcicr(id, sapiid, umur, berat, kesehatan, jk, jumlah, ci, cr) VALUES(null,?,?,?,?,?,?,?,?)";
//        try {
//            PreparedStatement st = conn.prepareStatement(sql);
//            st.setInt(1, nilaiVbCiCr.getSapiId());
//            st.setFloat(2, nilaiVbCiCr.getUmur());
//            st.setFloat(3, nilaiVbCiCr.getBerat());
//            st.setFloat(4, nilaiVbCiCr.getKesehatan());
//            st.setFloat(5, nilaiVbCiCr.getJk());
//            st.setFloat(6, nilaiVbCiCr.getJumlah());
//            st.setFloat(7, nilaiVbCiCr.getCi());
//            st.setFloat(8, nilaiVbCiCr.getCr());
//
//            st.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(SeleksiAkhirService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void insertUnweight(Unweight u) {
//        String sql = "INSERT INTO unweight(id, sapiid, umur, berat, kesehatan, jk, jumlah) VALUES(null,?,?,?,?,?,?)";
//        try {
//            PreparedStatement st = conn.prepareStatement(sql);
//            st.setInt(1, u.getSapiId());
//            st.setFloat(2, u.getUmur());
//            st.setFloat(3, u.getBerat());
//            st.setFloat(4, u.getKesehatan());
//            st.setFloat(5, u.getJk());
//            st.setFloat(6, u.getJumlah());
//
//            st.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(SeleksiAkhirService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void insertWeight(Weight w) {
//        String sql = "INSERT INTO weight(id, sapiid, umur, berat, kesehatan, jk, jumlah) VALUES(null,?,?,?,?,?,?)";
//        try {
//            PreparedStatement st = conn.prepareStatement(sql);
//            st.setInt(1, w.getSapiId());
//            st.setFloat(2, w.getUmur());
//            st.setFloat(3, w.getBerat());
//            st.setFloat(4, w.getKesehatan());
//            st.setFloat(5, w.getJk());
//            st.setFloat(6, w.getJumlah());
//
//            st.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(SeleksiAkhirService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void insertLimSupMatriks(LimitingSuperMatriks lsm) {
//        String sql = "INSERT INTO lsm(id, sapiid, totalunwe, totalwe, jumlah) VALUES(null,?,?,?,?)";
//        try {
//            PreparedStatement st = conn.prepareStatement(sql);
//            st.setInt(1, lsm.getSapiId());
//            st.setFloat(2, lsm.getTotalUnweight());
//            st.setFloat(3, lsm.getTotalWeight());
//            st.setFloat(4, lsm.getJumlah());
//
//            st.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(SeleksiAkhirService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
