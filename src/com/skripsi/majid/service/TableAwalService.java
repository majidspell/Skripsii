/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.service;

import com.skripsi.majid.db.DBConnection;
import com.skripsi.majid.entity.*;
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
public class TableAwalService {

    private Connection conn = null;

    public TableAwalService(Connection conn) {
        this.conn = conn;
    }

    public int getHarga() {
        int harga=0;
        String sql = "SELECT hargapasaran FROM harga WHERE id=1";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
             if (rs.next()) {
                harga = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableAwalService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return harga;
    }

    
    public List<TableAwal> getAllTawal() {
        List<TableAwal> list = new ArrayList<TableAwal>();
        String sql = "SELECT * FROM tableawal";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TableAwal ta = new TableAwal();
                ta.setId(rs.getInt("id"));
                ta.setSapiId(rs.getInt("sapiid"));
                ta.setUmur(rs.getFloat("umur"));
                ta.setBerat(rs.getFloat("berat"));
                ta.setKesehatan(rs.getFloat("kesehatan"));
                ta.setJk(rs.getFloat("jk"));
                ta.setJumlah(rs.getFloat("jumlah"));
                ta.setPrioritas(rs.getFloat("prioritas"));
                list.add(ta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableAwalService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<NilaiVa> getAllVa() {
        List<NilaiVa> list = new ArrayList<NilaiVa>();
        String sql = "SELECT * FROM va";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NilaiVa va = new NilaiVa();
                va.setId(rs.getInt("id"));
                va.setSapiId(rs.getInt("sapiid"));
                va.setUmur(rs.getFloat("umur"));
                va.setBerat(rs.getFloat("berat"));
                va.setKesehatan(rs.getFloat("kesehatan"));
                va.setJk(rs.getFloat("jk"));
                list.add(va);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableAwalService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<NilaiVbCiCr> getAllVbCiCr() {
        List<NilaiVbCiCr> list = new ArrayList<NilaiVbCiCr>();
        String sql = "SELECT * FROM vbcicr";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NilaiVbCiCr vbCiCr = new NilaiVbCiCr();
                vbCiCr.setId(rs.getInt("id"));
                vbCiCr.setSapiId(rs.getInt("sapiid"));
                vbCiCr.setUmur(rs.getFloat("umur"));
                vbCiCr.setBerat(rs.getFloat("berat"));
                vbCiCr.setKesehatan(rs.getFloat("kesehatan"));
                vbCiCr.setJk(rs.getFloat("jk"));
                vbCiCr.setJumlah(rs.getFloat("jumlah"));
                vbCiCr.setCi(rs.getFloat("ci"));
                vbCiCr.setCr(rs.getFloat("cr"));
                list.add(vbCiCr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableAwalService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void insertVa(NilaiVa va) {
        String sql = "INSERT INTO va(id, sapiid, umur, berat, kesehatan, jk) VALUES(null,?,?,?,?,?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, va.getSapiId());
            st.setFloat(2, va.getUmur());
            st.setFloat(3, va.getBerat());
            st.setFloat(4, va.getKesehatan());
            st.setFloat(5, va.getJk());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TableAwalService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertVbCiCr(NilaiVbCiCr nilaiVbCiCr) {
        String sql = "INSERT INTO vbcicr(id, sapiid, umur, berat, kesehatan, jk, jumlah, ci, cr) VALUES(null,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, nilaiVbCiCr.getSapiId());
            st.setFloat(2, nilaiVbCiCr.getUmur());
            st.setFloat(3, nilaiVbCiCr.getBerat());
            st.setFloat(4, nilaiVbCiCr.getKesehatan());
            st.setFloat(5, nilaiVbCiCr.getJk());
            st.setFloat(6, nilaiVbCiCr.getJumlah());
            st.setFloat(7, nilaiVbCiCr.getCi());
            st.setFloat(8, nilaiVbCiCr.getCr());

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TableAwalService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertUnweight(Unweight u) {
        String sql = "INSERT INTO unweight(id, sapiid, umur, berat, kesehatan, jk) VALUES(null,?,?,?,?,?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, u.getSapiId());
            st.setFloat(2, u.getUmur());
            st.setFloat(3, u.getBerat());
            st.setFloat(4, u.getKesehatan());
            st.setFloat(5, u.getJk());

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TableAwalService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertWeight(Weight w) {
        String sql = "INSERT INTO weight(id, sapiid, umur, berat, kesehatan, jk) VALUES(null,?,?,?,?,?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, w.getSapiId());
            st.setFloat(2, w.getUmur());
            st.setFloat(3, w.getBerat());
            st.setFloat(4, w.getKesehatan());
            st.setFloat(5, w.getJk());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TableAwalService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void insertLimSupMatriks(LimitingSuperMatriks lsm) {
        String sql = "INSERT INTO lsm(id, sapiid, totalunwe, totalwe, jumlah, status) VALUES(null,?,?,?,?,?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, lsm.getSapiId());
            st.setFloat(2, lsm.getTotalUnweight());
            st.setFloat(3, lsm.getTotalWeight());
            st.setFloat(4, lsm.getJumlah());
            st.setString(5, lsm.getStatus());

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TableAwalService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
