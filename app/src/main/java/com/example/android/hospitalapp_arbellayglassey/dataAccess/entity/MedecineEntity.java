package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;

import android.support.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;


public class MedecineEntity {

    @NonNull
    private String idM;
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
    public String getIdM() {
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


    public void setIdM(String id) {
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


    //map for firebase
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("type", type);
        result.put("activeIngredient", activeIngredient);
        result.put("manufacturers", manufacturers);
        result.put("application", application);
        result.put("sideEffects", sideEffects);
        result.put("maxPerDay", maxPerDay);

        return result;
    }



}
