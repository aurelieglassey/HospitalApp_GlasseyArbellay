package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "Patient",
        foreignKeys = @ForeignKey(entity = Treatment.class,
                parentColumns = "id", //Va chercher dans treatment son id
                childColumns = "idTreatment", //Met l'id treatment en foreign key dans patient sous le nom de idTreatment
                onDelete = CASCADE))

public class Patient {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "gender")
    private char gender;

    @ColumnInfo(name = "room_number")
    private int roomNumber;

    @ColumnInfo(name = "blood_group")
    private String bloodGroup;

    @ColumnInfo(name = "age")
    private int age;

    @ColumnInfo(name = "reason_of_admission")
    private String reasonAdmission;


}
