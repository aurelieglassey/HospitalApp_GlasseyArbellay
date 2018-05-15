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
    private String idP;
    private String name;
    private String gender;
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

    @Exclude
    public String getIdP() {
        return idP;
    }

    public String getGender() {
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


    public void setIdP(String id) {
        this.idP = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
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
        result.put("roomNumber", roomNumber);
        result.put("bloodGroup", bloodGroup);
        result.put("age", age);
        result.put("reasonAdmission", reasonAdmission);

        return result;
    }

}
