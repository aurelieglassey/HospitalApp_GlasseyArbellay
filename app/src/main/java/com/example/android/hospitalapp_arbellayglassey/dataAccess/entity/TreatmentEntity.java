package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity (tableName = "Treatment",
        foreignKeys = @ForeignKey(entity = PatientEntity.class,
                parentColumns = "idP",
                childColumns = "idPatient",
                onDelete = CASCADE,
                onUpdate = CASCADE))
public class TreatmentEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idT")
    private int idT;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "idPatient")
    @Nullable
    private int idPatient;

    public TreatmentEntity() {

    }

    public TreatmentEntity(int id, String name, int idPatient) {
        this.idT = id;
        this.name = name;
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
}
