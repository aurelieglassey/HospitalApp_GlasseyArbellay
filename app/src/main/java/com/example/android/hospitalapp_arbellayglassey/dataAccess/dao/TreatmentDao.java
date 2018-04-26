package com.example.android.hospitalapp_arbellayglassey.dataAccess.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;


import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;

import java.util.List;

@Dao
public interface TreatmentDao {

    //Insert a treatment
    @Insert
    long insertTreatment(TreatmentEntity treatment);

    // Delete a treatment
    // never used but it will be deleted on cascade when the patient is deleted
    @Delete
    void deleteTreatment(TreatmentEntity treatment);

    //Update a treatment
    @Update
    void updatetTreatment(TreatmentEntity treatment);

    //Get a treatment by idPatient
    @Query("SELECT * FROM Treatment WHERE idPatient = :idPatient")
    public TreatmentEntity getTreatmentByPatientId(int idPatient);

    //insert lists inside the database
    @Insert(onConflict = OnConflictStrategy.REPLACE) //replace the old data and continue the transaction
    void insertAllTreatment(List<TreatmentEntity> listTreatment);
}
