/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.entity;

/**
 *
 * @author majid
 */
public class Weight {
    private int id;
    private int sapiId;
    private float berat;
    private float umur;
    private float kesehatan;
    private float jk;

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
