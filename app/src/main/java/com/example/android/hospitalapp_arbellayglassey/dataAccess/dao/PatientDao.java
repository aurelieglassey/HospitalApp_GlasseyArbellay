package com.example.android.hospitalapp_arbellayglassey.dataAccess.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;

import java.util.List;

@Dao
public interface PatientDao {


    @Insert
    long insertPatient(PatientEntity patient);

    @Delete
    void deletePatient(PatientEntity patient);

    @Update
    void updatePatient(PatientEntity patient);

    //Get a list of patient
    @Query("SELECT * FROM Patient")
    public abstract List<PatientEntity> getAllPatient();

    //get a list of live data patient
    @Query("SELECT * FROM Patient")
    LiveData<List<PatientEntity>> getAll();

    //Get a Patient from his id
    @Query("SELECT * FROM Patient WHERE idP = :id")
    PatientEntity getByIdSync(int id);

    //Get all patients form the app
    @Query("SELECT * FROM Patient")
    List<PatientEntity> getAllSync();

    //Get a patient by ID
    @Query("SELECT * FROM Patient WHERE idP = :idsearch")
    PatientEntity getOnePatientById(int idsearch);

    //insert lists inside the database
    @Insert(onConflict = OnConflictStrategy.REPLACE) //replace the old data and continue the transaction
    void insertAllPatient(List<PatientEntity> listPatient);

}
