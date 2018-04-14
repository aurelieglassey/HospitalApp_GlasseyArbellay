package com.example.android.hospitalapp_arbellayglassey.dataAccess.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.Patient;

@Dao
public interface PatientDao {

    @Insert
    void insertPatient(Patient patient);

    @Delete
    void deletePatient(Patient patient);

    @Update
    void updatePatient(Patient patient);

    //Get a list of patient
    @Query("SELECT * FROM Patient")
    public Patient[] getAllPatient();

    //Get a patient from ID
    @Query("SELECT * FROM Patient WHERE id = :idsearch")
    public Patient getOnePatientById(int idsearch);

}
