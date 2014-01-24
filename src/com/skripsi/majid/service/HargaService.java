/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.service;

import com.skripsi.majid.entity.Harga;
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
public class HargaService {

    private Connection conn = null;

    public HargaService(Connection conn) {
        this.conn = conn;
    }

    public void update(Harga harga) {
        String sql = "UPDATE harga SET hargapasaran = ? WHERE id=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, harga.getHargapasaran());
            st.setInt(2, harga.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HargaService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public List<Harga> getAll() {
        List<Harga> list = new ArrayList<Harga>();
        String sql = "SELECT * FROM harga";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Harga harga = new Harga();
                harga.setId(rs.getInt("id"));
                harga.setHargapasaran(rs.getInt("hargapasaran"));
                list.add(harga);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HargaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
