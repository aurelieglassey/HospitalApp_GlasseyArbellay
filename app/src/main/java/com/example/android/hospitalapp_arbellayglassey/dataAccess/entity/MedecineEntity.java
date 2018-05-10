package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;


public class MedecineEntity {

    @NonNull
    private int idM;
    private String name;
    private String type;
    private String activeIngredient;
    private String manufacturers;
    private String application;
    private String sideEffects;
    private int maxPerDay;

    // empty constructor
    public MedecineEntity() {

    }

    //Getters and setters

    @Exclude
    public int getIdM() {
        return idM;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public String getManufacturers() {
        return manufacturers;
    }

    public String getApplication() {
        return application;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public int getMaxPerDay() {
        return maxPerDay;
    }


    public void setIdM(int id) {
        this.idM = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public void setManufacturers(String manufacturers) {
        this.manufacturers = manufacturers;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public void setMaxPerDay(int maxPerDay) {
        this.maxPerDay = maxPerDay;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("type", type);
        result.put("active_ingredient", activeIngredient);
        result.put("manufacturers", manufacturers);
        result.put("application", application);
        result.put("side_effects", sideEffects);
        result.put("max_per_day", maxPerDay);

        return result;
    }



}
