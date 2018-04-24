package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity (tableName = "Treatment",
        foreignKeys = @ForeignKey(entity = PatientEntity.class,
                parentColumns = "idP",
                childColumns = "idPatient",
                onDelete = CASCADE,
                onUpdate = CASCADE),
        indices = {
                @Index(
                        value = {"idPatient"}
                )})
public class TreatmentEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idT")
    private int idT;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "Max_quantity")
    private int maxQuantity;

    @ColumnInfo(name = "idPatient")
    @Nullable
    private int idPatient;

    public TreatmentEntity() {
    }

    public TreatmentEntity(String name, int maxQuantity, int idPatient) {
        this.name = name;
        this.maxQuantity = maxQuantity;
        this.idPatient = idPatient;
    }

    //Getters and setters
    public int getIdT() {
        return idT;
    }

    public String getName() {
        return name;
    }

    public int getIdPatient() {
        return idPatient;
    }


    public void setIdT(int id) {
        this.idT = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
