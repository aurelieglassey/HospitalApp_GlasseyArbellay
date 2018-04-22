package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "Patient")
public class PatientEntity {

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "idP")
    private int idP;

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

    public PatientEntity() {

    }


    public PatientEntity(String name, char gender, int roomNumber, String bloodGroup, int age, String reasonAdmission, int idTreatment) {
        //this.idP = id;
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

    public int getIdP() {
        return idP;
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


    public void setIdP(int id) {
        this.idP = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setReasonAdmission(String reasonAdmission) {
        this.reasonAdmission = reasonAdmission;
    }

    public void setIdTreatment(int idTreatment) {
        this.idTreatment = idTreatment;
    }
}
