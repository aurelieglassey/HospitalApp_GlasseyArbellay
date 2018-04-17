package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity (tableName = "Treatment",
        foreignKeys = @ForeignKey(entity = PatientEntity.class,
                parentColumns = "id",
                childColumns = "idPatient",
                onDelete = CASCADE,
                onUpdate = CASCADE
        ))

public class TreatmentEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "idPatient")
    private int idPatient;

    public TreatmentEntity(int id, String name, int idPatient) {
        this.id = id;
        this.name = name;
        this.idPatient = idPatient;
    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIdPatient() {
        return idPatient;
    }





}
