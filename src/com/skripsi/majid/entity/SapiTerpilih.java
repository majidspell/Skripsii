/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.entity;

/**
 *
 * @author majid
 */
public class SapiTerpilih {
    private int id;
    private int sapiid;

    public float getBerat() {
        return berat;
    }

    public void setBerat(float berat) {
        this.berat = berat;
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

    public float getKesehatan() {
        return kesehatan;
    }

    public void setKesehatan(float kesehatan) {
        this.kesehatan = kesehatan;
    }

    public int getSapiid() {
        return sapiid;
    }

    public void setSapiid(int sapiid) {
        this.sapiid = sapiid;
    }

    public float getUmur() {
        return umur;
    }

    public void setUmur(float umur) {
        this.umur = umur;
    }
    private float berat;
    private float umur;
    private float kesehatan;
    private float jk;
}
