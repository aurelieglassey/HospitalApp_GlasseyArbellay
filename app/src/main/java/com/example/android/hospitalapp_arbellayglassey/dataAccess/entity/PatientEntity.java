package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;


public class PatientEntity {


    @NonNull
    private int idP;
    private String name;
    private char gender;
    private int roomNumber;
    private String bloodGroup;
    private int age;
    private String reasonAdmission;
    //private int idTreatment;

    public PatientEntity() {

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


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("gender", gender);
        result.put("room_number", roomNumber);
        result.put("blood_group", bloodGroup);
        result.put("age", age);
        result.put("reason_of_admission", reasonAdmission);

        return result;
    }

}
