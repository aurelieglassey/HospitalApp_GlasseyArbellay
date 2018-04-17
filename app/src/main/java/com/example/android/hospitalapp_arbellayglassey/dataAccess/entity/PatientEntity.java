package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "Patient",
        foreignKeys = @ForeignKey(entity = TreatmentEntity.class,
                parentColumns = "id", //Va chercher dans treatment son id
                childColumns = "idTreatment", //Met l'id treatment en foreign key dans patient sous le nom de idTreatment
                onDelete = CASCADE))

public class PatientEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
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

    @ColumnInfo(name = "idTreatment")
    private int idTreatment;



    public PatientEntity(int id, String name, char gender, int roomNumber, String bloodGroup, int age, String reasonAdmission, int idTreatment) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.roomNumber = roomNumber;
        this.bloodGroup = bloodGroup;
        this.age = age;
        this.reasonAdmission = reasonAdmission;
        this.idTreatment = idTreatment;
    }
    //Getters and setters

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public char getGender() {
        return gender;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public int getAge() {
        return age;
    }

    public String getReasonAdmission() {
        return reasonAdmission;
    }

    public int getIdTreatment() {
        return idTreatment;
    }





}
