/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.entity;

import java.util.Date;

/**
 *
 * @author majid
 */
public class Pemilik {

    int id;
    String namaPemilik;
    String alamat;
    Date tanggal;

    @Override
    public String toString() {
        return id +". "+ namaPemilik;
    }
    
    String telepon;
    
     public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    
}
