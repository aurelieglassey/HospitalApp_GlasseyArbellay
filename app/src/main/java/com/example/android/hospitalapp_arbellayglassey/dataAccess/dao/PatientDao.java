package com.example.android.hospitalapp_arbellayglassey.dataAccess.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.os.AsyncTask;


import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PatientDao {


    @Insert
    void insertPatient(PatientEntity patient);

    @Delete
    void deletePatient(PatientEntity patient);

    @Update
    void updatePatient(PatientEntity patient);

    //Get a list of patient
    @Query("SELECT * FROM Patient")
    List<PatientEntity> getAllPatient();

    //Get a patient by ID
    @Query("SELECT * FROM Patient WHERE idP = :idsearch")
    PatientEntity getOnePatientById(int idsearch);

    //insert lists inside the database
    @Insert(onConflict = OnConflictStrategy.REPLACE) //replace the old data and continue the transaction
    void insertAllPatient(List<PatientEntity> listPatient);

}
