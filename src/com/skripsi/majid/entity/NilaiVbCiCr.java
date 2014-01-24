/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.entity;

/**
 *
 * @author majid
 */
public class NilaiVbCiCr {
    private int id;
    private int sapiId;
    private float berat;
    private float umur;
    private float kesehatan;
    private float jk;
    private float jumlah;
    private float ci;
    private float cr;

    public float getBerat() {
        return berat;
    }

    public void setBerat(float berat) {
        this.berat = berat;
    }

    public float getCi() {
        return ci;
    }

    public void setCi(float ci) {
        this.ci = ci;
    }

    public float getCr() {
        return cr;
    }

    public void setCr(float cr) {
        this.cr = cr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getJk() {
        return jk;
    }

    public void setJk(float jk) {
        this.jk = jk;
    }

    public float getJumlah() {
        return jumlah;
    }

    public void setJumlah(float jumlah) {
        this.jumlah = jumlah;
    }

    public float getKesehatan() {
        return kesehatan;
    }

    public void setKesehatan(float kesehatan) {
        this.kesehatan = kesehatan;
    }

    public int getSapiId() {
        return sapiId;
    }

    public void setSapiId(int sapiId) {
        this.sapiId = sapiId;
    }

    public float getUmur() {
        return umur;
    }

    public void setUmur(float umur) {
        this.umur = umur;
    }
}
