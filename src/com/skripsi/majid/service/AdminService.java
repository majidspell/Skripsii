/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.service;

import com.skripsi.majid.entity.Admin;
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
public class AdminService {

    private Connection conn = null;

    public AdminService(Connection conn) {
        this.conn = conn;
    }

    public Admin login(String nama, String password) {
        Admin admin = null;
        String sql = "SELECT * FROM admin WHERE nama = ? and password = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nama);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setNama(rs.getString("nama"));
                admin.setPassword(rs.getString("password"));
                admin.setJabatan(rs.getString("jabatan"));
                admin.setAlamat(rs.getString("alamat"));
                admin.setTelepon(rs.getString("telepon"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PemilikService.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return admin;
        }
    }
}
