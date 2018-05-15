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


public class TreatmentMedecineLinkEntity {


    @NonNull
    private String idL;
    private String idTreatment;
    private String idMedecine;
    //private Map<String, Boolean> medecines = new HashMap<>();

    // constructor
    public TreatmentMedecineLinkEntity() {

    }

    //Getters and setters

    public String getIdTreatment() {
        return idTreatment;
    }

    public String getIdMedecine() {
        return idMedecine;
    }

    public void setIdTreatment(String idTreatment) {
        this.idTreatment = idTreatment;
    }

    public void setIdMedecine(String idMedecine) {
        this.idMedecine = idMedecine;
    }

    public String getIdL() {
        return idL;
    }

    public void setIdL(String idL) {
        this.idL = idL;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("idTreatement", idMedecine);
        result.put("idMedecine", idTreatment);

        return result;
    }

}
