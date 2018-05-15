package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import static android.arch.persistence.room.ForeignKey.CASCADE;


public class TreatmentEntity {

    @NonNull
    private String idT;
    private String name;
    private int maxQuantity;
    private String idPatient;

    public TreatmentEntity() {
    }

    //Full constructor of TreatmentEntity
    public TreatmentEntity(String name, int maxQuantity, String idPatient) {
        this.name = name;
        this.maxQuantity = maxQuantity;
        this.idPatient = idPatient;
    }

    //Getters and setters
    @Exclude
    public String getIdT() {
        return idT;
    }

    public String getName() {
        return name;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdT(String id) {
        this.idT = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("max_quantity", maxQuantity);
        result.put("idPatient", idPatient);

        return result;
    }
}
