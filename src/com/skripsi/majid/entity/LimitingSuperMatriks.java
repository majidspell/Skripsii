/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.entity;

/**
 *
 * @author majid
 */
public class LimitingSuperMatriks {
    private int id;
    private int sapiId;
    private float totalUnweight;
    private float totalWeight;
    private float jumlah;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getJumlah() {
        return jumlah;
    }

    public void setJumlah(float jumlah) {
        this.jumlah = jumlah;
    }

    public int getSapiId() {
        return sapiId;
    }

    public void setSapiId(int sapiId) {
        this.sapiId = sapiId;
    }

    public float getTotalUnweight() {
        return totalUnweight;
    }

    public void setTotalUnweight(float totalUnweight) {
        this.totalUnweight = totalUnweight;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(float totalWeight) {
        this.totalWeight = totalWeight;
    }
}
