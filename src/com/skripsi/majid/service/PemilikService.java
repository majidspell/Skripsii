/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.service;

import com.skripsi.majid.entity.Pemilik;
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
public class PemilikService {

    private Connection conn = null;

    public PemilikService(Connection conn) {
        this.conn = conn;
    }

    public void insert(Pemilik pemilik) {
        String sql = "INSERT INTO pemilik(id, namapemilik,alamat,tanggal, telepon) VALUES(null,?,?,?,?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, pemilik.getNamaPemilik());
            st.setString(2, pemilik.getAlamat());
            st.setDate(3, new java.sql.Date(pemilik.getTanggal().getTime()));
            st.setString(4, pemilik.getTelepon());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PemilikService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(Pemilik pemilik) {
        String sql = "UPDATE pemilik SET namapemilik = ?, alamat = ?, tanggal = ?, telepon = ? WHERE id=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, pemilik.getNamaPemilik());
            st.setString(2, pemilik.getAlamat());
            st.setDate(3, new java.sql.Date(pemilik.getTanggal().getTime()));
            st.setString(4, pemilik.getTelepon());
            st.setInt(5, pemilik.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PemilikService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(int id) {
        String sql = "DELETE FROM pemilik WHERE id=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PemilikService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Pemilik> getAll() {
        List<Pemilik> list = new ArrayList<Pemilik>();
        String sql = "SELECT * FROM pemilik";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pemilik pemilik = new Pemilik();
                pemilik.setId(rs.getInt("id"));
                pemilik.setNamaPemilik(rs.getString("namapemilik"));
                pemilik.setAlamat(rs.getString("alamat"));
                pemilik.setTanggal(rs.getDate("tanggal"));
                pemilik.setTelepon(rs.getString("telepon"));
                list.add(pemilik);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PemilikService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Pemilik selectById(int id) {
        Pemilik pemilik = null;
        String sql = "SELECT * FROM pemilik WHERE id=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                pemilik = new Pemilik();
                pemilik.setId(rs.getInt("id"));
                pemilik.setNamaPemilik(rs.getString("namapemilik"));
                pemilik.setAlamat(rs.getString("alamat"));
                pemilik.setTanggal(rs.getDate("tanggal"));
                pemilik.setTelepon(rs.getString("telepon"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PemilikService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pemilik;
    }
}
