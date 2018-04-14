package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity (tableName = "Treatment",
        foreignKeys = @ForeignKey(entity = Patient.class,
                parentColumns = "id",
                childColumns = "idPatient",
                onDelete = CASCADE,
                onUpdate = CASCADE
        ))

public class Treatment {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;


}
