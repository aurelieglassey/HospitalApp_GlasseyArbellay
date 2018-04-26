package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(tableName = "Medecine")
public class MedecineEntity {

    //PK
    @PrimaryKey(autoGenerate = true)
    private int idM;

    // somme stuff
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "active_ingredient")
    private String activeIngredient;

    @ColumnInfo(name = "manufacturers")
    private String manufacturers;

    @ColumnInfo(name = "application")
    private String application;

    @ColumnInfo(name = "side_effects")
    private String sideEffects;

    @ColumnInfo(name = "max_per_day")
    private int maxPerDay;

    // empty constructor
    public MedecineEntity() {

    }




    //Getters and setters
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


        /*
        //Full constructor of MedecineEntity
    public MedecineEntity(int id, String name, String type, String activeIngredient, String manufacturers, String application, String sideEffects, int maxPerDay) {
        this.idM = id;
        this.name = name;
        this.type = type;
        this.activeIngredient = activeIngredient;
        this.manufacturers = manufacturers;
        this.application = application;
        this.sideEffects = sideEffects;
        this.maxPerDay = maxPerDay;
    }
    */


}
