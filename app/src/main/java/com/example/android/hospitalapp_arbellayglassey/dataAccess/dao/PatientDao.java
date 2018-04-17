package com.example.android.hospitalapp_arbellayglassey.dataAccess.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.os.AsyncTask;


import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;

import java.util.List;

@Dao
public interface PatientDao {

    //AsyncTask -> a extends sur la classe --> to query data and work with database : pour faire tourner les requêts sql en background pour pas que l'app crash
    //Data, data, id de la data -> database, then righ clik on save pour la télécharger et l'ouvrir avec sql lite
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
    @Query("SELECT * FROM Patient WHERE id = :idsearch")
    PatientEntity getOnePatientById(int idsearch);


}
